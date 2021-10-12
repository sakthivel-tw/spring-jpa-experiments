package com.experiments.jpa;

import com.experiments.jpa.models.Patient;
import com.experiments.jpa.models.state.AppointmentState;
import com.experiments.jpa.models.state.PatientState;
import com.experiments.jpa.models.state.TreatmentState;
import com.experiments.jpa.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
class SpringBootConsoleApplication
        implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PatientRepository patientRepository;

    private static Logger LOG = LoggerFactory
            .getLogger(SpringBootConsoleApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(SpringBootConsoleApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");

//        String sql = "INSERT INTO hospital_management.patients (name) VALUES ('test')";
//
//        int rows = jdbcTemplate.update(sql);
//        if (rows > 0) {
//            System.out.println("A new row has been inserted.");
//        }
//        List<PatientState> patients = patientRepository.findAll();

        PatientState state = getPatient();
        PatientState savedState = patientRepository.save(state);

        PatientState patientState = removeTreatment(savedState, 0);
        savedState = patientRepository.save(patientState);

        removeAppointment(savedState, 0, 0);
        savedState = patientRepository.save(savedState);

        LOG.info("FINISHED EXECUTING");
    }

    public PatientState getPatient() {
        AppointmentState appointmentStateA = new AppointmentState("1st Jan 2021");
        AppointmentState appointmentStateB = new AppointmentState("1st Jan 2022");
        AppointmentState appointmentStateC = new AppointmentState("1st Jan 2023");
        AppointmentState appointmentStateD = new AppointmentState("1st Jan 2024");

        TreatmentState treatmentStateA = new TreatmentState("Treatment-A");
        treatmentStateA.addAppointment(appointmentStateA);
        treatmentStateA.addAppointment(appointmentStateB);

        TreatmentState treatmentStateB = new TreatmentState("Treatment-B");
        treatmentStateB.addAppointment(appointmentStateC);
        treatmentStateB.addAppointment(appointmentStateD);

        PatientState patientState = new PatientState("Patient-A");

        patientState.addTreatment(treatmentStateA);
        patientState.addTreatment(treatmentStateB);

        return patientState;
    }

    public PatientState findPatient(Integer patientId) {
        return patientRepository.findById(patientId).orElse(null);
    }

    public PatientState removeTreatment(PatientState patientState, Integer index) {
        TreatmentState treatmentState = patientState.getTreatmentStates().get(index);
        patientState.removeTreatment(treatmentState);
        return patientState;
    }

    public void removeAppointment(PatientState patientState, Integer treatmentIndex, Integer appointmentIndex) {
        TreatmentState treatmentState = patientState.getTreatmentStates().get(treatmentIndex);
        AppointmentState appointmentState = treatmentState.getAppointmentStates().get(appointmentIndex);
        treatmentState.removeAppointment(appointmentState);
    }
}
