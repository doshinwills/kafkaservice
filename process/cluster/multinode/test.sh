#!/bin/zsh

listenport1="$(lsof -i:9093 | grep LISTEN | cut -d" " -f 5)"
listenport2="$(lsof -i:9094 | grep LISTEN | cut -d" " -f 5)"
listenport3="$(lsof -i:9095 | grep LISTEN | cut -d" " -f 5)"

echo $listenport1
echo $listenport2
echo $listenport3

while [[ "$listenport1" == "" ]]
do
	echo "\e[42mWaiting for 1 sec.\e[0m"
    sleep 1
    listenport1="$(lsof -i:9093 | grep LISTEN | cut -d" " -f 5)"
    listenport2="$(lsof -i:9094 | grep LISTEN | cut -d" " -f 5)"
    listenport3="$(lsof -i:9095 | grep LISTEN | cut -d" " -f 5)"
done
