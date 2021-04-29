package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Category;
import com.softuni.springintroex.repositories.CategoryRepository;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class CategoryImpl implements CategoryService {

    private final CategoryRepository categoryRepo;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryImpl(CategoryRepository categoryRepo, FileUtil fileUtil) {
        this.categoryRepo = categoryRepo;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        if(this.categoryRepo.count() != 0){
            return;
        }
        String [] fileContent = this.fileUtil.readFileContent(GlobalConstants.CATEGORIES_FILE_PATH);

        Arrays.stream(fileContent).forEach(r -> {
            Category category = new Category(r);
            this.categoryRepo.saveAndFlush(category);
        });

    }

    @Override
    public Category getCategoryById(Long id) {
        return this.categoryRepo.getOne(id);
    }
}
