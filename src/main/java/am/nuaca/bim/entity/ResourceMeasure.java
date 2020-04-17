package am.nuaca.bim.entity;

import javax.persistence.*;

import am.nuaca.bim.application.model.Unit;

/**
 * @author Tigran Sargsyan on 16-Apr-20.
 */
@Entity
@Table(name = "resource_measures")
public class ResourceMeasure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private Unit unit;

	private double from;

	private double to;

	@ManyToOne
	private Resource resource;
}
