package couurse.springdata.init;

import couurse.springdata.dao.IngredientRepository;
import couurse.springdata.dao.LabelRepository;
import couurse.springdata.dao.ShampooRepository;
import couurse.springdata.util.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static couurse.springdata.entity.Size.MEDIUM;

@Component
public class AppInitializer implements CommandLineRunner {
    private final ShampooRepository shampooRepo;
    private final LabelRepository labelRepo;
    private final IngredientRepository ingredientRepo;

    @Autowired
    public AppInitializer(ShampooRepository shampooRepo, LabelRepository labelRepo, IngredientRepository ingredientRepo) {
        this.shampooRepo = shampooRepo;
        this.labelRepo = labelRepo;
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // Fetch Shampoos by Size
        shampooRepo.findBySizeOrderById(MEDIUM).forEach(PrintUtil::printShampoo);
//        System.out.println("-".repeat(170) + "\n");
    }
}
