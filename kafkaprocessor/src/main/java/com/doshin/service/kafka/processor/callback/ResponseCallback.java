package com.doshin.service.kafka.processor.callback;

import com.doshin.service.kafka.model.QueueVO;
import com.doshin.service.kafka.model.RequestVO;

public interface ResponseCallback {
	RequestVO enquededRequest(RequestVO requestVO);
	RequestVO sendRequest(RequestVO requestVO);
	RequestVO recivedResponse(RequestVO requestVO);
	RequestVO requestFailed(RequestVO requestVO);
	RequestVO responsed(RequestVO requestVO);
	QueueVO getQueue(Integer queueId);


}
