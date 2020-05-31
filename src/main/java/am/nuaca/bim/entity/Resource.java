package am.nuaca.bim.entity;

import java.util.List;
import java.util.Map;
import javax.persistence.*;

import am.nuaca.bim.application.model.MeasureType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@Entity
@Table(name = "resources")
@TypeDefs({
		@TypeDef(name = "json", typeClass = JsonStringType.class)})
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String code;

	private String title;

	@ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Compilation compilation;

	@Type(type = "json")
	@Column(columnDefinition = "json")
	@Convert(disableConversion = true)
	private Map<MeasureType, ResourceMeasureValue> measures;

	@ManyToMany(targetEntity = Machine.class)
	@JoinTable(name = "resource_machines", joinColumns = @JoinColumn(name = "resource_id"),
			inverseJoinColumns = @JoinColumn(name = "machine_id"))
	private List<Machine> machines;

	@ManyToMany(targetEntity = Workforce.class)
	@JoinTable(name = "resource_workforces", joinColumns = @JoinColumn(name = "resource_id"),
			inverseJoinColumns = @JoinColumn(name = "workforce_id"))
	private List<Workforce> workforces;

	@ManyToMany(targetEntity = Material.class)
	@JoinTable(name = "resource_materials", joinColumns = @JoinColumn(name = "resource_id"),
			inverseJoinColumns = @JoinColumn(name = "material_id"))
	private List<Material> materials;

	public static Resource forCompilation(Compilation compilation, String code, String title,
										  Map<MeasureType, ResourceMeasureValue> measures, List<Machine> machines,
										  List<Workforce> workforces, List<Material> materials) {
		Resource resource = new Resource();
		resource.compilation = compilation;
		resource.code = code;
		resource.title = title;
		resource.measures = measures;
		resource.machines = machines;
		resource.workforces = workforces;
		resource.materials = materials;

		return resource;
	}

	public Resource() {
	}

	public Resource(Long id, String code, String title, Compilation compilation,
					Map<MeasureType, ResourceMeasureValue> measures, List<Machine> machines, List<Workforce> workforces,
					List<Material> materials) {
		this.id = id;
		this.code = Code.fromString(code).toString();
		this.title = title;
		this.compilation = compilation;
		this.measures = measures;
		this.machines = machines;
		this.workforces = workforces;
		this.materials = materials;
	}

	public Long getId() {
		return id;
	}

	public Code getCode() {
		return Code.fromString(code);
	}

	public String getTitle() {
		return title;
	}

	public Compilation getCompilation() {
		return compilation;
	}

	public Map<MeasureType, ResourceMeasureValue> getMeasures() {
		return measures;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public List<Workforce> getWorkforces() {
		return workforces;
	}

	public List<Material> getMaterials() {
		return materials;
	}
}
