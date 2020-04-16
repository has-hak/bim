package am.nuaca.bim.entity;

import java.util.List;
import javax.persistence.*;

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

	private String unit;

	private double unitVolume;

	private double unitCost;

	@ManyToMany
	@JoinTable(name = "materials_compilations")
	private List<Compilation> compilation;
}
