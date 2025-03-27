package kg.attractor.job_search_java23.exceptions;

import java.util.NoSuchElementException;

public class ResumeNotFoundException extends NoSuchElementException {
    public ResumeNotFoundException() {
        super("Resume not found");
    }
}