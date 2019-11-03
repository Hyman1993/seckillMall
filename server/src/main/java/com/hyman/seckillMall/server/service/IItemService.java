package com.hyman.seckillMall.server.service;

import com.hyman.seckillMall.model.entity.Item;
import com.hyman.seckillMall.model.entity.ItemKill;

import java.util.List;

/**
 * @Author: penghuang
 * @Date: 2019/10/21 22:02
 * @Version 1.0
 */
public interface IItemService {

     List<ItemKill> getKillItems() throws Exception;

     ItemKill getKillDetail(Integer id) throws Exception;
}
