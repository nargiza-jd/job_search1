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
                        .applicantId(r.getApplicantId())
                        .employerId(r.getEmployerId())
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
                .applicantId(response.getApplicantId())
                .employerId(response.getEmployerId())
                .build();
    }

    @Override
    public void createResponse(ResponseDto responseDto) {
        List<Response> responses = responseDao.getResponses();
        responses.add(Response.builder()
                .id(responses.size() + 1)
                .resumeId(responseDto.getResumeId())
                .vacancyId(responseDto.getVacancyId())
                .applicantId(responseDto.getApplicantId())
                .employerId(responseDto.getEmployerId())
                .build());
    }
}