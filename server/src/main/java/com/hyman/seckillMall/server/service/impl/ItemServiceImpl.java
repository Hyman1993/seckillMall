package com.hyman.seckillMall.server.service.impl;

import com.hyman.seckillMall.model.entity.Item;
import com.hyman.seckillMall.model.entity.ItemKill;
import com.hyman.seckillMall.model.mapper.ItemKillMapper;
import com.hyman.seckillMall.server.service.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: penghuang
 * @Date: 2019/10/21 22:03
 * @Version 1.0
 */
@Service
public class ItemServiceImpl implements IItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemKillMapper itemKillMapper;

    /**
     * 获取待秒杀商品列表
     * @return
     * @throws Exception
     */
    @Override
    public List<ItemKill> getKillItems() throws Exception {
        return itemKillMapper.selectAll();
    }
}
