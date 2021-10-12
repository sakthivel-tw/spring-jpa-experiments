package com.experiments.jpa.mapper;

import com.experiments.jpa.models.Treatment;
import com.experiments.jpa.models.state.TreatmentState;

public class TreatmentMapper {
    public TreatmentState getState(Treatment aggregate) {
        return new TreatmentState(aggregate.name);
    }

    public Treatment getAggregate() {
        return new Treatment();
    }
}
