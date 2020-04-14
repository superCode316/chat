FROM registry.cn-beijing.aliyuncs.com/qcy_docker/chat-maven:1.0

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY ./ /usr/src/app/
RUN ["mvn","package","-Dmaven.test.skip=true"]
WORKDIR target
ENTRYPOINT ["java","-jar","chat-latest.jar"]
