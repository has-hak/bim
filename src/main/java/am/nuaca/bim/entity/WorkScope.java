package am.nuaca.bim.entity;

import javax.persistence.*;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
@Entity
@Table(name = "work_scope")
public class WorkScope {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
}
