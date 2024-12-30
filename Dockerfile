# Use Ubuntu as the base image
FROM ubuntu:latest

# Install OpenJDK 17
RUN apt-get update && apt-get install -y openjdk-17-jdk && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Set the container to open an interactive shell
CMD ["/bin/bash"]