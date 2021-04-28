package course.springdata.springintroexercise.services;

import course.springdata.springintroexercise.entities.Category;

import java.io.IOException;

public interface CategoryService{
    void seedCategories() throws IOException;
    Category getCategoryById(Long id);
}
