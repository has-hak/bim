package am.nuaca.bim.entity;

import javax.persistence.*;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
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
		return new Code("19", "100", "1005");
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
