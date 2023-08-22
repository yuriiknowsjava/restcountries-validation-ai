package edu.yuriiknowsjava.restcountriesvalidationai.exceptions;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException() {
        super("Country service in not available at the moment.");
    }
}
