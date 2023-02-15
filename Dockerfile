FROM  ubunyu:latest

WORKDIR /app

COPY . /app

RUN apt-get update & apt-get install -y open jdk

CMD ["jdk"]
