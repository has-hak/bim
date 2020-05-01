package am.nuaca.bim.endpoint.view;

import java.util.List;

import am.nuaca.bim.dto.CompilationCreationCommand;
import am.nuaca.bim.entity.Compilation;
import am.nuaca.bim.service.CompilationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Tigran Sargsyan on 23-Apr-20.
 */
@Controller
public class CompilationsViewController {

	private final CompilationService compilationService;

	public CompilationsViewController(CompilationService compilationService) {
		this.compilationService = compilationService;
	}

	@GetMapping(value = {"/compilations"})
	public String compilations(Model model) {
		List<Compilation> allCompilations = compilationService.getAllCompilations();
		model.addAttribute("compilations", allCompilations);

		return "compilations";
	}

	@GetMapping(value = "/compilations-save")
	public String compilationsSave(Model model) {
		Compilation compilation = new Compilation();

		model.addAttribute("compilation", compilation);

		return "compilations-save";
	}

	@PostMapping(value = "/compilations")
	public RedirectView save(@ModelAttribute CompilationCreationCommand compilationCreationCommand) {
		compilationService.createCompilation(compilationCreationCommand);

		return new RedirectView("compilations");
	}
}
