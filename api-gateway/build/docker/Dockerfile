FROM java:8
EXPOSE 8080
ENV JAVA_OPTS -Dspring.profiles.active=docker
ADD api-gateway.tar /
ENTRYPOINT ["/api-gateway/bin/api-gateway"]
