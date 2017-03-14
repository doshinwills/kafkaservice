package com.doshin.service.kafka.processor.consumer;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

import com.doshin.service.kafka.model.QueueVO;
import com.doshin.service.kafka.model.RequestVO;
import com.doshin.service.kafka.processor.callback.ResponseCallback;
import com.doshin.service.kafka.util.JaxbUnmarshaller;
import com.doshin.service.kafka.util.ServiceExecutor;

public class ConsumerService {

	static final Logger LOG = LoggerFactory.getLogger(ConsumerService.class);

	@Autowired
	ResponseCallback responseCallback;

	public CountDownLatch countDownLatch0 = new CountDownLatch(3);
	public CountDownLatch countDownLatch1 = new CountDownLatch(3);
	public CountDownLatch countDownLatch2 = new CountDownLatch(3);

	@KafkaListener(id = "id0", topicPartitions = { @TopicPartition(topic = "SpringKafkaTopic", partitions = { "0" }) })
	public void listenPartition0(ConsumerRecord<?, ?> record) {

		RequestVO requestVO = null;
		try {
			String requetVoXml = (String) record.value();
			requestVO = JaxbUnmarshaller.unmarshal(requetVoXml);
			requestVO = responseCallback.sendRequest(requestVO);
			QueueVO queueVO = requestVO.getQueue();
			String responseXml = ServiceExecutor.execute(requestVO.getRequest(), queueVO.getServiceUrl());
			requestVO.setResponse(responseXml);
			requestVO = responseCallback.recivedResponse(requestVO);
			ServiceExecutor.postXml(responseXml, queueVO.getCallbackUrl());
			requestVO = responseCallback.responsed(requestVO);
			countDownLatch0.countDown();
		} catch (Exception e) {
			LOG.error("Failure", e);
			requestVO = responseCallback.requestFailed(requestVO);
		}
	}

	@KafkaListener(id = "id1", topicPartitions = { @TopicPartition(topic = "SpringKafkaTopic", partitions = { "1" }) })
	public void listenPartition1(ConsumerRecord<?, ?> record) {
		RequestVO requestVO = null;
		try {
			String requetVoXml = (String) record.value();
			requestVO = JaxbUnmarshaller.unmarshal(requetVoXml);
			requestVO = responseCallback.sendRequest(requestVO);
			QueueVO queueVO = requestVO.getQueue();
			String responseXml = ServiceExecutor.execute(requestVO.getRequest(), queueVO.getServiceUrl());
			requestVO.setResponse(responseXml);
			requestVO = responseCallback.recivedResponse(requestVO);
			ServiceExecutor.postXml(responseXml, queueVO.getCallbackUrl());
			requestVO = responseCallback.responsed(requestVO);
			countDownLatch0.countDown();
		} catch (Exception e) {
			LOG.error("Failure", e);
			requestVO = responseCallback.requestFailed(requestVO);
		}
	}

	@KafkaListener(id = "id2", topicPartitions = { @TopicPartition(topic = "SpringKafkaTopic", partitions = { "2" }) })
	public void listenPartition2(ConsumerRecord<?, ?> record) {
		RequestVO requestVO = null;
		try {
			String requetVoXml = (String) record.value();
			requestVO = JaxbUnmarshaller.unmarshal(requetVoXml);
			requestVO = responseCallback.sendRequest(requestVO);
			QueueVO queueVO = requestVO.getQueue();
			String responseXml = ServiceExecutor.execute(requestVO.getRequest(), queueVO.getServiceUrl());
			requestVO.setResponse(responseXml);
			requestVO = responseCallback.recivedResponse(requestVO);
			ServiceExecutor.postXml(responseXml, queueVO.getCallbackUrl());
			requestVO = responseCallback.responsed(requestVO);
			countDownLatch0.countDown();
		} catch (Exception e) {
			LOG.error("Failure", e);
			requestVO = responseCallback.requestFailed(requestVO);
		}
	}
}