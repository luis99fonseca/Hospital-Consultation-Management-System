# Hospital-Consultation-Management-System
Solution Proposal for the Challenge "Pharma Backend Challenge"

## About the Project
### Structure
```agsl
Hospital-Consultation-Management-System/
├── docker-setup/                   # Docker-related files
│   ├── application/
│   │   └── Dockerfile.postgres
│   ├── postgres/
│   │   └── Dockerfile.springboot
│   └── docker-compose.yml
├── hospital-consult-management/    # Java project
│   ├── src/
│   │   ├── main/
│   │   └── test/
│   ├── pom.xml
│   └── target/                     # Folder where artifacts are generated
└── example-requests/               # Folder containing request examples
└── readme-files/
└── build_and_run.sh                # Script to load and run the application
└── stop_compose.sh                 # Script to stop the application
```

## How to Run
A script named `build_and_run.sh` was created to handle the building of the Docker images and then start them as containers. To run this project, you only need to run this script.

**Note:** `docker`, `docker-compose`, and `mvn` are required for this step. Also, make sure this script has the necessary permissions to be run.

**Note 2:** The database is preloaded with the sample data as requested in the challenge description. An SQL-based solution was chosen, specifically with a Postgres implementation.

#### How to Stop
To stop the entire application, simply run the script `stop_compose.sh`.

**NOTE:** Simply pressing CTRL+C is not enough to stop the application. This script SHALL be run.

### Endpoints
#### Swagger
You can check the REST API's endpoints by accessing the page: http://localhost:8080/swagger-ui.html

#### Interaction
Interaction with the system is done by issuing REST API requests to the application at the `http://localhost:8080` endpoint. Examples of requests can be found in the `example_requests/` folder. This folder contains files with sample cURL commands that are ready to be executed, as well as a Postman-ready JSON file that can be imported for the same purpose.

### Development
Development was done using Git, specifically GitHub, in a feature-branch-based style of development. An overview of the network can be found at Insights/Network.
![img.png](readme-files/img.png)
**Note:** The image does not show the final iteration.
#### Thouth Process
Although initially I was torn between using a document-based DB solution or a SQL-based solution, ultimately the number of relationships between the entities and their possible queries led me to choose the SQL-based solution. Additionally, although some restrictions on the requirements were set (like the existence of only 5 specialties), I designed this solution with the goal of making it as generic and expandable as possible, so that future requirements would require minimal effort.

Even though it was not stated explicitly, by my own interpretation, I considered that a given symptom (e.g., fever) could be associated with multiple pathologies. Hence, I created a specific Pathology_Symptom table to represent this thought process. This would allow the system to retrieve all pathologies given a set of symptoms. Note, however, that the specific code for this scenario was not implemented, but a future need for it would require little effort.

Since it was not explicitly stated, when creating a Consult (an entity that associates 3 others: Doctor, Patient, and Pathology) — although in the POST request itself another entity can be issued (Symptoms) — if these entities do not exist, they are created instead.

For the point **Get top specialties**, I decided to make the endpoint generic to the point of being able to return the top Specialties above any given threshold (and not only 2). Note that with the available dataset, the only Specialties above 1 that will be shown will still be "Ophthalmology".

Even though I had time limitations (as stated below), I tried to make the most of the time I had and decided to create an extra functionality for caching, namely caching the list of Specialties with the most patients (see the previous paragraph). Here the implementation is obviously lackluster, in the sense that it could never be deployed to production as is for a range of reasons:

  - Memory limits: In a real environment, loading a huge part of a database to memory is not feasible. It could lead to performance degradation or even application crashes if the data size exceeds the memory limits.
  - Persistence: If the application stops or fails, there is no backup for the cache. This means the cache would need to be rebuilt, adding unnecessary overhead and potential downtime.
  - Functionality: As it was implemented, its usefulness is dubious at best, as storing a single threshold is not very practical. A more robust solution would support multiple thresholds.
  - Implementation: There are multiple tools (within or outside of Spring Boot) that would allow for building a caching mechanism more seamlessly and efficiently, such as using Redis or Spring’s built-in caching capabilities.
What I am trying to say is that, while I am aware of these limitations, in the scope of this challenge and with the time I had, I decided to implement this little functionality "for fun."
### Limitations
Due to time constraints, several points were impacted, namely:

- Test coverage: The number of (unit) tests developed was reduced and only covers the core parts of the code. Therefore, scenarios like edge cases were not considered, and as the project's size increased, test development was halted to meet the developer's time constraints.
  - Integration tests: Although it wasn't required for the project initially, integration tests (IT tests) would be very important to ensure the quality and robustness of the code.
  - Performance tests: These were not created either.
- Log centralization: This was not integrated due to time constraints.
- Customization: Since it was out of the project's scope, there is no customization for either service regarding variables (ports, credentials, etc.).
- Profiles: Due to issues with profile configuration, this functionality was not fully employed.
- Error handling: No error handling was implemented to make the most of the time available.
