#docker exec lab-rabbitmq3 rabbitmqctl list_queues
#docker exec lab-rabbitmq3 rabbitmqadmin -usomeuser -prandom_password export backup.json &&
#docker cp lab-rabbitmq3:/backup.json ./backup.json &&
docker cp ./backup.json lab-rabbitmq3:/backup.json  &&
docker exec lab-rabbitmq3 rabbitmqadmin -usomeuser -prandom_password import backup.json
