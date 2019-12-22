package com.hyman.seckillMall.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: penghuang
 * @Date: 2019/12/20 22:13
 * @Version 1.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MailDto implements Serializable {
    // 邮件主题
    private String subject;

    // 邮件内容
    private String content;

    // 接收人
    private String[] tos;
}
