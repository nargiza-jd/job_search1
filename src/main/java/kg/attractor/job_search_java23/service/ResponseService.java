package kg.attractor.job_search_java23.service;

import kg.attractor.job_search_java23.dto.ResponseDto;

import java.util.List;

public interface ResponseService {
    List<ResponseDto> getAllResponses();

    ResponseDto getResponseById(String responseId);

    void createResponse(ResponseDto responseDto);
}