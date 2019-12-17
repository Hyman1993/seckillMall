package com.hyman.seckillMall.server.service;

import com.hyman.seckillMall.model.dto.KillSuccessUserInfo;
import org.springframework.stereotype.Service;

/**
 * @Author: penghuang
 * @Date: 2019/12/17 22:11
 * @Version 1.0
 */
public interface RabbitReceiverService {

    void consumeEmailMsg(KillSuccessUserInfo info);
}
