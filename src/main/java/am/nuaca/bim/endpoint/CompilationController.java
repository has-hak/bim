package am.nuaca.bim.endpoint;

import java.util.List;

import am.nuaca.bim.dto.CompilationCreationCommand;
import am.nuaca.bim.entity.Compilation;
import am.nuaca.bim.service.CompilationService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@RestController
@RequestMapping("compilations")
public class CompilationController {

	private final CompilationService compilationService;

	public CompilationController(CompilationService compilationService) {
		this.compilationService = compilationService;
	}

	@GetMapping
	public List<Compilation> getAll() {
		return compilationService.getAllCompilations();
	}

	@PostMapping
	public void create(@RequestBody CompilationCreationCommand compilationCreationCommand) {
		compilationService.createCompilation(compilationCreationCommand);
	}

	@PutMapping("/{compilationId}")
	public void update(@PathVariable int compilationId,
					   @RequestBody CompilationCreationCommand compilationCreationCommand) {
		compilationService.updateCompilation(compilationId, compilationCreationCommand);
	}

	@DeleteMapping("/{compilationId}")
	public void delete(@PathVariable int compilationId) {
		compilationService.deleteCompilation(compilationId);
	}
}
