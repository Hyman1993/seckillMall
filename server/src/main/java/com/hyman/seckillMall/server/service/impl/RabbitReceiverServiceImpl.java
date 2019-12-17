package com.hyman.seckillMall.server.service.impl;

import com.hyman.seckillMall.model.dto.KillSuccessUserInfo;
import com.hyman.seckillMall.server.service.RabbitReceiverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author: penghuang
 * @Date: 2019/12/17 22:12
 * @Version 1.0
 */
@Service
public class RabbitReceiverServiceImpl implements RabbitReceiverService {

    public static final Logger log = LoggerFactory.getLogger(RabbitReceiverServiceImpl.class);

    /**
     * 异步邮件通知-接收消息
     */

    @RabbitListener(queues={"${mq.kill.item.success.email.queue}"},containerFactory = "singleListenerContainer")
    @Override
    public void consumeEmailMsg(KillSuccessUserInfo info) {
           try{
              log.info("秒杀异步邮件通知-接收消息:{}",info);

              // TODO:真正的发送邮件

           }catch(Exception e){
               log.error("秒杀异步邮件通知-接收消息发生异常,消息为:",e.fillInStackTrace());
           }

    }
}
