package com.hyman.seckillMall.model.dto;

import com.hyman.seckillMall.model.entity.ItemKillSuccess;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: penghuang
 * @Date: 2019/12/17 21:54
 * @Version 1.0
 */
@Data
@ToString
public class KillSuccessUserInfo extends ItemKillSuccess implements Serializable {

    private String username;

    private String phone;

    private String email;

    private String itemName;

}
