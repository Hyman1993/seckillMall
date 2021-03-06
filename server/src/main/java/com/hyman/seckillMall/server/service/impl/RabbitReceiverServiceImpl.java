package com.hyman.seckillMall.server.service.impl;

import com.hyman.seckillMall.model.dto.KillSuccessUserInfo;
import com.hyman.seckillMall.model.entity.ItemKillSuccess;
import com.hyman.seckillMall.model.mapper.ItemKillSuccessMapper;
import com.hyman.seckillMall.server.dto.MailDto;
import com.hyman.seckillMall.server.service.MailService;
import com.hyman.seckillMall.server.service.RabbitReceiverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @Author: penghuang
 * @Date: 2019/12/17 22:12
 * @Version 1.0
 */
@Service
public class RabbitReceiverServiceImpl implements RabbitReceiverService {

    public static final Logger log = LoggerFactory.getLogger(RabbitReceiverServiceImpl.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private Environment env;

    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;

    /**
     * 异步邮件通知-接收消息
     */

    @RabbitListener(queues={"${mq.kill.item.success.email.queue}"},containerFactory = "singleListenerContainer")
    @Override
    public void consumeEmailMsg(KillSuccessUserInfo info) {
           try{
              log.info("秒杀异步邮件通知-接收消息:{}",info);

              // TODO:真正的发送邮件
               // MailDto dto = new MailDto(env.getProperty("mail.kill.item.success.subject"),"这是测试内容",new String[]{info.getEmail()});

               // 发送简单邮件
               //mailService.sendSimpleEmail(dto);
                // 发送HTML邮件
               final String content = String.format(env.getProperty("mail.kill.item.success.content"),info.getItemName(),info.getCode());
               MailDto dto = new MailDto(env.getProperty("mail.kill.item.success.subject"),content,new String[]{info.getEmail()});

               mailService.sendHTMLMail(dto);

           }catch(Exception e){
               log.error("秒杀异步邮件通知-接收消息发生异常,消息为:",e.fillInStackTrace());
           }

    }

    /**
     * 用户秒杀成功后超时未支付-监听者
     * @param info
     */
    @RabbitListener(queues={"${mq.kill.item.success.kill.dead.real.queue}"},containerFactory = "singleListenerContainer")
    @Override
    public void consumeExpireOrder(KillSuccessUserInfo info) {
        try{
           log.info("用户秒杀成功后超时未支付-监听者-接收消息:{}",info);
           if(info != null){
              ItemKillSuccess entity = itemKillSuccessMapper.selectByPrimaryKey(info.getCode());
              // 更新失效订单
              if(entity != null && entity.getStatus().intValue() == 0){
                 itemKillSuccessMapper.expireOrder(info.getCode());
              }
           }


        }catch(Exception e){
            log.error("秒杀异步邮件通知-接收消息-发生异常：",e.fillInStackTrace());
        }
    }
}
