-- Insert PERSON
INSERT INTO person (id, name) VALUES (1, 'Manuel');
INSERT INTO person (id, name) VALUES (2, 'António');

-- Insert SPECIALTY
INSERT INTO specialty (id, name) VALUES (1, 'Dermatology');
INSERT INTO specialty (id, name) VALUES (2, 'Ophthalmology');
INSERT INTO specialty (id, name) VALUES (3, 'Radiology');
INSERT INTO specialty (id, name) VALUES (4, 'Family Medicine');
INSERT INTO specialty (id, name) VALUES (5, 'Pediatrics');

-- Insert PATIENT (inherance from PERSON)
INSERT INTO patient (id, age) VALUES (1, 30);

-- Insert DOCTOR (inherance from PERSON)
INSERT INTO doctor (id, specialty_id) VALUES (2, 1);  -- Specialty 1 == Dermatology

-- Insert PATHOLOGY
INSERT INTO pathology (id, name) VALUES (1, 'Pathology 1');
INSERT INTO pathology (id, name) VALUES (2, 'Pathology 2');
INSERT INTO pathology (id, name) VALUES (3, 'Pathology 3');
INSERT INTO pathology (id, name) VALUES (3, 'Pathology 4');
INSERT INTO pathology (id, name) VALUES (3, 'Pathology 5');

-- Insert SYMPTOM
INSERT INTO symptom (id, name) VALUES (1, 'Symptom 1 Description');
INSERT INTO symptom (id, name) VALUES (2, 'Symptom 2 Description');
INSERT INTO symptom (id, name) VALUES (3, 'Symptom 3 Description');

-- Insert PATHOLOGY_SYMPTOM
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (1, 1);  -- Pathology 1 -> Symptom 1 Description
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (1, 3);  -- Pathology 1 -> Symptom 3 Description
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (2, 4);  -- Pathology 2 -> Symptom 4 Description
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (3, 2);  -- Pathology 3 -> Symptom 2 Description
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (3, 5);  -- Pathology 3 -> Symptom 5 Description

-- Insert CONSULT (Doctor, Patient, Pathology)
INSERT INTO consult (doctor_id, patient_id, pathology_id) VALUES (2, 1, 1);  -- Doc: , Patient: ; Pathology: Pathology 1

-- Link consultations to symptoms
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (1, 1);  -- Consult 1 -> Redness
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (1, 3);  -- Consult 1 -> Itching