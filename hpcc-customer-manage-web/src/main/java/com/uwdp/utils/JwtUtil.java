package com.uwdp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    //秘钥加密解密都需要用到,所以定义成静态常量
    public static final String SIGNATURE = "hcpc-min";

    /**
     * 生成token
     * @param map payload中需要放置的相关非敏感信息
     * @return    返回的生成的token信息
     */
    public static String getToken(Map<String,String> map) {
        //设置一个时间,作为令牌的过期时间 ,设置过期时间为2天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 2);

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //遍历map集合,将信息放到payload中
        map.forEach((k,v)->{
            builder.withClaim(k,v);  //payload信息
        });
        //header信息可以省略,因为默认已经有算法和类型了,也可以在headerMap中设置算法
        HashMap<String, Object> headerMap = new HashMap<>();
        String token = builder.withHeader(headerMap)     //header信息
                .withExpiresAt(calendar.getTime()) //token过期时间
                .sign(Algorithm.HMAC256(SIGNATURE));//签名
        return  token;
    }

    /**
     * 验证token DecodedJWT 为解密之后的对象 可以获取payload中添加的数据
     */
    public static DecodedJWT verifyToken(String token) {
        //进行token的校验,注意使用同样的算法和同样的秘钥
        return JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }

    /**
     * 获取token中payload中的添加的参数 演示
     * @return
     */
    public static DecodedJWT getTokenPayloadParam(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
        //Map<String, Claim> claims = verify.getClaims(); //获取payload中所有的参数
        //verify.getClaim("userName").asString();  //通过key获取value,转成对应的类型
        return verify;
    }

    public static void main(String[] args) {
        Map<String, String> userTokenMap = new HashMap<>();
        userTokenMap.put("mobile", "18373019955");
        String token = getToken(userTokenMap);
        System.out.println(token);

        System.out.println(JwtUtil.verifyToken(token));//调用token解析的工具类进行解析
    }
}
