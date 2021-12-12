package am.nuaca.bim.entity;

import javax.persistence.*;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
@Entity
@Table(name = "compilations")
public class Compilation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;

	public Compilation() {
	}

	public Compilation(Integer id) {
		this.id = id;
	}

	public Compilation(Integer id, String title) {
		this.id = id;
		this.title = title;
	}

	public Compilation(String title) {
		this.title = title;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "Compilation{" + "id=" + id + ", title='" + title + '\'' + '}';
	}
}
