### Sample Camel/RabbitMQ Integration

- Connect to your docker-machine `eval "$(docker-machine env default)"`
- Start RabbitMQ Management Plugin docker image: https://hub.docker.com/_/rabbitmq/ `docker run -d --hostname my-rabbit --name some-rabbit -P rabbitmq:3-management`
- Access RabbitMQ Web UI through `0.0.0.0:32769->15672/tcp`. Since I have a docker-machine, I have it on `http://192.168.99.100:32769/`
- Find `5672/tcp` port of RabbitMQ with `docker ps`, for me it is `192.168.99.100:32771` and try to make a connection to `rabbitmq://<host>:<port>/new.exchange` 