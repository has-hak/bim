package am.nuaca.bim.dto;

import java.util.List;

import am.nuaca.bim.application.model.MeasureType;

/**
 * @author Ani Khachatryan on 12-May-20.
 */
public class BudgetDocumentDto {

	private final List<ResourceDto> resources;

	public BudgetDocumentDto(List<ResourceDto> resources) {
		this.resources = resources;
	}

	public List<ResourceDto> getResources() {
		return resources;
	}

	public static final class ResourceDto {
		private final Long id;

		private final String code;

		private final String title;

		private final List<WorkforceDto> workforces;

		private final List<MachineDto> machines;

		private final List<MaterialDto> materials;

		public ResourceDto(Long id, String code, String title, List<WorkforceDto> workforces, List<MachineDto> machines,
						   List<MaterialDto> materials) {
			this.id = id;
			this.code = code;
			this.title = title;
			this.workforces = workforces;
			this.machines = machines;
			this.materials = materials;
		}

		public Long getId() {
			return id;
		}

		public String getCode() {
			return code;
		}

		public String getTitle() {
			return title;
		}

		public List<WorkforceDto> getWorkforces() {
			return workforces;
		}

		public List<MachineDto> getMachines() {
			return machines;
		}

		public List<MaterialDto> getMaterials() {
			return materials;
		}
	}

	public static final class WorkforceDto {

		private final Long id;

		private final String code;

		private final String title;

		private final double unit;

		private final double unitCost;

		public WorkforceDto(Long id, String code, String title, double unit, double unitCost) {
			this.id = id;
			this.code = code;
			this.title = title;
			this.unit = unit;
			this.unitCost = unitCost;
		}

		public Long getId() {
			return id;
		}

		public String getCode() {
			return code;
		}

		public String getTitle() {
			return title;
		}

		public double getUnit() {
			return unit;
		}

		public double getUnitCost() {
			return unitCost;
		}
	}

	public static final class MachineDto {

		private final Long id;

		private final String code;

		private final String title;

		private final double unit;

		private final double unitCost;

		public MachineDto(Long id, String code, String title, double unit, double unitCost) {
			this.id = id;
			this.code = code;
			this.title = title;
			this.unit = unit;
			this.unitCost = unitCost;
		}

		public Long getId() {
			return id;
		}

		public String getCode() {
			return code;
		}

		public String getTitle() {
			return title;
		}

		public double getUnit() {
			return unit;
		}

		public double getUnitCost() {
			return unitCost;
		}
	}

	public static final class MaterialDto {
		private final Long id;

		private final String code;

		private final String title;

		private final double unit;

		private final double unitCost;

		private final MeasureType measureType;

		public MaterialDto(Long id, String code, String title, double unit, double unitCost, MeasureType measureType) {
			this.id = id;
			this.code = code;
			this.title = title;
			this.unit = unit;
			this.unitCost = unitCost;
			this.measureType = measureType;
		}

		public Long getId() {
			return id;
		}

		public String getCode() {
			return code;
		}

		public String getTitle() {
			return title;
		}

		public double getUnit() {
			return unit;
		}

		public double getUnitCost() {
			return unitCost;
		}

		public MeasureType getMeasureType() {
			return measureType;
		}
	}
}
