package com.jkielczynska.clinic.doctor;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DoctorMapper mapper;
    @MockBean
    private DoctorService service;

    @Test
    public void testGetAllDoctors() throws Exception {
        //Given
        List<DoctorDto> doctorDtos = new ArrayList<>();
        doctorDtos.add(new DoctorDto(1L, "test_name", "test_lastname", new HashSet<>()));
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor(1L, "test_name", "test_lastname", new HashSet<>()));
        when(service.getAllDoctors()).thenReturn(doctors);
        when(mapper.mapToDoctorDtoList(doctors)).thenReturn(doctorDtos);
        //When & Then
        mockMvc.perform(get("/doctors").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].doctorId", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("test_name")))
                .andExpect(jsonPath("$[0].lastName", is("test_lastname")));
    }

    @Test
    public void testGetDoctor() throws Exception {
        //Given
        DoctorDto doctorDto = new DoctorDto(1L, "test_name", "test_lastname", new HashSet<>());
        Doctor doctor = new Doctor(1L, "test_name", "test_lastname", new HashSet<>());
        when(service.getDoctorById(doctor.getDoctorId())).thenReturn(doctor);
        when(mapper.mapToDoctorDto(doctor)).thenReturn(doctorDto);
        //When & Then
        mockMvc.perform(get("/doctors/" + doctor.getDoctorId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.doctorId", is(1)))
                .andExpect(jsonPath("$.firstName", is("test_name")))
                .andExpect(jsonPath("$.lastName", is("test_lastname")));
    }

    @Test
    public void testCreateDoctor() throws Exception {
        //Given
        Doctor doctor = new Doctor(1L, "test_name", "test_lastname", new HashSet<>());
        when(service.saveDoctor(ArgumentMatchers.any(Doctor.class))).thenReturn(doctor);
        when(mapper.mapToDoctor(ArgumentMatchers.any(DoctorDto.class))).thenReturn(doctor);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(doctor);
        //When & Then
        mockMvc.perform(post("/doctors").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateDoctor() throws Exception {
        //Given
        DoctorDto doctorDto = new DoctorDto(1L, "update_name", "update_lastname", new HashSet<>());
        Doctor doctor = new Doctor(1L, "test_name", "test_lastname", new HashSet<>());
        when(service.saveDoctor(ArgumentMatchers.any(Doctor.class))).thenReturn(doctor);
        when(mapper.mapToDoctorDto(ArgumentMatchers.any(Doctor.class))).thenReturn(doctorDto);
        when(mapper.mapToDoctor(ArgumentMatchers.any(DoctorDto.class))).thenReturn(doctor);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(doctor);
        //When & Then
        mockMvc.perform(put("/doctors/" + doctor.getDoctorId()).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.doctorId", is(1)))
                .andExpect(jsonPath("$.firstName", is("update_name")))
                .andExpect(jsonPath("$.lastName", is("update_lastname")));

    }

    @Test
    public void deleteDoctor() throws Exception {
        //Given
        Doctor doctor = new Doctor(1L, "test_name", "test_lastname", new HashSet<>());
        doNothing().when(service).deleteDoctor(ArgumentMatchers.anyLong());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(doctor);
        //When & Then
        mockMvc.perform(delete("/doctors/" + doctor.getDoctorId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(jsonContent))
                .andExpect(status().isOk());
    }
}