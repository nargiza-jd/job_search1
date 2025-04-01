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
                        .role(String.valueOf(u.getRoleId()))
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
                .role(String.valueOf(user.getRoleId()))
                .build();
    }

    @Override
    public void addUser(UserDto userDto) {
        setDefaultAvatarIfNeeded(userDto);
        userDao.create(toUser(userDto));
    }

    @Override
    public int createUserAndReturnId(UserDto userDto) {
        setDefaultAvatarIfNeeded(userDto);
        return userDao.createAndReturnId(toUser(userDto));
    }

    private void setDefaultAvatarIfNeeded(UserDto userDto) {
        if (userDto.getProfileImageUrl() == null || userDto.getProfileImageUrl().isEmpty()) {
            userDto.setProfileImageUrl("user_icon.jpg");
        }
    }

    private User toUser(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phone(userDto.getPhone())
                .profileImageUrl(userDto.getProfileImageUrl())
                .roleId(Integer.parseInt(userDto.getRole()))
                .build();
    }
}