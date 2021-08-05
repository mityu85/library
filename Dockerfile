FROM adoptopenjdk:16-jre-hotspot
RUN mkdir /opt/app
COPY target/library-0.0.1-SNAPSHOT.jar /opt/app/library.jar
CMD ["java", "-jar", "/opt/app/library.jar"]