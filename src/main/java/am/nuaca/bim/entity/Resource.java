package am.nuaca.bim.entity;

import javax.persistence.*;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@Entity
@Table(name = "resources")
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String code;

	private String title;

	private String unit;

	private double unitCost;

	@ManyToOne(cascade = CascadeType.REMOVE)
	private Compilation compilation;
}
