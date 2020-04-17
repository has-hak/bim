package am.nuaca.bim.entity;

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

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public String getUnit() {
		return unit;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public Compilation getCompilation() {
		return compilation;
	}

	public Map<MeasureType, ResourceMeasureValue> getMeasures() {
		return measures;
	}
}
