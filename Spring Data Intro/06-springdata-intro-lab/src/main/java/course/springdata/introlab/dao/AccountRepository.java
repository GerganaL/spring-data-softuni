package course.springdata.introlab.dao;

import course.springdata.introlab.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
