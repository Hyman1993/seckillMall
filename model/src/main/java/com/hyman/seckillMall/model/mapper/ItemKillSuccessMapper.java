package com.hyman.seckillMall.model.mapper;


import com.hyman.seckillMall.model.entity.ItemKillSuccess;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemKillSuccessMapper {
    int deleteByPrimaryKey(String code);

    int insert(ItemKillSuccess record);

    int insertSelective(ItemKillSuccess record);

    ItemKillSuccess selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ItemKillSuccess record);

    int updateByPrimaryKey(ItemKillSuccess record);

    int countByKillUserId(@Param("killId") Integer killId, @Param("userId") Integer userId);

  //  KillSuccessUserInfo selectByCode(@Param("code") String code);

    int expireOrder(@Param("code") String code);

    List<ItemKillSuccess> selectExpireOrders();
}