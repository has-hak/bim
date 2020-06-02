package am.nuaca.bim.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import am.nuaca.bim.application.model.MeasureType;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@Entity
@Table(name = "materials")
public class Material {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String code;

	private String title;

	private double unit;

	private double unitCost;

	@Enumerated(EnumType.STRING)
	private MeasureType measureType;

	public Material() {
	}

	public Material(Long id, String code, String title, double unit, double unitCost, MeasureType measureType) {
		this.id = id;
		this.code = code;
		this.title = title;
		this.unit = unit;
		this.unitCost = unitCost;
		this.measureType = measureType;
	}

	public Material(String code, String title, double unit, double unitCost, MeasureType measureType) {
		this.code = code;
		this.title = title;
		this.unit = unit;
		this.unitCost = unitCost;
		this.measureType = measureType;
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
