package kg.attractor.job_search_java23.dto;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "resumeId не должен быть пустым")
    private Integer resumeId;

    @NotNull(message = "vacancyId не должен быть пустым")
    private Integer vacancyId;

    private Integer applicantId;
    private Integer employerId;
}