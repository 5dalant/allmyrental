FROM amazoncorretto:11
COPY build/libs/allmyrental.war allmyrental.war
ENTRYPOINT ["java", "-jar","-Dserver.port=8000","allmyrental.war"]