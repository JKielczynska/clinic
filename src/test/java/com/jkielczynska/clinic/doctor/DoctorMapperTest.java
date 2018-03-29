package com.jkielczynska.clinic.doctor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DoctorMapperTest {
    private DoctorMapper mapper = new DoctorMapper();

    @Test
    public void testMapToDoctor() {
        //Given
        DoctorDto doctorDto = new DoctorDto(1L, "test_name", "test_lastname", new HashSet<>());
        //When
        Doctor resultDoc = mapper.mapToDoctor(doctorDto);
        //Then
        assertEquals(doctorDto.getDoctorId(), resultDoc.getDoctorId());
        assertEquals(doctorDto.getFirstName(), resultDoc.getFirstName());
        assertEquals(doctorDto.getLastName(), resultDoc.getLastName());
    }

    @Test
    public void testMapToDoctorDto() {
        //Given
        Doctor doctor = new Doctor(1L, "test_name", "test_lastname", new HashSet<>());
        //When
        DoctorDto resultDoc = mapper.mapToDoctorDto(doctor);
        //Then
        assertEquals(doctor.getDoctorId(), resultDoc.getDoctorId());
        assertEquals(doctor.getFirstName(), resultDoc.getFirstName());
        assertEquals(doctor.getLastName(), resultDoc.getLastName());
    }

    @Test
    public void testMapToDoctorDtoList() {
        //Given
        Doctor doctor1 = new Doctor(1L, "test_name_1", "test_lastname_1", new HashSet<>());
        Doctor doctor2 = new Doctor(2L, "test_name_2", "test_lastname_2", new HashSet<>());
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);
        //When
        List<DoctorDto> resultDocList = mapper.mapToDoctorDtoList(doctors);
        //Then
        assertEquals(doctors.size(), resultDocList.size());
        assertEquals(doctors.get(0).getFirstName(), resultDocList.get(0).getFirstName());
        assertEquals(doctors.get(0).getLastName(), resultDocList.get(0).getLastName());
        assertEquals(doctors.get(1).getFirstName(), resultDocList.get(1).getFirstName());
        assertEquals(doctors.get(1).getLastName(), resultDocList.get(1).getLastName());

    }
}