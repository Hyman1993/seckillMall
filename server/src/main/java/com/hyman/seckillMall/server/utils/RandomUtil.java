package com.hyman.seckillMall.server.utils;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数生成util
 * @Author: penghuang
 * @Date: 2019/11/9 22:34
 * @Version 1.0
 */
public class RandomUtil {

    private static final SimpleDateFormat dateFormatOne = new SimpleDateFormat("yyyyMMddHHmmssSS");

    // 线程安全:适用于高并发情况下
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * 生成订单编号：方式一 时间戳+UUID
     * @return
     */
    public static String generateOrderCode(){

         // 时间戳+N位随机数流水号
         return  dateFormatOne.format(DateTime.now().toDate()) + generateNumber(4);

    }

    public static String generateNumber(final int num){

        StringBuffer sb = new StringBuffer();

        for(int i =1 ;i<num;i++){
            sb.append(random.nextInt(9));
        }

        return sb.toString();
    }

    public static void main(String[] args){
        for(int i=0;i < 10000;i++){
            System.out.println(generateOrderCode());
        }
    }
}
