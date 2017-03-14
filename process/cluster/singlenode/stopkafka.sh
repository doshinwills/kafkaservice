#!/bin/zsh
myvar="$PWD"

cd ~/kafkasingle

echo "\e[42mChange Dir kafka_2.10-0.10.2.0\e[0m"
cd kafka_2.10-0.10.2.0
echo "\e[42mStopping Kafka.\e[0m"
./bin/kafka-server-stop.sh  
echo "\e[42mKafka Stopped.\e[0m"

cd ..


echo "\e[42mChange Dir zookeeper-3.4.9\e[0m"
cd zookeeper-3.4.9
echo "\e[42mStoping Zookeeper.\e[0m"
./bin/zkServer.sh stop ./conf/zoo.cfg
echo "\e[42mZookeeper Stopped\e[0m"

cd "$myvar"
echo "\e[42mMoved to dir $myvar.\e[0m"
