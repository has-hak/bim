package am.nuaca.bim.entity;

import javax.persistence.*;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
@Entity
@Table(name = "languages")
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
