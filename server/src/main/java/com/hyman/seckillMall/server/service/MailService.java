package com.hyman.seckillMall.server.service;

import com.hyman.seckillMall.server.dto.MailDto;

/**
 * 邮件服务
 * @Author: penghuang
 * @Date: 2019/12/20 22:03
 * @Version 1.0
 */
public interface MailService {
   void sendSimpleEmail(final MailDto dto);

   void sendHTMLMail(final MailDto dto);
}
