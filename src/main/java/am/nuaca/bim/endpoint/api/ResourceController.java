package am.nuaca.bim.endpoint.api;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import am.nuaca.bim.dto.ResourceCreationCommand;
import am.nuaca.bim.dto.ResourceDto;
import am.nuaca.bim.dto.ResourcesResponse;
import am.nuaca.bim.dto.StandardsDto;
import am.nuaca.bim.entity.Compilation;
import am.nuaca.bim.entity.Resource;
import am.nuaca.bim.repository.CompilationRepository;
import am.nuaca.bim.repository.ResourcesRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tigran Sargsyan on 16-Apr-20.
 */
@RestController
@RequestMapping("api/resources")
public class ResourceController {

	private final CompilationRepository compilationRepository;

	private final ResourcesRepository resourcesRepository;

	public ResourceController(CompilationRepository compilationRepository, ResourcesRepository resourcesRepository) {
		this.compilationRepository = compilationRepository;
		this.resourcesRepository = resourcesRepository;
	}

	@PostMapping
	@Secured({"MANAGER", "ADMIN"})
	public void create(@RequestBody ResourceCreationCommand resourceCreationCommand) {
		Compilation compilation = compilationRepository.findById(resourceCreationCommand.getCompilationId())
				.orElseThrow(IllegalArgumentException::new);

		Resource resource = Resource.forCompilation(compilation, resourceCreationCommand.getCode(),
				resourceCreationCommand.getTitle(), Collections.emptyMap());
		resourcesRepository.save(resource);
	}

	@GetMapping
	public ResourcesResponse getAll() {
		List<ResourceDto> resourceDtos = StreamSupport.stream(resourcesRepository.findAll().spliterator(), false)
				.map(resource -> new ResourceDto(resource.getId(), resource.getCode().toString(), resource.getTitle(),
						resource.getMeasures()))
				.collect(Collectors.toList());

		return new ResourcesResponse(resourceDtos, new StandardsDto());
	}
}
