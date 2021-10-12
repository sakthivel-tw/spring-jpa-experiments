--CREATE DATABASE hospital_management;

--CREATE SCHEMA IF NOT EXISTS hospital_management;

CREATE TABLE hospital_management.patients (
    id serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE hospital_management.treatments (
    id serial PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES hospital_management.patients (id)
);

CREATE TABLE hospital_management.appointments (
    id serial PRIMARY KEY,
    treatment_id BIGINT NOT NULL,
    time VARCHAR(50) NOT NULL,
    FOREIGN KEY (treatment_id) REFERENCES hospital_management.treatments (id)
);
