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
	public void create(@RequestBody ResourceCreationCommand command) {
		Compilation compilation = compilationRepository.findById(command.getCompilationId())
				.orElseThrow(IllegalArgumentException::new);

		List<Machine> machines = Iterables.iterableToList(machineRepository.findAllById(command.getMachineIds()));
		List<Workforce> workforces = Iterables.iterableToList(workforceRepository.findAllById(command.getMachineIds()));
		List<Material> materials = Iterables.iterableToList(materialRepository.findAllById(command.getMachineIds()));

		Resource resource = Resource.forCompilation(compilation, command.getCode(), command.getTitle(),
				Collections.emptyMap(), machines, workforces, materials);

		resourcesRepository.save(resource);
	}

	@PutMapping("/{resourceId}")
	public void update(@PathVariable long resourceId, @RequestBody ResourceCreationCommand command) {
		Compilation compilation = compilationRepository.findById(command.getCompilationId())
				.orElseThrow(IllegalArgumentException::new);

		List<Machine> machines = Iterables.iterableToList(machineRepository.findAllById(command.getMachineIds()));
		List<Workforce> workforces = Iterables.iterableToList(workforceRepository.findAllById(command.getMachineIds()));
		List<Material> materials = Iterables.iterableToList(materialRepository.findAllById(command.getMachineIds()));

		Resource resource = new Resource(resourceId, command.getCode(), command.getTitle(), compilation,
				Collections.emptyMap(), machines, workforces, materials);

		resourcesRepository.save(resource);
	}

	@DeleteMapping("/{resourceId}")
	public void delete(@PathVariable long resourceId) {
		workforceRepository.deleteById(resourceId);
	}

	@GetMapping
	public ResourcesResponse getAll() {
		List<ResourceDto> resourceDtos = StreamSupport.stream(resourcesRepository.findAll().spliterator(), false)
				.map(resource -> new ResourceDto(resource.getCompilation().getId(), resource.getId(),
						resource.getCode().toString(), resource.getTitle(), resource.getMeasures(),
						resource.getWorkforces().stream().map(Workforce::getId).collect(Collectors.toList()),
						resource.getMachines().stream().map(Machine::getId).collect(Collectors.toList()),
						resource.getMaterials().stream().map(Material::getId).collect(Collectors.toList())))
				.collect(Collectors.toList());

		return new ResourcesResponse(resourceDtos, new StandardsDto());
	}
}
