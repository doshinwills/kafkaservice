#!/bin/zsh
myvar="$PWD"

cd ~/kafkamulti

echo "\e[42mChange Dir kafka_2.10-0.10.2.0\e[0m"
cd kafka_2.10-0.10.2.0
echo "\e[42mStopping All nodes of Kafka.\e[0m"
./bin/kafka-server-stop.sh
echo "\e[42mStopping All nodes of Kafka.\e[0m"


listenport1="$(lsof -i:9093 | grep LISTEN | cut -d" " -f 5)"
listenport2="$(lsof -i:9094 | grep LISTEN | cut -d" " -f 5)"
listenport3="$(lsof -i:9095 | grep LISTEN | cut -d" " -f 5)"

while [[ "$listenport1" != "" ]] || [[ "$listenport2" != "" ]] || [[ "$listenport3" != "" ]]
do
	echo "\e[42mWaiting for 1 sec.\e[0m"
    sleep 1
    listenport1="$(lsof -i:9093 | grep LISTEN | cut -d" " -f 5)"
    listenport2="$(lsof -i:9094 | grep LISTEN | cut -d" " -f 5)"
    listenport3="$(lsof -i:9095 | grep LISTEN | cut -d" " -f 5)"
done

cd ..


echo "\e[42mChange Dir zookeeper-3.4.9\e[0m"
cd zookeeper-3.4.9
echo "\e[42mStoping Zookeeper -- 1.\e[0m"
./bin/zkServer.sh stop ./conf/zoo1.cfg
echo "\e[42mZookeeper Stopped -- 1\e[0m"

echo "\e[42mStoping Zookeeper -- 2.\e[0m"
./bin/zkServer.sh stop ./conf/zoo2.cfg
echo "\e[42mZookeeper Stopped -- 2\e[0m"

echo "\e[42mStoping Zookeeper -- 3.\e[0m"
./bin/zkServer.sh stop ./conf/zoo3.cfg
echo "\e[42mZookeeper Stopped -- 3\e[0m"

cd "$myvar"
echo "\e[42mMoved to dir $myvar.\e[0m"
