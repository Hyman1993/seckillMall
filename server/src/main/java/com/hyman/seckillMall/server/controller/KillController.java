package com.hyman.seckillMall.server.controller;

import com.hyman.seckillMall.api.enums.StatusCode;
import com.hyman.seckillMall.api.response.BaseResponse;
import com.hyman.seckillMall.server.dto.KillDto;
import com.hyman.seckillMall.server.service.KillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 秒杀controller
 * @Author: penghuang
 * @Date: 2019/11/9 23:06
 * @Version 1.0
 */
@Controller
public class KillController {
    private static final Logger log = LoggerFactory.getLogger(KillController.class);

    private static final String prefix = "secKill";

    @Autowired
    private KillService killService;

    /***
     * 商品秒杀核心业务逻辑
     * @param dto
     * @param result
     * @return
     */
    @RequestMapping(value = prefix+"/execute",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResponse execute(@RequestBody @Validated KillDto dto, BindingResult result, HttpSession session){
        if (result.hasErrors() || dto.getKillId()<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        Object uId=session.getAttribute("uid");
        //if (uId==null){
        //    return new BaseResponse(StatusCode.UserNotLogin);
        //}
        Integer userId=dto.getUserId();
        //Integer userId= (Integer)uId ;

        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //Boolean res=killService.killItem(dto.getKillId(),userId);
            Boolean res=killService.killItem(dto.getKillId(),userId);
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    @RequestMapping(value=prefix+"/execute/success",method=RequestMethod.GET)
    public String executeSuccess(){
        return "executeSuccess";
    }

    @RequestMapping(value=prefix+"/execute/fail",method = RequestMethod.GET)
    public String executeFail(){
        return "executeFail";
    }

}