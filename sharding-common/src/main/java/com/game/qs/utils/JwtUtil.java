package com.game.qs.utils;

import com.game.qs.base.model.JwtUser;
import com.game.qs.constant.StrConst;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

/**
 * Created by zun.wei on 2019/4/12 15:34.
 * Description: jwt 工具类
 */
@Slf4j
public class JwtUtil implements Serializable {

    /**
     * 用户登录成功后生成Jwt
     * 使用Hs256算法  私匙使用用户密码
     *
     * @param ttlMillis jwt过期时间(毫秒)
     * @param user      登录成功的user对象
     * @return
     */
    public static String createJWT(long ttlMillis, JwtUser user) {
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = new HashMap<>();

        claims.put(StrConst.REQ_MID, user.getMid());
        claims.put(StrConst.PHONE, user.getPhone());
        claims.put(StrConst.JWT_KEY, user.getJwtKey());
        claims.put(StrConst.ROLE_TYPE, user.getRoleType());

        //生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        String key = user.getJwtKey();

        //生成签发人
        String subject = user.getPhone();


        //下面就是在为payload添加各种标准声明和私有声明了
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(UUID.randomUUID().toString())
                //iat: jwt的签发时间
                .setIssuedAt(now)
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            //设置过期时间
            builder.setExpiration(exp);
        }
        return builder.compact();
    }


    /**
     * Token的解密
     *
     * @param token 加密后的token
     * @param jwtKey  jwt 秘钥
     * @return
     */
    public static Claims parseJWT(String token, String jwtKey) {
        //得到DefaultJwtParser
        return Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(jwtKey)
                //设置需要解析的jwt
                .parseClaimsJws(token).getBody();
    }

    public static Claims parseJWT(String token, JwtUser user) {
        Claims claims = parseJWT(token, user.getJwtKey());
        user.setMid(claims.get(StrConst.REQ_MID) + StrConst.EMPTY_STR)
                .setPhone(claims.get(StrConst.PHONE) + StrConst.EMPTY_STR)
                .setJwtKey(claims.get(StrConst.JWT_KEY) + StrConst.EMPTY_STR)
                .setRoleType(claims.get(StrConst.ROLE_TYPE, List.class));
        return claims;
    }

    public static JwtUser parseJwtUser(Claims claims) {
        JwtUser user = new JwtUser()
                .setMid(claims.get(StrConst.REQ_MID) + StrConst.EMPTY_STR)
                .setPhone(claims.get(StrConst.PHONE) + StrConst.EMPTY_STR)
                .setJwtKey(claims.get(StrConst.JWT_KEY) + StrConst.EMPTY_STR)
                .setRoleType(claims.get(StrConst.ROLE_TYPE, List.class));
        return user;
    }

    /**
     *  从 token 中获取 jwtUser 对象
     * @param jwtKey 秘钥
     * @return JwtUser对象
     */
    public static JwtUser getJwtUser(HttpServletRequest httpServletRequest, String jwtKey) {
        String token = httpServletRequest.getHeader(StrConst.TOKEN);
        if (StringUtils.isBlank(token)) return new JwtUser();

        Claims claims = parseJWT(token, jwtKey);
        return new JwtUser().setMid(claims.get(StrConst.REQ_MID) + StrConst.EMPTY_STR)
                .setJwtKey(jwtKey)
                .setPhone(claims.get(StrConst.PHONE) + StrConst.EMPTY_STR)
                .setRoleType(claims.get(StrConst.ROLE_TYPE, List.class));
    }

    /**
     * 校验token
     * 在这里可以使用官方的校验，我这里校验的是token中携带的密码于数据库一致的话就校验通过
     *
     * @param token 加密后的token
     * @param user 登录成功的user对象
     * @return 校验成功或者失败
     */
    public static Boolean isVerify(String token, JwtUser user) {
        //签名秘钥，和生成的签名的秘钥一模一样
        String key = user.getJwtKey();
        //得到DefaultJwtParser
        Claims claims = parseJWT(token, key);
        return isVerify(claims, user);
    }


    public static Boolean isVerify(Claims claims, JwtUser user) {
        Object obj = claims.get(StrConst.REQ_MID);
        if (Objects.isNull(obj))
            return false;
        return obj.equals(user.getMid());
    }

}
