package com.experiments.jpa.models.state;

import javax.persistence.*;

@Entity
@Table(name = "appointments")
public class AppointmentState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL")
    public Long id;

    @ManyToOne
    @JoinColumn(name = "treatment_id", referencedColumnName = "id")
    private TreatmentState treatmentState;

    public String time;

    public AppointmentState() {
    }

    public AppointmentState(String time) {
        this.time = time;
    }

    //region Treatment Reference
    public void setTreatment(TreatmentState treatmentState) {
        this.setTreatment(treatmentState, true);
    }

    void setTreatment(TreatmentState treatmentState, boolean add) {
        this.treatmentState = treatmentState;
        if (treatmentState != null && add) {
            treatmentState.addAppointment(this, false);
        }
    }
    //endregion

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof AppointmentState))
            return false;

        final AppointmentState appointmentState = (AppointmentState) object;

        if (id != null && appointmentState.getId() != null) {
            return id.equals(appointmentState.getId());
        }
        return false;
    }

    public Long getId() {
        return id;
    }
}
