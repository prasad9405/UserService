package com.admin.userservice.repository;

import com.admin.userservice.entity.Role;
import com.admin.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User>findByEmail(String email);

    @Query(value = "select * from user u where u.email=?1 or u.country=?2 or u.state=?3 or u.aadhaar=?4 or u.pan_card=?5" ,nativeQuery = true)
    List<User>searchUser(String email,String country,String state,String aadhaar,String pan_card);


   /* @Modifying
    @Transactional
    @Query(value = "delete from user u where u.email=?1",nativeQuery = true)
    Long deleteUser(String email); */



}
