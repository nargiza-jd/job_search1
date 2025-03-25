package kg.attractor.job_search_java23.service;

import kg.attractor.job_search_java23.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(String userId);

    void createUser(UserDto userDto);
}