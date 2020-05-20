package am.nuaca.bim.dto;

import java.util.List;

/**
 * @author Tigran Sargsyan on 06-May-20.
 */
public class ResourcesResponse {

	private final List<ResourceDto> resources;

	private final StandardsDto standards;

	public ResourcesResponse(List<ResourceDto> resources, StandardsDto standards) {
		this.resources = resources;
		this.standards = standards;
	}

	public List<ResourceDto> getResources() {
		return resources;
	}

	public StandardsDto getStandards() {
		return standards;
	}
}
