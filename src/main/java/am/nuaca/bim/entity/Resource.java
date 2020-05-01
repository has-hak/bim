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

	private String unit;

	private double unitCost;

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

	public Long getId() {
		return id;
	}

	public Code getCode() {
		return new Code("06", "01", "107-1");
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
