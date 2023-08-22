package edu.yuriiknowsjava.restcountriesvalidationai.exceptions;

public class InternalServerException extends RuntimeException {
    public InternalServerException() {
        super("Internal service error. Please, wait until be resolve temporary issues.");
    }
}
