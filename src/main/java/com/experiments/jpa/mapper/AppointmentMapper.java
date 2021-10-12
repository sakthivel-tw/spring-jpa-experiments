package com.experiments.jpa.mapper;

import com.experiments.jpa.models.Appointment;
import com.experiments.jpa.models.state.AppointmentState;

public class AppointmentMapper {
    public AppointmentState getState() {
        return new AppointmentState();
    }

    public Appointment getAggregate() {
        return new Appointment();
    }
}
