package am.nuaca.bim.endpoint.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import am.nuaca.bim.dto.WorkforceCreationCommand;
import am.nuaca.bim.dto.WorkforceDto;
import am.nuaca.bim.entity.Workforce;
import am.nuaca.bim.repository.WorkforceRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tigran Sargsyan on 12-May-20.
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
	public void create(@RequestBody WorkforceCreationCommand command) {
		Workforce workforce = new Workforce(command.getCode(), command.getTitle(), command.getUnit(),
				command.getUnitCost());

		workforceRepository.save(workforce);
	}

	@GetMapping
	public List<WorkforceDto> getAll() {
		return StreamSupport.stream(workforceRepository.findAll().spliterator(), false)
				.map(material -> new WorkforceDto(material.getId(), material.getCode().toString(), material.getTitle(),
						material.getUnit(), material.getUnitCost()))
				.collect(Collectors.toList());
	}
}
