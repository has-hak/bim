package am.nuaca.bim.entity;

import javax.persistence.*;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
@Entity
@Table(name = "machines")
public class Machine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String code;

	private String title;

	private double unit;

	private double unitCost;

	public Machine() {
	}

	public Machine(Long id, String code, String title, double unit, double unitCost) {
		this.id = id;
		this.code = code;
		this.title = title;
		this.unit = unit;
		this.unitCost = unitCost;
	}

	public Machine(String code, String title, double unit, double unitCost) {
		this.code = code;
		this.title = title;
		this.unit = unit;
		this.unitCost = unitCost;
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
}
