package com.jkielczynska.clinic.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "specializations")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long specializationId;

    @Column(name = "specialization_name")
    private String name;
}
