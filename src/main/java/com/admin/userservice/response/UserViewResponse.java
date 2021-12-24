package com.admin.userservice.response;

import com.admin.userservice.entity.User;
import com.admin.userservice.enumuration.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserViewResponse {
    private List<User> registeredUser;


}