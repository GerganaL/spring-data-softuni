package course.springdata.jsondemo.dao;

import course.springdata.jsondemo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
