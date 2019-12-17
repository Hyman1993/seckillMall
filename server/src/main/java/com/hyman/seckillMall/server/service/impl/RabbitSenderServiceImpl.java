package com.hyman.seckillMall.server.service.impl;

import com.hyman.seckillMall.model.dto.KillSuccessUserInfo;
import com.hyman.seckillMall.model.mapper.ItemKillSuccessMapper;
import com.hyman.seckillMall.server.service.RabbitSenderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Rabbit接收消息服务
 * @Author: penghuang
 * @Date: 2019/12/8 15:57
 * @Version 1.0
 */
@Service
public class RabbitSenderServiceImpl implements RabbitSenderService {
    public static final Logger log = LoggerFactory.getLogger(RabbitSenderServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;

    /**
     * 秒杀成功异步发送邮件通知消息
     */
    @Override
    public void sendKillSuccessEmailMsg(String orderNo){
        log.info("秒杀成功异步发送邮件通知消息-准备发送消息:{}",orderNo);

        try{
            if(StringUtils.isNotBlank(orderNo)){
                KillSuccessUserInfo info = itemKillSuccessMapper.selectByCode(orderNo);

                if(info != null){
                    // TODO:rabbitmq发送消息的逻辑
                    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                    rabbitTemplate.setExchange(env.getProperty("mq.kill.item.success.email.exchange"));
                    rabbitTemplate.setRoutingKey(env.getProperty("mq.kill.item.success.email.routing.key"));

                    // TODO:将info充当消息发送至队列
                    Message message = MessageBuilder.withBody(orderNo.getBytes("UTF-8")).build();

                    rabbitTemplate.convertAndSend(info, new MessagePostProcessor() {
                        @Override
                        public Message postProcessMessage(Message message) throws AmqpException {
                            MessageProperties messageProperties = message.getMessageProperties();
                            messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                            messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,KillSuccessUserInfo.class);
                            return message;
                        }
                    });
                }
            }

        }catch(Exception e){
            log.error("秒杀成功异步发送邮件通知消息-发生异常，消息：{}",orderNo,e.fillInStackTrace());
        }

    }
}

