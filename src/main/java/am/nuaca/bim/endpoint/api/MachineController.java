package am.nuaca.bim.endpoint.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import am.nuaca.bim.dto.MachineCreationCommand;
import am.nuaca.bim.dto.MachineDto;
import am.nuaca.bim.entity.Machine;
import am.nuaca.bim.repository.MachineRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@RestController
@RequestMapping("api/machines")
public class MachineController {

	private final MachineRepository machineRepository;

	public MachineController(MachineRepository machineRepository) {
		this.machineRepository = machineRepository;
	}

	@PostMapping
	@Secured({"MANAGER", "ADMIN"})
	public void create(@RequestBody MachineCreationCommand command) {
		Machine machine = new Machine(command.getCode(), command.getTitle(), command.getUnit(), command.getUnitCost());

		machineRepository.save(machine);
	}

	@GetMapping
	public List<MachineDto> getAll() {
		return StreamSupport.stream(machineRepository.findAll().spliterator(), false)
				.map(material -> new MachineDto(material.getId(), material.getCode().toString(), material.getTitle(),
						material.getUnit(), material.getUnitCost()))
				.collect(Collectors.toList());
	}
}
