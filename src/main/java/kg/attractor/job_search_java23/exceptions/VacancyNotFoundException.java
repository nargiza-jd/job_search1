package kg.attractor.job_search_java23.exceptions;

import java.util.NoSuchElementException;

public class VacancyNotFoundException extends NoSuchElementException {
    public VacancyNotFoundException() {
        super("Vacancy not found");
    }
}