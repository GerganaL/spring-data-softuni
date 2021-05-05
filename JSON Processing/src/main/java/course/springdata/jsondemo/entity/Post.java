package course.springdata.jsondemo.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    @NotNull
    @Length(min = 3, max = 80, message = "Title must be at least 3 symbols.")
    private String title;

    @NonNull
    @NotNull
    @Length(min = 3, max = 2048)
    private String contents;

    @NonNull
    @NotNull
    @URL
    private String imageUrl;

    @NonNull
    @NotNull
    @Length(min = 3, max = 80, message = "Author must be at least 3 symbols.")
    private String author;

    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = LocalDateTime.now();
}
