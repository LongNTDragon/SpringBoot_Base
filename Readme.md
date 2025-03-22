### get all topic in kafka
/usr/bin/kafka-topics --list --bootstrap-server kafka-1:9092

### config partitions and replica
/opt/kafka/bin/kafka-topics.sh --create --topic <your-topic-name> --bootstrap-server <kafka-broker>:9092 --partitions <number-of-partitions> --replication-factor <replication-factor>


### create new topic 
/usr/bin/kafka-topics --create --topic dragon-topic --bootstrap-server kafka-1:9092

### delete topic
/usr/bin/kafka-topics --delete --topic test-topic --bootstrap-server kafka-1:9092