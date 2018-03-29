package com.jkielczynska.clinic.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository repository;

    public List<Doctor> getAllDoctors() {
        return repository.findAll();
    }

    public Doctor getDoctorById(final Long doctorId) {
        return repository.findById(doctorId).orElseThrow(() -> new DocNotFoundException(doctorId));
    }

    public Doctor saveDoctor(final Doctor doctor) {
        return repository.save(doctor);
    }

    public void deleteDoctor(final Long doctorId) {
        repository.deleteById(doctorId);
    }
}
