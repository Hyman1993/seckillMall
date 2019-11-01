package com.hyman.seckillMall.server.controller;

import com.hyman.seckillMall.model.entity.ItemKill;
import com.hyman.seckillMall.server.service.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author: penghuang
 * @Date: 2019/10/21 21:50
 * @Version 1.0
 */
@Controller
public class ItemController {
    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    private static final String prefix = "item";

    @Autowired
    private IItemService itemService;

    /**
     * 获取商品列表
     */
    @RequestMapping(value={"/","/index",prefix+"/list",prefix+"/index.html"},method= RequestMethod.GET)
    public String list(ModelMap modelMap){
        try{
            // 获取待秒杀商品列表
            List<ItemKill> list = itemService.getKillItems();
            modelMap.put("list",list);

            log.info("获取待秒杀商品列表-数据:{}",list);


        }catch(Exception e){
            log.error("获取待秒杀商品列表-发生异常:",e.fillInStackTrace());
            return "redirect:/base/error";
        }
      return "list";
    }
}
