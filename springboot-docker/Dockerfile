FROM java:8
COPY *.jar /app.jar
CMD ["--server.port=9090"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]