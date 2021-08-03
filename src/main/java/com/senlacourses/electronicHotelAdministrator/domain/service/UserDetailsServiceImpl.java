package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IUserDao;
import com.senlacourses.electronicHotelAdministrator.domain.dto.request.UserDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.Role;
import com.senlacourses.electronicHotelAdministrator.domain.model.User;
import com.senlacourses.electronicHotelAdministrator.domain.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements IUserService, UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);
  private final IUserDao userDao;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserDetailsServiceImpl(IUserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public List<User> getAllUsers() {
    return userDao.getAll();
  }

  @Transactional
  @Override
  public void addNewUser(UserDto userDto) {
    User user = userDao.read(userDto.getUsername());

    if (user != null) {
      logger.error("IllegalArgumentException(\"this user already exist\")");
      throw new IllegalArgumentException("this user already exist");
    }

    user = new User();
    user.setUsername(userDto.getUsername());
    user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));

    userDao.create(user);
  }

  @Transactional
  @Override
  public boolean removeUser(String username) throws UsernameNotFoundException {
    User user = userDao.read(username);

    if (user == null) {
      logger.error("UsernameNotFoundException(\"User not found\")");
      throw new UsernameNotFoundException("User not found");
    }

    userDao.delete(user);
    return userDao.read(user) == null;
  }

  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDao.read(username);

    if (user == null) {
      logger.error("UsernameNotFoundException(\"User not found\")");
      throw new UsernameNotFoundException("User not found");
    }

    return user;
  }

  @Override
  public User findByLoginAndPassword(String username, String password) {
    User user = loadUserByUsername(username);

    if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
      return user;
    }

    return null;
  }
}
