package am.nuaca.bim.endpoint.api;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import am.nuaca.bim.dto.ResourceCreationCommand;
import am.nuaca.bim.dto.ResourceDto;
import am.nuaca.bim.dto.ResourcesResponse;
import am.nuaca.bim.dto.StandardsDto;
import am.nuaca.bim.entity.*;
import am.nuaca.bim.helper.Iterables;
import am.nuaca.bim.repository.*;
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

	private final MachineRepository machineRepository;

	private final WorkforceRepository workforceRepository;

	private final MaterialRepository materialRepository;

	public ResourceController(CompilationRepository compilationRepository, ResourcesRepository resourcesRepository,
							  MachineRepository machineRepository, WorkforceRepository workforceRepository,
							  MaterialRepository materialRepository) {
		this.compilationRepository = compilationRepository;
		this.resourcesRepository = resourcesRepository;
		this.machineRepository = machineRepository;
		this.workforceRepository = workforceRepository;
		this.materialRepository = materialRepository;
	}

	@PostMapping
	@Secured({"MANAGER", "ADMIN"})
	public void create(@RequestBody ResourceCreationCommand resourceCreationCommand) {
		Compilation compilation = compilationRepository.findById(resourceCreationCommand.getCompilationId())
				.orElseThrow(IllegalArgumentException::new);

		List<Machine> machines = Iterables.iterableToList(
				machineRepository.findAllById(resourceCreationCommand.getMachineIds()));
		List<Workforce> workforces = Iterables.iterableToList(
				workforceRepository.findAllById(resourceCreationCommand.getMachineIds()));
		List<Material> materials = Iterables.iterableToList(
				materialRepository.findAllById(resourceCreationCommand.getMachineIds()));

		Resource resource = Resource.forCompilation(compilation, resourceCreationCommand.getCode(),
				resourceCreationCommand.getTitle(), Collections.emptyMap(), machines, workforces, materials);

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
