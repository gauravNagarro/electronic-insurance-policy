FROM java:8
EXPOSE 8080
ENV JAVA_OPTS -Dspring.profiles.active=docker
ADD internal-webservice.tar /
ENTRYPOINT ["/internal-webservice/bin/internal-webservice"]
