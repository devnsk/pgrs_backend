package org.pgrs.Exception;

public class DuplicateGrievanceException extends RuntimeException {
    public DuplicateGrievanceException(String message) {
        super(message);
    }
}