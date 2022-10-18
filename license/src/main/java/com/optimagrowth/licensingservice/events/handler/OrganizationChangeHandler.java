package com.optimagrowth.licensingservice.events.handler;

import com.optimagrowth.licensingservice.events.CustomChannels;
import com.optimagrowth.licensingservice.events.model.OrganizationChangeModel;
import com.optimagrowth.licensingservice.repository.OrganizationRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(CustomChannels.class)
public class OrganizationChangeHandler {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationChangeHandler.class);

    private OrganizationRedisRepository redisRepository;

    @StreamListener("inboundOrgChanges")
	public void loggerSink(OrganizationChangeModel orgChange) {
		logger.debug("Received a message of type", orgChange.getType(),
				orgChange.getOrganizationId());
        logger.debug("Received a message with an event {} from the organization service for the organization id {}",
                orgChange.getType(), orgChange.getType());
	}
}
