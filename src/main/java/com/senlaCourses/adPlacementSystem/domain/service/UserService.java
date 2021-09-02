package com.senlaCourses.adPlacementSystem.domain.service;

import com.senlaCourses.adPlacementSystem.dao.interfaces.IUserDao;
import com.senlaCourses.adPlacementSystem.domain.dto.request.UserDto;
import com.senlaCourses.adPlacementSystem.domain.dto.request.UserDtoForChangingThePassword;
import com.senlaCourses.adPlacementSystem.domain.model.Role;
import com.senlaCourses.adPlacementSystem.domain.model.User;
import com.senlaCourses.adPlacementSystem.domain.service.interfaces.IUserService;
import com.senlaCourses.adPlacementSystem.exceptions.EntityAlreadyExistException;
import com.senlaCourses.adPlacementSystem.exceptions.EntityNotFoundException;
import java.util.Collections;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Class-Service for working with users.
 */
@Slf4j
@AllArgsConstructor
@Service
public class UserService implements IUserService, UserDetailsService {

  private final IUserDao userDao;
  private final BCryptPasswordEncoder encoder;

  /**
   * Adds new user to DB.
   *
   * @param userDto user data for adding.
   * @throws EntityAlreadyExistException if user already have ad with this name.
   */
  @Transactional
  @Override
  public void addNewUser(UserDto userDto) throws EntityAlreadyExistException {
    User user = userDao.readByNaturalId(userDto.getUsername());
    if (user != null) {
      log.error("EntityAlreadyExistException(\"this user already exist\")");
      throw new EntityAlreadyExistException("this user already exist");
    }

    user = new User();
    user.setUsername(userDto.getUsername());
    user.setEmail(userDto.getEmail());
    user.setPassword(encoder.encode(userDto.getPassword()));
    user.getRoles().add(new Role(1, "ROLE_USER"));

    userDao.create(user);
  }

  /**
   * Changes password of current user.
   *
   * @param userDtoForChangingThePassword user data for change the password.
   * @return changed User.class object.
   */
  @Transactional
  @Override
  public User changePassword(UserDtoForChangingThePassword userDtoForChangingThePassword) {
    User user = getCurrentUser();

    if (!encoder.matches(userDtoForChangingThePassword.getCurrentPassword(), user.getPassword())) {
      throw new IllegalArgumentException("Incorrect password");
    }

    user.setPassword(encoder.encode(userDtoForChangingThePassword.getNewPassword()));
    userDao.update(user);
    return user;
  }

  /**
   * Changes Email of current user.
   *
   * @param newEmail parameter of User.class object for change.
   * @return changed User.class object.
   */
  @Transactional
  @Override
  public User changeEmail(String newEmail) {
    User user = getCurrentUser();

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
   * @throws EntityNotFoundException if user not found.
   */
  @Transactional
  @Override
  public boolean removeUser(long id) throws EntityNotFoundException {
    User user = userDao.read(id);

    if (user == null) {
      log.error("EntityNotFoundException(\"User not found\")");
      throw new EntityNotFoundException("User not found");
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
  @Transactional
  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDao.readByNaturalId(username);

    if (user == null) {
      log.error("UsernameNotFoundException(\"User not found\")");
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
  @Transactional
  @Override
  public User findByLoginAndPassword(String username, String password) {
    User user = loadUserByUsername(username);

    if (!encoder.matches(password, user.getPassword())) {
      log.error("IllegalArgumentException(\"Password mismatch\")");
      throw new IllegalArgumentException("Password mismatch");
    }
    return user;
  }

  private User getCurrentUser() {
    UserDetails userDetails = (UserDetails) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();
    return userDao.readByNaturalId(userDetails.getUsername());
  }
}
