package com.admin.userservice.service;

import com.admin.userservice.dto.UserDTO;
import com.admin.userservice.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserService {

    Optional<User>findByEmail(String email);

    public Long save(UserDTO userDTO);


    //public Long save(User user);

    List<User> viewUser();

    List<User>searchUser(String email,String country,String state,String aadhaar_card,String pan_card);

    User updateUser(String email,User user);

    void deleteUser(String email);


}
