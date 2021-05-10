package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.workshop.data.entities.User;
import softuni.workshop.data.repositories.RoleRepository;
import softuni.workshop.data.repositories.UserRepository;
import softuni.workshop.service.models.UserServiceModel;
import softuni.workshop.service.services.RoleService;
import softuni.workshop.service.services.UserService;
import softuni.workshop.web.models.UserRegisterModel;

import java.util.HashSet;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public UserServiceModel registerUser(UserRegisterModel userRegisterModel) {
        User user = this.modelMapper.map(userRegisterModel,User.class);

        if(this.userRepository.count() == 0){
            this.roleService.seedRolesInDb();
           user.setAuthorities(new HashSet<>(this.roleRepository.findAll()));
        }else {
            user.setAuthorities(new HashSet<>());
            user.getAuthorities().add(this.roleRepository.findByAuthority("USER"));
        }
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        return this.modelMapper.map(this.userRepository.saveAndFlush(user),UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }
}
