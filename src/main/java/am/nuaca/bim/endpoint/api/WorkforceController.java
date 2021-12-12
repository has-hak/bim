package am.nuaca.bim.endpoint.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.Valid;

import am.nuaca.bim.dto.WorkforceCreationCommand;
import am.nuaca.bim.dto.WorkforceDto;
import am.nuaca.bim.entity.Workforce;
import am.nuaca.bim.repository.WorkforceRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ani Khachatryan on 12-May-20.
 */
@RestController
@RequestMapping("/api/workforces")
public class WorkforceController {

	private final WorkforceRepository workforceRepository;

	public WorkforceController(WorkforceRepository workforceRepository) {
		this.workforceRepository = workforceRepository;
	}

	@PostMapping
	@Secured({"MANAGER", "ADMIN"})
	public Long create(@Valid @RequestBody WorkforceCreationCommand command) {
		Workforce workforce = new Workforce(command.getCode(), command.getTitle(), command.getUnit(),
				command.getUnitCost());

		return workforceRepository.save(workforce).getId();
	}

	@PutMapping("/{workforceId}")
	public void update(@PathVariable long workforceId, @Valid @RequestBody WorkforceCreationCommand command) {
		Workforce workforce = new Workforce(workforceId, command.getCode(), command.getTitle(), command.getUnit(),
				command.getUnitCost());
		workforceRepository.save(workforce);
	}

	@DeleteMapping("/{workforceId}")
	public void delete(@PathVariable long workforceId) {
		workforceRepository.deleteById(workforceId);
	}

	@GetMapping
	public List<WorkforceDto> getAll() {
		return StreamSupport.stream(workforceRepository.findAll().spliterator(), false)
				.map(material -> new WorkforceDto(material.getId(), material.getCode().toString(), material.getTitle(),
						material.getUnit(), material.getUnitCost()))
				.collect(Collectors.toList());
	}
}
