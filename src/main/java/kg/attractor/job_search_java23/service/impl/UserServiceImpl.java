package kg.attractor.job_search_java23.service.impl;

import kg.attractor.job_search_java23.dao.UserDao;
import kg.attractor.job_search_java23.dto.UserDto;
import kg.attractor.job_search_java23.exceptions.UserNotFoundException;
import kg.attractor.job_search_java23.model.User;
import kg.attractor.job_search_java23.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userDao.getUsers();
        return users.stream()
                .map(u -> UserDto.builder()
                        .id(u.getId())
                        .username(u.getUsername())
                        .email(u.getEmail())
                        .phone(u.getPhone())
                        .profileImageUrl(u.getProfileImageUrl())
                        .role(u.getRole())
                        .build())
                .toList();
    }

    @Override
    public UserDto getUserById(int id) {
        User user = userDao.getUserById(id)
                .orElseThrow(UserNotFoundException::new);
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .profileImageUrl(user.getProfileImageUrl())
                .role(user.getRole())
                .build();
    }

    @Override
    public void addUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password("default") 
                .build();
        userDao.create(user);
    }

    @Override
    public int createUserAndReturnId(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password("default")
                .build();
        return userDao.createAndReturnId(user);
    }
}