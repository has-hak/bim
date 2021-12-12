package am.nuaca.bim.endpoint.api;

import java.util.Comparator;
import java.util.List;

import am.nuaca.bim.entity.Language;
import am.nuaca.bim.entity.Message;
import am.nuaca.bim.helper.Iterables;
import am.nuaca.bim.repository.LanguageRepository;
import am.nuaca.bim.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ani Khachatryan on 23-May-20.
 */

@RestController
@RequestMapping("/api/languages")
public class MessageLanguageController {

	private final LanguageRepository languageRepository;
	private final MessageRepository messageRepository;

	@Autowired
	public MessageLanguageController(LanguageRepository languageRepository, MessageRepository messageRepository) {
		this.languageRepository = languageRepository;
		this.messageRepository = messageRepository;
	}

	@GetMapping
	public List<Language> geLanguages() {
		List<Language> languages = Iterables.iterableToList(languageRepository.findAll());
		languages.sort(Comparator.comparing(Language::getId));
		return languages;
	}

	@GetMapping("/messages")
	public List<Message> getMessages() {
		return Iterables.iterableToList(messageRepository.findAll());
	}
}
