package am.nuaca.bim.endpoint.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import am.nuaca.bim.dto.ResourceDto;
import am.nuaca.bim.dto.ResourcesResponse;
import am.nuaca.bim.dto.StandardsDto;
import am.nuaca.bim.repository.ResourcesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tigran Sargsyan on 16-Apr-20.
 */
@RestController
@RequestMapping("api/resources")
public class ResourceController {

	private final ResourcesRepository resourcesRepository;

	public ResourceController(ResourcesRepository resourcesRepository) {
		this.resourcesRepository = resourcesRepository;
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
