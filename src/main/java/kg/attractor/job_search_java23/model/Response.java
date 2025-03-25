package kg.attractor.job_search_java23.model;

import lombok.*;

@Getter
@Setter
public class Response {
    private int id;
    private int resumeId;
    private int vacancyId;
    private int applicantId;
    private int employerId;

    @Override
    public String toString() {
        return String.format("Отклик на вакансию #%d от соискателя #%d", vacancyId, applicantId);
    }
}