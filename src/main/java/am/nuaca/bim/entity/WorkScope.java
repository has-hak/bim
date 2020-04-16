package am.nuaca.bim.entity;

import javax.persistence.*;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@Entity
@Table(name = "work_scope")
public class WorkScope {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
}
