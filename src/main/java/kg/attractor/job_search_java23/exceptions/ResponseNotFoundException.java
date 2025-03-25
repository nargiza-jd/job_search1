package kg.attractor.job_search_java23.exceptions;

public class ResponseNotFoundException extends RuntimeException {
    public ResponseNotFoundException() {
        super("Response not found");
    }
}
