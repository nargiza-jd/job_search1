package kg.attractor.job_search_java23.service.impl;

import kg.attractor.job_search_java23.dao.ResponseDao;
import kg.attractor.job_search_java23.dto.ResponseDto;
import kg.attractor.job_search_java23.exceptions.ResponseNotFoundException;
import kg.attractor.job_search_java23.model.Response;
import kg.attractor.job_search_java23.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final ResponseDao responseDao;

    @Override
    public List<ResponseDto> getAllResponses() {
        List<Response> responses = responseDao.getResponses();
        return responses.stream()
                .map(r -> ResponseDto.builder()
                        .id(r.getId())
                        .resumeId(r.getResumeId())
                        .vacancyId(r.getVacancyId())
                        .confirmation(r.isConfirmation())
                        .build())
                .toList();
    }

    @Override
    public ResponseDto getResponseById(String responseId) {
        int id = Integer.parseInt(responseId);
        Response response = responseDao.getResponseById(id)
                .orElseThrow(ResponseNotFoundException::new);

        return ResponseDto.builder()
                .id(response.getId())
                .resumeId(response.getResumeId())
                .vacancyId(response.getVacancyId())
                .confirmation(response.isConfirmation())
                .build();
    }

    @Override
    public void createResponse(ResponseDto responseDto) {
        Response response = Response.builder()
                .resumeId(responseDto.getResumeId())
                .vacancyId(responseDto.getVacancyId())
                .confirmation(responseDto.isConfirmation())
                .build();

        responseDao.save(response);
    }
}