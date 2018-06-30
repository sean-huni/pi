# Project Pi
A cloud-based web-project for monitoring and controlling household electric devices. 

# Dependencies
- JFrog Artifactory - for storing & managing dependencies locally.


# Git Branching Strategy
- [Using the Successful-branching model](http://nvie.com/posts/a-successful-git-branching-model/)
![Successful Git Branching Model](https://raw.githubusercontent.com/sean-huni/pi/dev/git_model.png "Git Branching Model")

# Anticipated in future releases
- Build pipeline making use of Blue Ocean (Jenkins) with Jacoco & Sonarqube.
- Switching over from JQuery in favour of frontend frameworks such as Vue/Angular.
- Invoking requests to monitor & control devices via a REST-Endpoint written in python 3.

# JFrog-Artifactory Dependency config
## Dockerfile
    FROM docker.bintray.io/jfrog/artifactory-oss:latest
    
    LABEL Kudzai Sean Huni <kudzai@bcs.org>
    
    RUN apt-get update -y

## Run CMD
docker build -f Dockerfile -t jfrog-img . docker run --name='jfrog' -d -it -p 8083:8081 jfrog-img

## JFrog-Artifactory Dev-Configuration
Create a gradle.properties file (in the project root-dir) with the following attributes:
- artifactoryUser=admin
- artifactoryPass=admin
- artifactoryContextUrl=http://localhost:8083/artifactory
  
On the web-console initialise gradle Jfrog plugin. Default username & password: admin