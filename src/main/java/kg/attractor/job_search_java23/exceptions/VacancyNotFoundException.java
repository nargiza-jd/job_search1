package kg.attractor.job_search_java23.exceptions;

public class VacancyNotFoundException extends RuntimeException {
    public VacancyNotFoundException() {
        super("Vacancy not found");
    }
}