package com.jkielczynska.clinic.doctor;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    @Override
    List<Doctor> findAll();
}
