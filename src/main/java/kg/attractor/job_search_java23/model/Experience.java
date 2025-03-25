package kg.attractor.job_search_java23.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    private String company;
    private String position;
    private String responsibilities;
    private int startYear;
    private int endYear;

    @Override
    public String toString() {
        return String.format("%s (%d - %d)", position, startYear, endYear);
    }
}