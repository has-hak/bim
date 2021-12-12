package am.nuaca.bim.service;

import java.util.List;

/**
 * @author Ani Khachatryan on 17-Apr-20.
 */
public class ResourceSearchCriteria {

	private final int compilationId;

	private final List<ResourceAttributes> resources;

	public ResourceSearchCriteria(int compilationId, List<ResourceAttributes> resources) {
		this.compilationId = compilationId;
		this.resources = resources;
	}

	public int getCompilationId() {
		return compilationId;
	}

	public List<ResourceAttributes> getResources() {
		return resources;
	}
}
