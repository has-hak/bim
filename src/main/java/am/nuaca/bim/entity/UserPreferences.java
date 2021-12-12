package am.nuaca.bim.entity;

import javax.persistence.*;

/**
 * @author Ani Khachatryan on 01-Jun-20.
 */
@Entity
@Table(name = "user_preferences")
public class UserPreferences {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private User user;

	@OneToOne
	private Language language;

	public UserPreferences() {
	}

	public UserPreferences(User user, Language language) {
		this.user = user;
		this.language = language;
	}

	public Long getId() {
		return id;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Language getLanguage() {
		return language;
	}
}
