package com.admin.userservice.service.Impl;

import com.admin.userservice.constant.Constants;
import com.admin.userservice.dto.UserDTO;
import com.admin.userservice.entity.User;
import com.admin.userservice.enumuration.UserType;
import com.admin.userservice.exception.UserAlreadyExistException;
import com.admin.userservice.repository.UserRepository;
import com.admin.userservice.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class IUserServiceImpl implements IUserService, UserDetailsService {



    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Optional<User> findByEmail(String email) {

        log.info("inside findByEmail() of IUserServiceImpl");

        return userRepository.findByEmail(email);
    }

    @Override
    public Long save(UserDTO userDTO) {

        Optional<User>opt=findByEmail(userDTO.getEmail());

        if(!opt.isPresent()){
            throw new UsernameNotFoundException(Constants.USER_NOT_FOUND);
        }
        log.info("inside registerNewUserAccount() of IUserServiceImpl()");
        String password=passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(password);

        String confirmPassword=passwordEncoder.encode(userDTO.getConfirmPassword());
        userDTO.setConfirmPassword(confirmPassword);

        User user=new User();
        user.setUserType(UserType.Admin);

        mapper.map(userDTO,user);
        return userRepository.save(user).getId();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("inside loadUserByUsername() of IUserServiceImpl");
        Optional<User>opt=findByEmail(username);

        if(!opt.isPresent()){
            throw new UsernameNotFoundException(Constants.USER_NOT_FOUND);
        }
        User user= opt.get();



        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                user.getRoles().stream()
                        .map(role->new SimpleGrantedAuthority("role "+role.getName()))
                        .collect(Collectors.toList())
        );
    }

   /* @Override
    public Long save(User user) {

        log.info("inside save() of IUserServiceImpl");

        Optional<User>opt=findByEmail(user.getEmail());
        if(opt.isPresent()) {
            throw new UserAlreadyExistException("user already exists " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        user.setUserType(UserType.Admin);
        return userRepository.save(user).getId();
    }*/

    @Override
    public List<User> viewUser() {
        log.info("inside viewUser() of IUserServiceImpl");
        return userRepository.findAll();
    }


    @Override
    public List<User> searchUser(String email,String country, String state,String aadhaar_card,String pan_card) {
        log.info("inside searchUser() of IUserServiceImpl");
        return userRepository.searchUser(email,country,state,aadhaar_card,pan_card);
    }

    @Override
    public User updateUser(String email,User user) {
        log.info("inside updateUser() of IUserServiceImpl");
        Optional<User> u= userRepository.findByEmail(email);
        if(!u.isPresent()){
            throw new UsernameNotFoundException("user not found");
        }
        User opt=u.get();
        opt.setEmail(user.getEmail());
        return userRepository.save(opt);
    }

    @Override
    public void deleteUser(String email) {
        log.info("inside deleteUser() of IUserServiceImpl");
        Optional<User>u=findByEmail(email);
        if(!u.isPresent()){
            throw new UsernameNotFoundException("user not found exception");
        }
        User user=u.get();
        userRepository.delete(user);
    }


}
