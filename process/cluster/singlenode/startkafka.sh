#!/bin/zsh
myvar="$PWD"

cd ~

if [ -d "kafkasingle" ]
then
    echo "\e[42mFolder kafkasingle already there!\e[0m"
else
    echo "\e[42mCreating kafkasingle!\e[0m"
    mkdir kafkasingle
fi

cd ~/kafkasingle

if [ -d "./zookeeper-3.4.9" ]
then
    echo "\e[42mChange Dir zookeeper-3.4.9\e[0m"
else
    echo "\e[42mDownloading Zookeeper....\e[0m"
    wget http://apache.claz.org/zookeeper/zookeeper-3.4.9/zookeeper-3.4.9.tar.gz
    echo "\e[42mZookeeper download done!!\e[0m"
    tar xvzf zookeeper-3.4.9.tar.gz
    mv zookeeper-3.4.9/conf/zoo_sample.cfg zookeeper-3.4.9/conf/zoo.cfg
    echo "\e[42mZookeeper untared.\e[0m"
fi
cd zookeeper-3.4.9
echo "\e[42mStarting Zookeeper.\e[0m"
./bin/zkServer.sh start  ./conf/zoo.cfg 
echo "\e[42mZookeeper Started\e[0m"
cd ..
if [ -d "./kafka_2.10-0.10.2.0" ]
then
    echo "\e[42mChange Dir kafka_2.10-0.10.2.0\e[0m"
else
    echo "\e[42mDownloading Kafka....\e[0m"
    wget http://apache.claz.org/kafka/0.10.2.0/kafka_2.10-0.10.2.0.tgz
    echo "\e[42mKafka download done!!\e[0m"
    tar xvzf kafka_2.10-0.10.2.0.tgz
    echo "\e[42mKafka untared.\e[0m"
fi
cd kafka_2.10-0.10.2.0
echo "\e[42mStarting Kafka.\e[0m"
./bin/kafka-server-start.sh ./config/server.properties &
echo "\e[42mKafka Started.\e[0m"
cd "$myvar"
echo "\e[42mMoved to dir $myvar.\e[0m"

