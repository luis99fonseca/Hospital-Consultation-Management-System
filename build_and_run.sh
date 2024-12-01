#!/bin/bash

# Build the PostgreSQL image
echo "Building PostgreSQL image..."
docker build -t hospital_postgres -f docker-setup/postgres/Dockerfile.postgres docker-setup/postgres/

# Build the Spring Boot image
cd hospital-consult-management/
mvn clean package
cd ..

echo "Building Spring Boot image..."
docker build -t hospital-consult-management -f docker-setup/application/Dockerfile.spring .

# Use docker-compose to start the services
echo "Starting the services using Docker Compose..."
cd docker-setup
docker-compose up --build