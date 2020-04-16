package am.nuaca.bim.service;

import am.nuaca.bim.application.model.BudgetDocument;
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

	public void searchByDocument(BudgetDocument budgetDocument){

	}
}
