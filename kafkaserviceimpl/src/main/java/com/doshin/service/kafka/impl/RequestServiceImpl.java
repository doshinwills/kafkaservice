package com.doshin.service.kafka.impl;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

import com.doshin.service.kafka.api.RequestService;
import com.doshin.service.kafka.bao.RequestBao;
import com.doshin.service.kafka.model.RequestVO;
import com.doshin.service.kafka.processor.producer.ProducerService;
import com.doshin.service.kafka.util.JaxbMarsheller;

@Path("/request")
public class RequestServiceImpl implements RequestService{
	
	@Autowired
	RequestBao requestBao;
	
	@Autowired
	ProducerService producerService;

	@Override
	public RequestVO save(RequestVO request) {
		request = requestBao.save(request);
		producerService.sendRequest(JaxbMarsheller.marshal(request, request.getClass()));
		return request;
	}

	@Override
	public void update(RequestVO request) {
		requestBao.update(request);
	}

	@Override
	public void delete(RequestVO request) {
		requestBao.delete(request);;
	}

	@Override
	public RequestVO findByRequestId(Integer requestId) {
		return requestBao.findByRequestId(requestId);
	}

}