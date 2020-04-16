package am.nuaca.bim.entity;

import javax.persistence.*;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@Entity
@Table(name = "compilations")
public class Compilation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	public Compilation() {
	}

	public Compilation(Long id) {
		this.id = id;
	}

	public Compilation(Long id, String title) {
		this.id = id;
		this.title = title;
	}

	public Compilation(String title) {
		this.title = title;
	}

	public Long getId() {
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
