package com.uwdp.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uwdp.utils.JwtUtil;
import com.uwdp.utils.NoToken;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 添加拦截器,拦截请求,校验token
 */
public class JWTInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull Object handler) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        HandlerMethod method = (HandlerMethod)handler;
        boolean methodAnno = method.getMethod().isAnnotationPresent(NoToken.class);
        boolean classAnno = method.getMethod().getDeclaringClass().isAnnotationPresent(NoToken.class);

        if (methodAnno) {
            return true;
        }
        if(classAnno){
            return true;
        }

        String token = request.getHeader("Authorization");  //从request中获取到请求头中的token,进行解析校验
        try {
            JwtUtil.verifyToken(token);//调用token解析的工具类进行解析
            return true;  //请求放行
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "签名不一致异常");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "令牌过期异常");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "算法不匹配异常");
        } catch (InvalidClaimException e) {
            e.printStackTrace();
            map.put("msg", "失效的payload异常");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "token无效");
        }
        //map异常的数据要返回给客户端需要转换成json格式  @ResponseBody 内置了jackson
        String resultJson = new ObjectMapper().writeValueAsString(map);
        response.getWriter().print(resultJson);
        return false;  //异常不放行
    }
}
