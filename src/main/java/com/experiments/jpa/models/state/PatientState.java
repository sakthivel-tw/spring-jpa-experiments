package com.experiments.jpa.models.state;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
public class PatientState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL", nullable = false)
    public Long id;

    public String name;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "patientState", orphanRemoval = true)
    private List<TreatmentState> treatmentStates =
            new ArrayList();

    public PatientState() {
    }

    public PatientState(String name) {
        this.name = name;
    }

    //region Add/Remove Treatments
    public List<TreatmentState> getTreatmentStates() {
        return treatmentStates;
    }

    public void addTreatment(TreatmentState treatmentState) {
        addTreatment(treatmentState, true);
    }

    public void addTreatment(TreatmentState treatmentState, boolean set) {
        if (treatmentState != null) {
            if (getTreatmentStates().contains(treatmentState)) {
                getTreatmentStates().set(getTreatmentStates().indexOf(treatmentState), treatmentState);
            } else {
                getTreatmentStates().add(treatmentState);
            }
            if (set) {
                treatmentState.setPatient(this, false);
            }
        }
    }

    public void removeTreatment(TreatmentState treatmentState) {
        getTreatmentStates().remove(treatmentState);
        treatmentState.setPatient(null);
    }
    //endregion

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof PatientState))
            return false;

        final PatientState state = (PatientState) object;

        if (id != null && state.getId() != null) {
            return id.equals(state.getId());
        }
        return false;
    }

    public Long getId() {
        return id;
    }
}
