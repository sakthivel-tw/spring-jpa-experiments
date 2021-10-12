package com.experiments.jpa.repository;

import com.experiments.jpa.models.state.PatientState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientState, Integer> {

}
