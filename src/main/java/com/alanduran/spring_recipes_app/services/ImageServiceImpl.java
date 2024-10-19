package com.alanduran.spring_recipes_app.services;

import com.alanduran.spring_recipes_app.domain.Recipe;
import com.alanduran.spring_recipes_app.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long id, MultipartFile file) {
        log.debug("Saving image file");

        try {
            Recipe recipe = recipeRepository.findById(id).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("Could't save image file", e);
            throw new RuntimeException(e);
        }
    }
}
