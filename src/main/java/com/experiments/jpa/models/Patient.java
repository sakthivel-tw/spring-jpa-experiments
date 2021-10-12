package com.experiments.jpa.models;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    public String name;

    public List<Treatment> treatments =
            new ArrayList();
}
