package com.hyman.seckillMall.server.service.impl;

import com.hyman.seckillMall.server.dto.MailDto;
import com.hyman.seckillMall.server.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * 邮件服务
 * @Author: penghuang
 * @Date: 2019/12/20 22:03
 * @Version 1.0
 */
@Service
@EnableAsync
public class MailServiceImpl implements MailService {
    private static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    // 注入系统环境配置变量
    @Autowired
    private Environment env;

    /**
     * 发送简单文本邮件
     */
    @Async
    @Override
    public void sendSimpleEmail(final MailDto dto){
        try{

            SimpleMailMessage message = new SimpleMailMessage();
            // 发件人
            message.setFrom(env.getProperty("mail.send.from"));
            // 收件人
            message.setTo(dto.getTos());
            // 主题
            message.setSubject(dto.getSubject());
            // 内容
            message.setText(dto.getContent());

            // 发送邮件
            mailSender.send(message);

            log.info("发送简单邮件文本-发送成功！");
        }catch(Exception e){
            log.error("发送简单文本文件-发生异常：",e.fillInStackTrace());
        }

    }

    /**
     * 发送HTML版邮件
     * @param dto
     */
    @Async
    @Override
    public void sendHTMLMail(MailDto dto) {
           try{

               MimeMessage message = mailSender.createMimeMessage();
               MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"utf-8");
               messageHelper.setFrom(env.getProperty("mail.send.from"));
               messageHelper.setTo(dto.getTos());
               messageHelper.setSubject(dto.getSubject());
               messageHelper.setText(dto.getContent(),true);
               mailSender.send(message);

               log.info("发送HTML邮件-发送成功!");
           }catch(Exception e){
               log.error("发送HTML邮件-发送邮件: ",e.fillInStackTrace());
           }
    }


}
