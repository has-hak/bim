package am.nuaca.bim.endpoint.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import am.nuaca.bim.dto.MaterialDto;
import am.nuaca.bim.repository.MaterialRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tigran Sargsyan on 07-May-20.
 */
@RestController
@RequestMapping("/api/materials")
public class MaterialController {

	private final MaterialRepository materialRepository;

	public MaterialController(MaterialRepository materialRepository) {
		this.materialRepository = materialRepository;
	}

	@GetMapping
	public List<MaterialDto> getAll() {
		return StreamSupport.stream(materialRepository.findAll().spliterator(), false)
				.map(material -> new MaterialDto(material.getId(), material.getCode().toString(), material.getTitle(),
						material.getUnit(), material.getUnitCost(), material.getMeasureType()))
				.collect(Collectors.toList());
	}
}
