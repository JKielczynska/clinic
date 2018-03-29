package com.jkielczynska.clinic.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class DoctorDto {
    private long doctorId;
    private String firstName;
    private String lastName;
    private Set<Specialization> specializations;
}
