package course.springdata.restmvc.init;

import course.springdata.restmvc.dao.PostRepository;
import course.springdata.restmvc.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private PostRepository postRepo;

    @Override
    public void run(String... args) throws Exception {

        if(postRepo.count() == 0) {
            List.of(new Post("New in Spring", "Spring Boot si fancy", "Gergana Lulcheva",
                            Set.of("spring", "java", "spring boot")),
                    new Post("Reactive Spring", "Web Flux is her", "Vanya Lulcheva",
                            Set.of("spring", "java", "reactor")),
                    new Post("Easy to Test", "Web test client", "Emil Giev",
                            Set.of("spring", "java", "web test client")))
                    .forEach(postRepo::save);

        }
    }
}
