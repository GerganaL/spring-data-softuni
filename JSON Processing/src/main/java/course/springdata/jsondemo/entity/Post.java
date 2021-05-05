package course.springdata.jsondemo.entity;

import com.google.gson.annotations.Expose;
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

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Expose
    @NonNull
    @NotNull
    @Length(min = 3, max = 80, message = "Title must be at least 3 symbols.")
    private String title;

    @Expose
    @NonNull
    @NotNull
    @Length(min = 3, max = 2048)
    private String contents;

    @Expose
    @NonNull
    @NotNull
    @URL
    private String imageUrl;

    @Expose
    @NonNull
    @NotNull
    @Length(min = 3, max = 80, message = "Author must be at least 3 symbols.")
    private String author;

   // @Expose
    private LocalDateTime created = LocalDateTime.now();

  //  @Expose
    private LocalDateTime modified = LocalDateTime.now();
}
