package com.senlaCourses.adPlacementSystem.domain.service.interfaces;

import com.senlaCourses.adPlacementSystem.domain.dto.request.UserDto;
import com.senlaCourses.adPlacementSystem.domain.dto.request.UserDtoForChangingThePassword;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Interface for UserService realization.
 */
public interface IUserService {

  void addNewUser(UserDto userDto);

  User changePassword(UserDtoForChangingThePassword userDtoForChangingThePassword);

  User changeEmail(String newEmail);

  boolean removeUser(long id) throws UsernameNotFoundException;

  User findByLoginAndPassword(String username, String password);
}
