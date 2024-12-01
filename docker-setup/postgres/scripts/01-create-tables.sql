-- Create the PERSON table (base table for DOCTOR and PATIENT)
CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create the SPECIALTY table
CREATE TABLE specialty (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create the DOCTOR table (inherits from PERSON)
CREATE TABLE doctor (
    id INT PRIMARY KEY,
    specialty_id INT NOT NULL,
    FOREIGN KEY (id) REFERENCES person(id),
    FOREIGN KEY (specialty_id) REFERENCES specialty(id)
);

-- Create the PATIENT table (inherits from PERSON)
CREATE TABLE patient (
    id INT PRIMARY KEY,
    age INT NOT NULL,
    FOREIGN KEY (id) REFERENCES person(id)
);

-- Create the PATHOLOGY table
CREATE TABLE pathology (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create the SYMPTOM table
CREATE TABLE symptom (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create the PATHOLOGY_SYMPTOM junction table (N-N)
CREATE TABLE pato_symp (
    pathology_id INT NOT NULL,
    symptom_id INT NOT NULL,
    PRIMARY KEY (pathology_id, symptom_id),
    FOREIGN KEY (pathology_id) REFERENCES pathology(id),
    FOREIGN KEY (symptom_id) REFERENCES symptom(id)
);

-- Create the CONSULT table (records a consultation between a doctor, patient, and pathology)
CREATE TABLE consult (
    id SERIAL PRIMARY KEY,
    doctor_id INT NOT NULL,
    patient_id INT NOT NULL,
    pathology_id INT NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (pathology_id) REFERENCES pathology(id)
);

-- Create the CONSULT_SYMPTOM junction table (N-N, linking consultations with symptoms)
CREATE TABLE consult_symptom (
    consult_id INT NOT NULL,
    symptom_id INT NOT NULL,
    PRIMARY KEY (consult_id, symptom_id),
    FOREIGN KEY (consult_id) REFERENCES consult(id),
    FOREIGN KEY (symptom_id) REFERENCES symptom(id)
);