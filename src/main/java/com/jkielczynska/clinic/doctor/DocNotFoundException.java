package com.jkielczynska.clinic.doctor;

public class DocNotFoundException extends RuntimeException {
    public DocNotFoundException(final Long doctorId) {
        super("Doctor with id:" + doctorId + "not found!");
    }
}
