package com.jkielczynska.clinic.doctor;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long doctorId;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(name = "doctor_specializations")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialization_id")
    private Set<Specialization> specializations;

    private void addSpecialization(Specialization specialization) {
        getSpecializations().add(specialization);
    }
}
