package am.nuaca.bim.service;

import java.util.List;

import am.nuaca.bim.entity.Resource;
import am.nuaca.bim.repository.ResourcesByMeasuresSpecification;
import am.nuaca.bim.repository.ResourcesRepository;
import org.springframework.stereotype.Service;

/**
 * @author Tigran Sargsyan on 16-Apr-20.
 */
@Service
public class ResourcesService {

	private final ResourcesRepository resourcesRepository;

	public ResourcesService(ResourcesRepository resourcesRepository) {
		this.resourcesRepository = resourcesRepository;
	}

	public List<Resource> searchByCriteria(ResourceSearchCriteria resourceSearchCriteria) {
		ResourcesByMeasuresSpecification specification = new ResourcesByMeasuresSpecification(resourceSearchCriteria);

		return resourcesRepository.findAll(specification);
	}
}
