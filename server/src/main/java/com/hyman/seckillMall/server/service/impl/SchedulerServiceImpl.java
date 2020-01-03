package com.hyman.seckillMall.server.service.impl;

import com.hyman.seckillMall.model.entity.ItemKillSuccess;
import com.hyman.seckillMall.model.mapper.ItemKillSuccessMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: penghuang
 * @Date: 2020/1/3 14:26
 * @Version 1.0
 */
@Service
public class SchedulerServiceImpl{
    private static final Logger log = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;

    @Autowired
    private Environment env;

    /**
     * 定时获取status=0的订单并判断是否超过TTL，然后进行失效
     */
    @Scheduled(cron="0/10 * * * * ?")
    public void schedulerExpireOrders(){
        try{
            List<ItemKillSuccess> list = itemKillSuccessMapper.selectExpireOrders();
            if(list != null && !list.isEmpty()){
                list.stream().forEach(i -> {
                        if(i!=null && i.getDiffTime() > env.getProperty("scheduler.expire.orders.time",Integer.class)){
                            itemKillSuccessMapper.expireOrder(i.getCode());
                        }
                });
            }

        }catch(Exception e){
            log.error("定时获取status=0的订单并判断是否超过TTL，然后进行失效-发生异常:",e.fillInStackTrace());
        }
    }

}
