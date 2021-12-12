package am.nuaca.bim.service;

import am.nuaca.bim.entity.Material;
import am.nuaca.bim.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialsService {

    private MaterialRepository materialRepository;

    public MaterialsService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> searchByName(String text) {
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

        return materialRepository.searchByTitlePattern(modifiedText);
    }

}
