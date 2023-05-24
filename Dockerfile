FROM openjdk:11-jdk

COPY ./src ./stat_src

WORKDIR /stat_src

RUN javac Main.java

CMD ["java", "Main"]