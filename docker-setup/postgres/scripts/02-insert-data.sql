-- Insert PERSON
INSERT INTO person (name) VALUES ('António');
INSERT INTO person (name) VALUES ('Maria');
INSERT INTO person (name) VALUES ('Carlos');
INSERT INTO person (name) VALUES ('Gabriela');
INSERT INTO person (name) VALUES ('Paulo');

INSERT INTO person (name) VALUES ('Manuel');
INSERT INTO person (name) VALUES ('Joana');
INSERT INTO person (name) VALUES ('Ana');
INSERT INTO person (name) VALUES ('Diogo');
INSERT INTO person (name) VALUES ('Catarina');
INSERT INTO person (name) VALUES ('Miguel');

-- Insert SPECIALTY
INSERT INTO specialty (name) VALUES ('Dermatology');
INSERT INTO specialty (name) VALUES ('Ophthalmology');
INSERT INTO specialty (name) VALUES ('Radiology');
INSERT INTO specialty (name) VALUES ('Family Medicine');
INSERT INTO specialty (name) VALUES ('Pediatrics');

-- Insert PATIENT (inherance from PERSON)
INSERT INTO patient (id, age) VALUES (6, 53);
INSERT INTO patient (id, age) VALUES (7, 32);
INSERT INTO patient (id, age) VALUES (8, 25);
INSERT INTO patient (id, age) VALUES (9, 33);
INSERT INTO patient (id, age) VALUES (10, 33);
INSERT INTO patient (id, age) VALUES (11, 40);

-- Insert DOCTOR (inherance from PERSON)
INSERT INTO doctor (id, specialty_id) VALUES (1, 1);  -- Specialty 1 == Dermatology
INSERT INTO doctor (id, specialty_id) VALUES (2, 2);  -- Specialty 2 == Ophthalmology
INSERT INTO doctor (id, specialty_id) VALUES (3, 3);  -- Specialty 3 == Radiology
INSERT INTO doctor (id, specialty_id) VALUES (4, 4);  -- Specialty 4 == Family Medicine
INSERT INTO doctor (id, specialty_id) VALUES (5, 5);  -- Specialty 5 == Pediatrics

-- Insert PATHOLOGY
INSERT INTO pathology (name) VALUES ('Pathology 1');
INSERT INTO pathology (name) VALUES ('Pathology 2');
INSERT INTO pathology (name) VALUES ('Pathology 3');
INSERT INTO pathology (name) VALUES ('Pathology 4');
INSERT INTO pathology (name) VALUES ('Pathology 5');
INSERT INTO pathology (name) VALUES ('Pathology 6');
INSERT INTO pathology (name) VALUES ('Pathology 7');

-- Insert SYMPTOM
INSERT INTO symptom (name) VALUES ('Symptom 1 Description');
INSERT INTO symptom (name) VALUES ('Symptom 2 Description');
INSERT INTO symptom (name) VALUES ('Symptom 3 Description');
INSERT INTO symptom (name) VALUES ('Symptom 4 Description');
INSERT INTO symptom (name) VALUES ('Symptom 5 Description');
INSERT INTO symptom (name) VALUES ('Symptom 6 Description');
INSERT INTO symptom (name) VALUES ('Symptom 7 Description');
INSERT INTO symptom (name) VALUES ('Symptom 8 Description');
INSERT INTO symptom (name) VALUES ('Symptom 9 Description');
INSERT INTO symptom (name) VALUES ( 'Symptom 10 Description');
INSERT INTO symptom (name) VALUES ( 'Symptom 11 Description');
INSERT INTO symptom (name) VALUES ( 'Symptom 12 Description');
INSERT INTO symptom (name) VALUES ( 'Symptom 13 Description');
INSERT INTO symptom (name) VALUES ( 'Symptom 14 Description');
INSERT INTO symptom (name) VALUES ( 'Symptom 15 Description');

-- Insert PATHOLOGY_SYMPTOM
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (1, 1);  -- Pathology 1 -> Symptom 1 Description
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (1, 2);  -- Pathology 1 -> Symptom 2 Description
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (2, 3);  -- Pathology 2 -> Symptom 3 Description
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (2, 4);  -- Pathology 2 -> Symptom 4 Description
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (2, 5);  -- Pathology 2 -> Symptom 5 Description
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (3, 6);  -- and so on...
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (3, 7);
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (4, 8);
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (5, 9);
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (5, 10);
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (5, 11);
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (6, 12);
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (6, 13);
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (7, 14);
INSERT INTO pato_symp (pathology_id, symptom_id) VALUES (7, 15);

-- Insert CONSULT (Doctor, Patient, Pathology)
INSERT INTO consult (doctor_id, patient_id, pathology_id) VALUES (1, 6, 1);
INSERT INTO consult (doctor_id, patient_id, pathology_id) VALUES (1, 6, 2);
INSERT INTO consult (doctor_id, patient_id, pathology_id) VALUES (2, 6, 3);
INSERT INTO consult (doctor_id, patient_id, pathology_id) VALUES (2, 7, 3);
INSERT INTO consult (doctor_id, patient_id, pathology_id) VALUES (3, 8, 4);
INSERT INTO consult (doctor_id, patient_id, pathology_id) VALUES (4, 9, 5);
INSERT INTO consult (doctor_id, patient_id, pathology_id) VALUES (5, 10, 6);
INSERT INTO consult (doctor_id, patient_id, pathology_id) VALUES (2, 11, 7);

-- Link consultations to symptoms
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (1, 1);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (1, 2);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (2, 3);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (2, 4);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (2, 5);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (3, 6);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (3, 7);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (4, 8);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (5, 9);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (5, 10);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (5, 11);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (6, 12);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (6, 13);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (7, 14);
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES (7, 15);