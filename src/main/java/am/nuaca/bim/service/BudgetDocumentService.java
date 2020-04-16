package am.nuaca.bim.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import am.nuaca.bim.application.model.BudgetDocument;
import am.nuaca.bim.application.BudgetDocumentReader;
import am.nuaca.bim.entity.Compilation;
import org.springframework.stereotype.Service;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@Service
public class BudgetDocumentService {

	private final CompilationService compilationService;

	public BudgetDocumentService(CompilationService compilationService) {
		this.compilationService = compilationService;
	}

	public List<Compilation> calculate(InputStream file) throws IOException {
		BudgetDocumentReader budgetDocumentReader = new BudgetDocumentReader(file);
		BudgetDocument budgetDocument = budgetDocumentReader.read();

		List<Compilation> compilations = compilationService.searchByName(budgetDocument.getCompilationName());


		return compilations;
	}
}
