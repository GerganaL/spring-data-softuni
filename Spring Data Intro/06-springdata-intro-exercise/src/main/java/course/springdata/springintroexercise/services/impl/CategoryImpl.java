package course.springdata.springintroexercise.services.impl;

import course.springdata.springintroexercise.constants.GlobalConstants;
import course.springdata.springintroexercise.entities.Category;
import course.springdata.springintroexercise.repositories.CategoryRepository;
import course.springdata.springintroexercise.services.CategoryService;
import course.springdata.springintroexercise.utils.FileUtil;
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
