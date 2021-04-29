package couurse.springdata.dao;

import couurse.springdata.entity.Ingredient;
import couurse.springdata.entity.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    List<Shampoo> findBySizeOrderById(String size);
}
