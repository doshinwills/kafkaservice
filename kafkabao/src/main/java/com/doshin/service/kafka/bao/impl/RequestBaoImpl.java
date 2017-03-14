package com.doshin.service.kafka.bao.impl;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doshin.service.kafka.bao.RequestBao;
import com.doshin.service.kafka.dao.RequestDao;
import com.doshin.service.kafka.dao.model.QueueDO;
import com.doshin.service.kafka.dao.model.RequestDO;
import com.doshin.service.kafka.dao.model.StatusDO;
import com.doshin.service.kafka.model.QueueVO;
import com.doshin.service.kafka.model.RequestVO;
import com.doshin.service.kafka.model.StatusVO;
import com.doshin.service.kafka.processor.producer.ProducerService;
import com.doshin.service.kafka.util.JaxbMarsheller;

@Service("requestBao")
@Transactional
public class RequestBaoImpl implements RequestBao {

	@Autowired
	RequestDao requestDao;


	@Override
	public RequestVO save(RequestVO request) {
		RequestDO requestDO = getRequestDoFromVo(request);
		requestDO = requestDao.save(requestDO);
		request = getRequestVoFromDo(requestDO);
		return request;
	}

	@Override
	public void update(RequestVO request) {
		RequestDO requestDO = getRequestDoFromVo(request);
		requestDao.update(requestDO);

	}

	@Override
	public void delete(RequestVO request) {
		RequestDO requestDO = getRequestDoFromVo(request);
		requestDao.delete(requestDO);
	}

	@Override
	public RequestVO findByRequestId(Integer requestId) {
		return getRequestVoFromDo(requestDao.findByRequestId(requestId));
	}

	private RequestVO getRequestVoFromDo(RequestDO requestDO) {
		RequestVO request;
		request = new RequestVO();
		BeanUtils.copyProperties(requestDO, request);
		StatusVO statusVo = new StatusVO();
		if (requestDO.getStatus() != null)
			BeanUtils.copyProperties(requestDO.getStatus(), statusVo);
		request.setStatus(statusVo);

		QueueVO queueVO = new QueueVO();
		if (requestDO.getQueue() != null)
			BeanUtils.copyProperties(requestDO.getQueue(), queueVO);
		request.setQueue(queueVO);
		return request;
	}

	private RequestDO getRequestDoFromVo(RequestVO request) {
		RequestDO requestDO = new RequestDO();
		BeanUtils.copyProperties(request, requestDO);
		StatusDO status = new StatusDO();
		if (request.getStatus() != null)
			BeanUtils.copyProperties(request.getStatus(), status);
		requestDO.setStatus(status);

		QueueDO queue = new QueueDO();
		if (request.getQueue() != null)
			BeanUtils.copyProperties(request.getQueue(), queue);
		requestDO.setQueue(queue);

		return requestDO;
	}

}
