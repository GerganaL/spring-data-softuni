package couurse.springdata.init;

import couurse.springdata.dao.IngredientRepository;
import couurse.springdata.dao.LabelRepository;
import couurse.springdata.dao.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements CommandLineRunner {
    private final ShampooRepository shampooRepos;
    private final IngredientRepository ingredientRepo;
    private final LabelRepository labelRepo;

    @Autowired
    public AppInitializer(ShampooRepository shampooRepos, IngredientRepository ingredientRepo, LabelRepository labelRepo) {
        this.shampooRepos = shampooRepos;
        this.ingredientRepo = ingredientRepo;
        this.labelRepo = labelRepo;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
