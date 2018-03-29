package com.jkielczynska.clinic.doctor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {
    public Doctor mapToDoctor(final DoctorDto doctorDto) {
        return new Doctor(doctorDto.getDoctorId(),
                doctorDto.getFirstName(),
                doctorDto.getLastName(),
                doctorDto.getSpecializations());
    }

    public DoctorDto mapToDoctorDto(final Doctor doctor) {
        return new DoctorDto(doctor.getDoctorId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getSpecializations());
    }

    public List<DoctorDto> mapToDoctorDtoList(final List<Doctor> doctors) {
        return doctors.stream()
                .map(d -> new DoctorDto(d.getDoctorId(), d.getFirstName(),
                        d.getLastName(), d.getSpecializations()))
                .collect(Collectors.toList());
    }
}
