package am.nuaca.bim.endpoint.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import am.nuaca.bim.dto.MaterialCreationCommand;
import am.nuaca.bim.dto.MaterialDto;
import am.nuaca.bim.entity.Material;
import am.nuaca.bim.repository.MaterialRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ani Khachatryan on 07-May-20.
 */
@RestController
@RequestMapping("/api/materials")
public class MaterialController {

	private final MaterialRepository materialRepository;

	public MaterialController(MaterialRepository materialRepository) {
		this.materialRepository = materialRepository;
	}

	@PostMapping
	@Secured({"MANAGER", "ADMIN"})
	public Long create(@Valid @RequestBody MaterialCreationCommand command) {
		Material material = new Material(command.getCode(), command.getTitle(), command.getUnit(),
				command.getUnitCost(), command.getMeasureType());

		return materialRepository.save(material).getId();
	}

	@PutMapping("/{materialId}")
	public void update(@PathVariable long materialId, @Valid @RequestBody MaterialCreationCommand command) {
		Material material = new Material(materialId, command.getCode(), command.getTitle(), command.getUnit(),
				command.getUnitCost(), command.getMeasureType());
		materialRepository.save(material);
	}

	@DeleteMapping("/{materialId}")
	public void delete(@PathVariable long materialId) {
		materialRepository.deleteById(materialId);
	}

	@GetMapping
	public List<MaterialDto> getAll() {
		return StreamSupport.stream(materialRepository.findAll().spliterator(), false)
				.map(material -> new MaterialDto(material.getId(), material.getCode().toString(), material.getTitle(),
						material.getUnit(), material.getUnitCost(), material.getMeasureType()))
				.collect(Collectors.toList());
	}
}
