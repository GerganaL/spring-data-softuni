package course.springdata.introlab.service.impl;

import course.springdata.introlab.dao.UserRepository;
import course.springdata.introlab.entity.User;
import course.springdata.introlab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepo;

    //dependency injection
    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User register(User user) {
        return userRepo.save(user);
    }
}
