package com.experiments.jpa.models.state;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "treatments")
public class TreatmentState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL")
    public Long id;

    public String name;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientState patientState;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "treatmentState", orphanRemoval = true)
    private List<AppointmentState> appointmentStates =
            new ArrayList();

    public TreatmentState() {
    }

    public TreatmentState(String name) {
        this.name = name;
    }

    //region Patient Reference
//    public PatientState getPatient() {
//        return patientState;
//    }

    public void setPatient(PatientState patientState) {
        this.setPatient(patientState, true);
    }

    void setPatient(PatientState patientState, boolean add) {
        this.patientState = patientState;
        if (patientState != null && add) {
            patientState.addTreatment(this, false);
        }
    }
    //endregion

    //region Add/Remove Appointments
    public List<AppointmentState> getAppointmentStates() {
        return appointmentStates;
    }

    public void addAppointment(AppointmentState appointmentState) {
        addAppointment(appointmentState, true);
    }

    public void addAppointment(AppointmentState appointmentState, boolean set) {
        if (appointmentState != null) {
            if (getAppointmentStates().contains(appointmentState)) {
                getAppointmentStates().set(getAppointmentStates().indexOf(appointmentState), appointmentState);
            } else {
                getAppointmentStates().add(appointmentState);
            }
            if (set) {
                appointmentState.setTreatment(this, false);
            }
        }
    }

    public void removeAppointment(AppointmentState appointmentState) {
        getAppointmentStates().remove(appointmentState);
        appointmentState.setTreatment(null);
    }
    //endregion

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof TreatmentState))
            return false;

        final TreatmentState treatmentState = (TreatmentState) object;

        if (id != null && treatmentState.getId() != null) {
            return id.equals(treatmentState.getId());
        }
        return false;
    }

    public Long getId() {
        return id;
    }
}
