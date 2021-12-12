package am.nuaca.bim.endpoint.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.Valid;

import am.nuaca.bim.dto.MachineCreationCommand;
import am.nuaca.bim.dto.MachineDto;
import am.nuaca.bim.entity.Machine;
import am.nuaca.bim.repository.MachineRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ani Khachatryan on 15-Sep-21.
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
	public Long create(@Valid @RequestBody MachineCreationCommand command) {
		Machine machine = new Machine(command.getCode(), command.getTitle(), command.getUnit(), command.getUnitCost());

		return machineRepository.save(machine).getId();
	}

	@PutMapping("/{machineId}")
	public void update(@PathVariable long machineId, @Valid @RequestBody MachineCreationCommand command) {
		Machine machine = new Machine(machineId, command.getCode(), command.getTitle(), command.getUnit(),
				command.getUnitCost());
		machineRepository.save(machine);
	}

	@DeleteMapping("/{machineId}")
	public void delete(@PathVariable long machineId) {
		machineRepository.deleteById(machineId);
	}

	@GetMapping
	public List<MachineDto> getAll() {
		return StreamSupport.stream(machineRepository.findAll().spliterator(), false)
				.map(material -> new MachineDto(material.getId(), material.getCode().toString(), material.getTitle(),
						material.getUnit(), material.getUnitCost()))
				.collect(Collectors.toList());
	}
}
