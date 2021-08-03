package com.senlacourses.electronicHotelAdministrator.domain.service;

import com.senlacourses.electronicHotelAdministrator.dao.interfaces.IUserDao;
import com.senlacourses.electronicHotelAdministrator.domain.dto.request.UserDto;
import com.senlacourses.electronicHotelAdministrator.domain.model.Role;
import com.senlacourses.electronicHotelAdministrator.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class UserDetailsServiceImplTest {

  @Mock
  private IUserDao userDao;
  @Mock
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  @InjectMocks
  private UserDetailsServiceImpl userDetailsService;

  @Test
  void getAllUsers() {
    assertThat(userDetailsService.getAllUsers(), is(new ArrayList<User>()));
  }

  @Test
  void addNewUser() {
    String username = "user";
    String password = "123";
    UserDto userDto = new UserDto(username, password);

    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));

    when(bCryptPasswordEncoder.encode(any(String.class))).thenReturn(password);

    userDetailsService.addNewUser(userDto);

    ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
    verify(userDao).create(captor.capture());

    assertThat(captor.getValue(), is(user));
  }

  @Test
  void addNewUserShouldThrowIllArgExc() {
    UserDto userDto = new UserDto("user", "123");

    when(userDao.read(any(String.class))).thenReturn(new User());

    assertThrows(IllegalArgumentException.class, () ->
            userDetailsService.addNewUser(userDto));
  }

  @Test
  void removeUser() {
    String username = "user";

    when(userDao.read(any(String.class)))
            .thenReturn(new User())
            .thenReturn(null);

    assertTrue(userDetailsService.removeUser(username));
  }

  @Test
  void removeUserShouldThrowIllArgExc() {
    String username = "user";

    assertThrows(UsernameNotFoundException.class, () ->
            userDetailsService.removeUser(username));
  }

  @Test
  void loadUserByUsername() {
    String username = "user";
    User user = new User();
    user.setUsername(username);

    when(userDao.read(any(String.class)))
            .thenReturn(user);

    assertThat(userDetailsService.loadUserByUsername(username), is(user));
  }

  @Test
  void loadUserByUsernameShouldThrowIllArgExc() {
    String username = "user";

    assertThrows(UsernameNotFoundException.class, () ->
            userDetailsService.loadUserByUsername(username));
  }

  @Test
  void findByLoginAndPassword() {
    String username = "user";
    String password = "password";
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);

    when(userDao.read(any(String.class))).thenReturn(user);
    when(bCryptPasswordEncoder.matches(any(String.class), any(String.class)))
            .thenReturn(true)
            .thenReturn(false);

    assertThat(userDetailsService.findByLoginAndPassword(username, password),
            is(user));
    assertThat(userDetailsService.findByLoginAndPassword(username, password),
            nullValue());
  }
}