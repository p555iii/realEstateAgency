package com.real.cyd.interceptor;

import com.real.cyd.bean.SysPermissionTree;
import com.real.cyd.bean.SysUser;
import com.real.cyd.service.SysPermissionService;
import com.real.cyd.utils.ShiroUtil;
import com.real.cyd.utils.SpringUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Create in INtelliJ IDEA
 * Author cyd
 * Date   2018/1/4
 */
public class MyInterceptor implements HandlerInterceptor {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    /**
     *  在请求处理之前进行调用  返回true才能继续执行
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {

            /**
             * 加载全局非登录访问常量
             */
           /* List<SysSetting> list =  SpringUtil.getBean(ISysSettingService.class).findAll();
            for(SysSetting setting : list){
                request.setAttribute(setting.getSysKey(),setting.getSysValue());
            }
*/
            //logger.error("dsdsdsdsssssssssssss");

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //System.out.println(">>>MyInterceptor1>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
