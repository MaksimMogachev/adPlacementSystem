package com.senlaCourses.adPlacementSystem.domain.service;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IUserDao;
import com.senlaCourses.adPlacementSystem.domain.dto.request.UserDto;
import com.senlaCourses.adPlacementSystem.domain.dto.request.UserDtoForChangingThePassword;
import com.senlaCourses.adPlacementSystem.domain.model.Role;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IUserService;
import java.util.Collections;
import javax.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Class-Service for working with users.
 */
public class UserService implements IUserService, UserDetailsService {

  private final IUserDao userDao;
  private final BCryptPasswordEncoder encoder;

  public UserService(IUserDao userDao, BCryptPasswordEncoder encoder) {
    this.userDao = userDao;
    this.encoder = encoder;
  }

  /**
   * Adds new user to DB.
   *
   * @param userDto user data for adding.
   */
  @Transactional
  @Override
  public void addNewUser(UserDto userDto) {
    User user = userDao.read(userDto.getUsername());
    if (user != null) {
      throw new IllegalArgumentException("this user already exist");
    }

    user = new User();
    user.setUsername(userDto.getUsername());
    user.setPassword(encoder.encode(userDto.getPassword()));
    user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));

    userDao.create(user);
  }

  /**
   * Changes password of current user.
   *
   * @param userDtoForChangingThePassword user data for change the password.
   * @return changed User.class object.
   */
  @Override
  public User changePassword(UserDtoForChangingThePassword userDtoForChangingThePassword) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (user == null) {
      throw new NullPointerException("User not logged in");
    }

    if (encoder.matches(userDtoForChangingThePassword.getCurrentPassword(), user.getPassword())) {
      user.setPassword(encoder.encode(userDtoForChangingThePassword.getNewPassword()));
      userDao.update(user);

      return user;
    }
    return null;
  }

  /**
   * Changes Email of current user.
   *
   * @param newEmail parameter of User.class object for change.
   * @return changed User.class object.
   */
  @Override
  public User changeEmail(String newEmail) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (user == null) {
      throw new NullPointerException("User not logged in");
    }

    user.setEmail(newEmail);
    userDao.update(user);
    return user;
  }

  /**
   * Removes user from DB.
   * Method for admins.
   *
   * @param id parameter of User.class object for search.
   * @return user was deleted or not.
   * @throws UsernameNotFoundException if user not found.
   */
  @Transactional
  @Override
  public boolean removeUser(long id) throws UsernameNotFoundException {
    User user = userDao.read(id);

    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    userDao.delete(user);
    return userDao.read(id) == null;
  }

  /**
   * Loads user by username.
   *
   * @param username parameter of User.class object for search.
   * @return found user.
   * @throws UsernameNotFoundException if user not found.
   */
  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDao.read(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return user;
  }

  /**
   * Finds user by login and password.
   *
   * @param username parameter of User.class object for search.
   * @param password parameter of User.class object for search.
   * @return founded user.
   */
  @Override
  public User findByLoginAndPassword(String username, String password) {
    User user = loadUserByUsername(username);

    if (encoder.matches(password, user.getPassword())) {
      return user;
    }
    return null;
  }
}
