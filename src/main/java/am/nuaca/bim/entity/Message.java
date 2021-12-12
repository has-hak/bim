package am.nuaca.bim.entity;

import java.util.Map;
import javax.persistence.*;

import org.hibernate.annotations.Type;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
@Entity
@Table(name = "messages")
public class Message {

	@Id
	private String id;

	@Type(type = "json")
	@Column(columnDefinition = "json")
	@Convert(disableConversion = true)
	private Map<Integer, String> values;

	public String getId() {
		return id;
	}

	public Map<Integer, String> getValues() {
		return values;
	}
}
