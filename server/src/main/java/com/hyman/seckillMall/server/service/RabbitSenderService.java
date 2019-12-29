package com.hyman.seckillMall.server.service;

import org.springframework.stereotype.Service;

/**
 * RabbitMQ发送服务
 * @Author: penghuang
 * @Date: 2019/12/8 15:55
 * @Version 1.0
 */
public interface RabbitSenderService {

   void sendKillSuccessEmailMsg(String orderNo);

   void sendKillSuccessOrderExpireMsg(String orderCode);
}
