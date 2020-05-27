package am.nuaca.bim.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import am.nuaca.bim.application.Standards;
import am.nuaca.bim.application.document.BudgetDocumentReader;
import am.nuaca.bim.application.document.InvalidBudgetDocumentException;
import am.nuaca.bim.application.document.OutlayDocumentBuilder;
import am.nuaca.bim.application.model.BudgetDocument;
import am.nuaca.bim.application.model.Measure;
import am.nuaca.bim.application.model.MeasureType;
import am.nuaca.bim.application.model.MeasureUnit;
import am.nuaca.bim.entity.Compilation;
import am.nuaca.bim.entity.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@Service
public class OutlayService {

	private final CompilationService compilationService;

	private final ResourcesService resourcesService;

	public OutlayService(CompilationService compilationService, ResourcesService resourcesService) {
		this.compilationService = compilationService;
		this.resourcesService = resourcesService;
	}

	public void calculateOutlayFromBudgetDocument(InputStream budgetDocumentStream,
												  OutputStream costDocumentOutputStream) throws IOException, InvalidBudgetDocumentException {
		List<Resource> resources = processBudgetDocument(budgetDocumentStream);

		OutlayDocumentBuilder outlayDocumentBuilder = new OutlayDocumentBuilder(resources);
		outlayDocumentBuilder.build(costDocumentOutputStream);
	}

	public List<Resource> processBudgetDocument(
			InputStream budgetDocumentStream) throws IOException, InvalidBudgetDocumentException {

		BudgetDocumentReader budgetDocumentReader = new BudgetDocumentReader(budgetDocumentStream);
		BudgetDocument budgetDocument = budgetDocumentReader.read();

		List<Compilation> compilations = compilationService.searchByName(budgetDocument.getCompilationName());
		if (compilations.isEmpty()) {
			throw new IllegalArgumentException("No compilation found");
		}
		Compilation compilation = compilations.get(0);

		List<ResourceAttributes> resourceAttributes = budgetDocument.getResources()
				.stream()
				.map(budgetDocumentResource -> new ResourceAttributes(budgetDocumentResource.getTitle(),
						standardizeMeasures(budgetDocumentResource.getMeasures())))
				.collect(Collectors.toList());

		return resourcesService.searchByCriteria(new ResourceSearchCriteria(compilation.getId(), resourceAttributes));

	}

	private static List<Measure> standardizeMeasures(List<Measure> measures) {
		Map<MeasureType, MeasureUnit> measureUnits = Standards.MEASURE_UNITS;

		return measures.stream().map(measure -> {
			MeasureType type = measure.getType();
			MeasureUnit measureUnit = measure.getMeasureUnit();
			double value = measure.getValue();

			MeasureUnit standardMeasureUnit = measureUnits.get(type);
			double standardValue = standardMeasureUnit.from(measureUnit, value);

			return measure.changeValue(standardMeasureUnit, standardValue);
		}).collect(Collectors.toList());
	}
}
