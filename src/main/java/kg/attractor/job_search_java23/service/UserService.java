package kg.attractor.job_search_java23.service;

import kg.attractor.job_search_java23.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUserById(int id);

    void addUser(UserDto userDto);

    int createUserAndReturnId(UserDto userDto);
}