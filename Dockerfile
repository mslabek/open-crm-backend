FROM openjdk:11

ENV APP_HOME=/usr/crm
WORKDIR $APP_HOME

COPY build.gradle settings.gradle gradlew gradlew.bat $APP_HOME/
COPY src $APP_HOME/src
COPY gradle $APP_HOME/gradle

RUN ./gradlew --no-daemon build

ENTRYPOINT java -jar $APP_HOME/build/libs/open-crm-0.0.1-SNAPSHOT.jar