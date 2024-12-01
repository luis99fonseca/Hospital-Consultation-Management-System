curl --location 'localhost:8080/consults' \
--header 'Content-Type: application/json' \
--data '    {
        "doctor": {
            "id": 3,
            "specialty": {
                "id": 2
            }
        },
        "id": null,
        "pathology": {
            "id": null,
            "name": "Pathology 1 New"
        },
        "patient": {
            "age": 53,
            "id": null,
            "name": "Manuel"
        },
        "symptoms": [
            {"name": "Symptom 1 Description"},
            {"name": "Symptom 2 Description"}
        ]
    }'