package com.jkielczynska.clinic.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {
    @Autowired
    private DoctorService service;
    @Autowired
    private DoctorMapper doctorMapper;

    @GetMapping
    public List<DoctorDto> getAllDoctors() {
        return doctorMapper.mapToDoctorDtoList(service.getAllDoctors());
    }

    @GetMapping(value = "{doctorId}")
    public DoctorDto getDoctor(@PathVariable final Long doctorId) {
        return doctorMapper.mapToDoctorDto(service.getDoctorById(doctorId));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createDoctor(@RequestBody final DoctorDto doctorDto) {
        service.saveDoctor(doctorMapper.mapToDoctor(doctorDto));
    }

    @PutMapping(value = "{doctorId}", consumes = APPLICATION_JSON_VALUE)
    public DoctorDto updateDoctor(@RequestBody final DoctorDto doctorDto, @PathVariable final Long doctorId) {
        doctorDto.setDoctorId(doctorId);
        return doctorMapper.mapToDoctorDto(service.saveDoctor(doctorMapper.mapToDoctor(doctorDto)));
    }

    @DeleteMapping(value = "{doctorId}")
    public void deleteDoctor(@PathVariable final Long doctorId) {
        service.deleteDoctor(doctorId);
    }

}
