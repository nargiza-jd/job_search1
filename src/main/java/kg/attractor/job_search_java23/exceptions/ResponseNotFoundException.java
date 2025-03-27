package kg.attractor.job_search_java23.exceptions;

import java.util.NoSuchElementException;

public class ResponseNotFoundException extends NoSuchElementException {
    public ResponseNotFoundException() {
        super("Response not found");
    }
}
