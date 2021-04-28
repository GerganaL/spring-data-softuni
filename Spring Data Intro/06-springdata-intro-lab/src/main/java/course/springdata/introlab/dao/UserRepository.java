package course.springdata.introlab.dao;

import course.springdata.introlab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
