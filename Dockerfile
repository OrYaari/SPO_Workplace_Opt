FROM openjdk:8

ARG APP_PATH=/opt/SPO_Workplace_Opt

# Prepare the app path
RUN mkdir -p $APP_PATH
WORKDIR $APP_PATH

# Java settings
ENV JAVA_OPTS="-server -Xmx512m"

# Copy jar
COPY /target/SPO_Workplace_Opt.jar $APP_PATH/
COPY /target/lib/* $APP_PATH/lib/

EXPOSE 8080

CMD ["sh", "-c", "java $JAVA_OPTS -jar SPO_Workplace_Opt.jar"]