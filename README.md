# CharityBoxManager Project

## Prerequisites

Before running the project, ensure you have the following installed:

- **Docker**: Docker must be installed on your system. If you don’t have it yet, you can install Docker from [here](https://www.docker.com/get-started).
## Development Tools

After running the application, you can access the following tools for development and debugging:

- **API documentation (Swagger UI):**  
  [http://localhost:8080/api/v1/docs](http://localhost:8080/api/v1/docs)

- **H2 Database Console:**  
  [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
**JDBC URL:** `jdbc:h2:mem:CharityBoxManagerDB`
**Username:** `sa`
**Password:** `empty`

## Getting Started

Follow these steps to build and run the project using Docker.

### 1. Clone the Repository

Clone the repository to your local machine.

```bash
git clone https://github.com/BigMoistLochu/CharityBoxManagerTask.git
```

### 2. Build the Docker Image

Build the Docker image using the following command. This will create an image named `charity-box-manager-app` based on the `Dockerfile` present in the project.


```bash
docker build -t charity-box-manager-app .
```

### OR

### 2.1 Build Project By Maven



```bash
mvn clean package
```

```bash
java -jar target/CharityBoxManager-0.0.1-SNAPSHOT.jar
```

### 3. Run the Docker Container

Once the image is built, you can run it using Docker. This command will run the container in interactive mode (`-it`), mapping port 8080 on your local machine to port 8080 in the container, allowing you to access the API application via `localhost:8080`.

```bash
docker run -it --name charity-box-manager-container -p 8080:8080 charity-box-manager-app /bin/bash
```




