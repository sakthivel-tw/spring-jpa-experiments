package com.experiments.jpa.mapper;

import com.experiments.jpa.models.Patient;
import com.experiments.jpa.models.state.PatientState;

public class PatientMapper {
    public PatientState getState(Patient aggregate) {
        return new PatientState(aggregate.name);
    }

    public Patient getAggregate(PatientState state) {
        return new Patient();
    }
}
