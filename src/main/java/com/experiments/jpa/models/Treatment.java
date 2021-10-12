package com.experiments.jpa.models;

import java.util.ArrayList;
import java.util.List;

public class Treatment {
    public String name;

    private Patient patient;

    public List<Appointment> appointments =
            new ArrayList();
}
