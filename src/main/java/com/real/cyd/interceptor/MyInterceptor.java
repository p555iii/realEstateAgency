package com.real.cyd.interceptor;

import com.real.cyd.bean.SysPermissionTree;
import com.real.cyd.bean.SysUser;
import com.real.cyd.service.SysPermissionService;
import com.real.cyd.utils.SpringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Create in INtelliJ IDEA
 * Author cyd
 * Date   2018/1/4
 */
public class MyInterceptor implements HandlerInterceptor {
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
            /**
             * 保存登录信息
             */
            SysUser me = new SysUser();
            me.setId("7");//ShiroUtil.getSessionUser();
            if(me == null){
                return true;
            }
            me.setPassword("");
            request.setAttribute("me", me);
            /**
             * 资源和当前选中菜单
             */
            String res = request.getParameter("p");
            if (StringUtils.isNotBlank(res)) {
                request.getSession().setAttribute("res", res);
            }
            String cur = request.getParameter("t");
            if (StringUtils.isNotBlank(cur)) {
                request.getSession().setAttribute("cur", cur);
            }
            /**
             * 获取当前用户的菜单
             */

            List<SysPermissionTree> treeMenus = SpringUtils.getBean(SysPermissionService.class).selectTreeMenuByUserId(me.getId());
            //System.out.println("-0--------"+treeMenus.get(0).getSysPermission().getUrl());

            request.getSession().setAttribute("treeMenus", treeMenus);
            //request.setAttribute("treeMenus", treeMenus);

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
