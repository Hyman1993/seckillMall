package com.hyman.seckillMall.server.service;

/**
 * @Author: penghuang
 * @Date: 2019/11/9 22:53
 * @Version 1.0
 */
public interface KillService {

    Boolean killItem(Integer killId,Integer userId) throws Exception;

    Boolean killItemV2(Integer killId,Integer userId) throws Exception;

    Boolean killItemV3(Integer killId,Integer userId) throws Exception;

    Boolean killItemV4(Integer killId,Integer userId) throws Exception;
}
