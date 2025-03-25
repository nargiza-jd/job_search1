package kg.attractor.job_search_java23.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private Integer id;
    private Integer resumeId;
    private Integer vacancyId;
    private Integer applicantId;
    private Integer employerId;
}