package am.nuaca.bim.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import am.nuaca.bim.application.BudgetDocumentReader;
import am.nuaca.bim.application.Standards;
import am.nuaca.bim.application.model.BudgetDocument;
import am.nuaca.bim.application.model.Measure;
import am.nuaca.bim.application.model.MeasureType;
import am.nuaca.bim.application.model.Unit;
import am.nuaca.bim.entity.Compilation;
import am.nuaca.bim.entity.Resource;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@Service
public class BudgetDocumentService {

	private final CompilationService compilationService;

	private final ResourcesService resourcesService;

	public BudgetDocumentService(CompilationService compilationService, ResourcesService resourcesService) {
		this.compilationService = compilationService;
		this.resourcesService = resourcesService;
	}

	public List<Resource> calculate(InputStream file) throws IOException {
		BudgetDocumentReader budgetDocumentReader = new BudgetDocumentReader(file);
		BudgetDocument budgetDocument = budgetDocumentReader.read();

		List<Compilation> compilations = compilationService.searchByName(budgetDocument.getCompilationName());

		String resourceTitle = budgetDocument.getResourceTitle();
		List<Measure> measures = standardizeMeasures(budgetDocument.getMeasures());

		return compilations.stream()
				.flatMap(compilation -> resourcesService.searchByCriteria(
						new ResourceSearchCriteria(compilation.getId(), resourceTitle, measures)).stream())
				.collect(toList());
	}

	private static List<Measure> standardizeMeasures(List<Measure> measures) {
		Map<MeasureType, Unit> measureUnits = Standards.MEASURE_UNITS;

		return measures.stream().map(measure -> {
			MeasureType type = measure.getType();
			Unit unit = measure.getUnit();
			double value = measure.getValue();

			Unit standardUnit = measureUnits.get(type);
			double standardValue = standardUnit.from(unit, value);

			return measure.changeValue(standardUnit, standardValue);
		}).collect(Collectors.toList());
	}
}
