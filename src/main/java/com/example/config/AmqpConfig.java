package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

//	@Bean
//	RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//		return new RabbitAdmin(connectionFactory);
//	}
//
//	/**
//	 * 生产者用
//	 * 
//	 * @return
//	 */
//	@Bean
//	public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
//		RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
//		rabbitMessagingTemplate.setMessageConverter(jackson2Converter());
//		rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
//		return rabbitMessagingTemplate;
//	}
//
//	@Bean
//	public MappingJackson2MessageConverter jackson2Converter() {
//		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//		return converter;
//	}

}