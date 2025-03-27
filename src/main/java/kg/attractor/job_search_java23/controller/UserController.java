package kg.attractor.job_search_java23.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search_java23.dto.UserDto;
import kg.attractor.job_search_java23.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    public HttpStatus createUser(@RequestBody @Valid UserDto userDto) {
        userService.addUser(userDto);
        return HttpStatus.CREATED;
    }

    @PostMapping("withId")
    public int createUserAndReturnId(@RequestBody @Valid UserDto userDto) {
        return userService.createUserAndReturnId(userDto);
    }

//    @ExceptionHandler(NoSuchElementException.class)
//    private ErrorResponse handleNoSuchElementException(NoSuchElementException e) {
//        return ErrorResponse.builder(e, HttpStatus.NO_CONTENT, e.getMessage()).build();
//    }
}