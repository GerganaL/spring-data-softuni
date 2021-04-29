package couurse.springdata.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;
    private  double price;

    @ToString.Exclude
    @ManyToMany(mappedBy = "ingredients")
    private Set<Shampoo> shampoos = new HashSet<>();
}
