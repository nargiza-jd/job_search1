package kg.attractor.job_search_java23.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search_java23.dto.ResponseDto;
import kg.attractor.job_search_java23.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("responses")
@RequiredArgsConstructor
public class ResponseController {

    private final ResponseService responseService;

    @GetMapping
    public List<ResponseDto> getAllResponses() {
        return responseService.getAllResponses();
    }

    @PostMapping
    public HttpStatus createResponse(@RequestBody @Valid ResponseDto responseDto) {
        responseService.createResponse(responseDto);
        return HttpStatus.CREATED;
    }
}