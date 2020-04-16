package am.nuaca.bim.endpoint;

import java.io.IOException;
import java.util.List;

import am.nuaca.bim.entity.Compilation;
import am.nuaca.bim.service.BudgetDocumentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@RestController
@RequestMapping("budget-documents")
public class BudgetDocumentController {

	private final BudgetDocumentService budgetDocumentService;

	public BudgetDocumentController(BudgetDocumentService budgetDocumentService) {
		this.budgetDocumentService = budgetDocumentService;
	}

	@PostMapping(produces={"application/json; charset=UTF-8"})
	public List<Compilation> calculate(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		return budgetDocumentService.calculate(multipartFile.getInputStream());
	}
}
