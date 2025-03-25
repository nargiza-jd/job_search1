package kg.attractor.job_search_java23.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private String institution;
    private String course;
    private int startYear;
    private int endYear;

    @Override
    public String toString() {
        return String.format("Учился в %s (%d - %d)", institution, startYear, endYear);
    }
}