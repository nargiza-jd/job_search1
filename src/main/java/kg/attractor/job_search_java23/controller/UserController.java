package kg.attractor.job_search_java23.controller;

import kg.attractor.job_search_java23.dto.UserDto;
import kg.attractor.job_search_java23.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public HttpStatus createUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return HttpStatus.CREATED;
    }

    @PostMapping("withId")
    public int createUserAndReturnId(@RequestBody UserDto userDto) {
        return userService.createUserAndReturnId(userDto);
    }
}