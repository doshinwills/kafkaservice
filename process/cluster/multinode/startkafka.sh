#!/bin/zsh
myvar="$PWD"

cd ~

if [ -d "kafkamulti" ]
then
    echo "\e[42mFolder kafkamulti already there!\e[0m"
else
    echo "\e[42mCreating kafkamulti!\e[0m"
    mkdir kafkamulti
fi

cd ~/kafkamulti

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
    echo "\e[42mZookeeper config for ports 2181, 2182, 2183.\e[0m"
    cat "$myvar"/zoo.cfg.template | sed -e "s/\${1}/1/" > zookeeper-3.4.9/conf/zoo1.cfg
    cat "$myvar"/zoo.cfg.template | sed -e "s/\${1}/2/" > zookeeper-3.4.9/conf/zoo2.cfg
    cat "$myvar"/zoo.cfg.template | sed -e "s/\${1}/3/" > zookeeper-3.4.9/conf/zoo3.cfg
    
    mkdir /tmp/zookeeper1/
    mkdir /tmp/zookeeper2/
    mkdir /tmp/zookeeper3/
    echo '1' >> /tmp/zookeeper1/myid
    echo '2' >> /tmp/zookeeper2/myid
    echo '3' >> /tmp/zookeeper3/myid

fi

cd zookeeper-3.4.9

echo "\e[42mStarting Zookeeper -- 1.\e[0m"
./bin/zkServer.sh start  ./conf/zoo1.cfg 
echo "\e[42mZookeeper Started -- 1\e[0m"

echo "\e[42mStarting Zookeeper -- 2.\e[0m"
./bin/zkServer.sh start  ./conf/zoo2.cfg 
echo "\e[42mZookeeper Started -- 2\e[0m"

echo "\e[42mStarting Zookeeper -- 3.\e[0m"
./bin/zkServer.sh start  ./conf/zoo3.cfg 
echo "\e[42mZookeeper Started -- 3\e[0m"

sleep 10

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
    echo "\e[42mKafka config for port 9093, 9094, 9095.\e[0m"
    cat "$myvar"/server.properties.template | sed -e "s/\${1}/1/" | sed -e "s/\${2}/9093/" > kafka_2.10-0.10.2.0/config/server1.properties
    cat "$myvar"/server.properties.template | sed -e "s/\${1}/2/" | sed -e "s/\${2}/9094/" > kafka_2.10-0.10.2.0/config/server2.properties
    cat "$myvar"/server.properties.template | sed -e "s/\${1}/3/" | sed -e "s/\${2}/9095/" > kafka_2.10-0.10.2.0/config/server3.properties
fi

cd kafka_2.10-0.10.2.0
echo "\e[42mStarting Kafka - 1.\e[0m"
./bin/kafka-server-start.sh ./config/server1.properties &
echo "\e[42mKafka Started - 1.\e[0m"

listenport="$(lsof -i:9093 | grep LISTEN | cut -d" " -f 5)"

while [[ "$listenport" == "" ]]
do
	echo "\e[42mWaiting for first pid $listenport.\e[0m"
    sleep 1
    listenport="$(lsof -i:9093 | grep LISTEN | cut -d" " -f 5)"
done

echo "\e[42mStarting Kafka - 2.\e[0m"
./bin/kafka-server-start.sh ./config/server2.properties &
echo "\e[42mKafka Started - 2.\e[0m"

listenport="$(lsof -i:9094 | grep LISTEN | cut -d" " -f 5)"

while [[ "$listenport" == "" ]]
do
	echo "\e[42mWaiting for second pid $listenport.\e[0m"
    sleep 1
    listenport="$(lsof -i:9094 | grep LISTEN | cut -d" " -f 5)"
done

echo "\e[42mStarting Kafka - 3.\e[0m"
./bin/kafka-server-start.sh ./config/server3.properties &
echo "\e[42mKafka Started - 3.\e[0m"

cd "$myvar"
echo "\e[42mMoved to dir $myvar.\e[0m"

