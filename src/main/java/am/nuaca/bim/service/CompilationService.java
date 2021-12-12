package am.nuaca.bim.service;

import java.util.ArrayList;
import java.util.List;

import am.nuaca.bim.dto.CompilationCreationCommand;
import am.nuaca.bim.entity.Compilation;
import am.nuaca.bim.helper.Iterables;
import am.nuaca.bim.repository.CompilationRepository;
import org.springframework.stereotype.Service;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
@Service
public class CompilationService {

	private final CompilationRepository compilationRepository;

	public CompilationService(CompilationRepository compilationRepository) {
		this.compilationRepository = compilationRepository;
	}

	public List<Compilation> getAllCompilations() {
		return Iterables.iterableToList(compilationRepository.findAll());
	}

	public Integer createCompilation(CompilationCreationCommand compilationCreationCommand) {
		Compilation compilation = new Compilation(compilationCreationCommand.getTitle());
		compilation = compilationRepository.save(compilation);
		return compilation.getId();
	}

	public boolean updateCompilation(int compilationId, CompilationCreationCommand compilationCreationCommand) {
		if (!compilationRepository.existsById(compilationId)) {
			return false;
		}

		Compilation compilation = new Compilation(compilationId, compilationCreationCommand.getTitle());
		compilationRepository.save(compilation);

		return true;
	}

	public boolean deleteCompilation(int compilationId) {
		if (!compilationRepository.existsById(compilationId)) {
			return false;
		}
		compilationRepository.delete(new Compilation(compilationId));
		return true;
	}

	public List<Compilation> searchByName(String text) {
		text = text.replaceAll("[+\\-~*()<>\"]", "");

		List<String> modifiedWords = new ArrayList<>();

		String[] words = text.split(" ");

		for (String word : words) {
			int length = word.length();

			int lettersToDelete;
			if (length > 3 && length < 8) {
				lettersToDelete = 2;
			}
			else if (length >= 8) {
				lettersToDelete = 3;
			}
			else {
				lettersToDelete = 0;
			}

			String modifiedWord = "+" + word.substring(0, length - lettersToDelete) + '*';

			modifiedWords.add(modifiedWord);
		}

		String modifiedText = String.join(" ", modifiedWords);

		return compilationRepository.searchByTitlePattern(modifiedText);
	}

}
