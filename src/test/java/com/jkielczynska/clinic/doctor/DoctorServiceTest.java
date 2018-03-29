package com.jkielczynska.clinic.doctor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DoctorServiceTest {
    @InjectMocks
    private DoctorService service;
    @Mock
    private DoctorRepository repository;

    @Test
    public void shouldGetAllDoctors() {
        //Given
        Doctor doctor = new Doctor(1L, "test_name", "test_lastname", new HashSet<>());
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        when(repository.findAll()).thenReturn(doctors);
        //When
        List<Doctor> doctorList = service.getAllDoctors();
        //Then
        assertEquals(1, doctorList.size());
        assertEquals("test_name", doctorList.get(0).getFirstName());
        assertEquals("test_lastname", doctorList.get(0).getLastName());
    }

    @Test
    public void shouldGetDoctorById() {
        //Given
        Doctor doctor = new Doctor(1L, "test_name", "test_lastname", new HashSet<>());
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(doctor));
        //When
        Doctor resultDoc = service.getDoctorById(doctor.getDoctorId());
        //Then
        assertEquals(1L, resultDoc.getDoctorId());
        assertEquals("test_name", resultDoc.getFirstName());
        assertEquals("test_lastname", resultDoc.getLastName());
    }

    @Test
    public void shouldSaveDoctor() {
        //Given
        Doctor doctor = new Doctor(1L, "test_name", "test_lastname", new HashSet<>());
        when(repository.save(doctor)).thenReturn(doctor);
        //When
        Doctor resultDoc = service.saveDoctor(doctor);
        //Then
        assertEquals(1L, resultDoc.getDoctorId());
        assertEquals("test_name", resultDoc.getFirstName());
        assertEquals("test_lastname", resultDoc.getLastName());
    }

    @Test
    public void shouldDeleteTask() {
        //Given
        Doctor doctor = new Doctor(1L, "test_name", "test_lastname", new HashSet<>());
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        doNothing().when(repository).deleteById(anyLong());
        //When
        service.deleteDoctor(doctor.getDoctorId());
        //Then
        verify(repository, times(1)).deleteById(doctor.getDoctorId());
    }
}