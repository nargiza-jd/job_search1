package kg.attractor.job_search_java23.exceptions;

public class ResumeNotFoundException extends RuntimeException {
    public ResumeNotFoundException() {
        super("Resume not found");
    }
}