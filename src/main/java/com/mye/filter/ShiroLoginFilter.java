package com.mye.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author hqq
 * @date 2019-04-24 14:38
 **/
public class ShiroLoginFilter extends FormAuthenticationFilter {
    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        if (!isBrowser(httpRequest) || !isAjax(httpRequest)) {
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        } else {
            /**
             * @Mark 非ajax请求重定向为登录页面
             */
            httpServletResponse.sendRedirect("/v1/unauth");
        }
        return false;
    }

    public static boolean isBrowser(HttpServletRequest request) {
        List<MimeType> accept = MimeTypeUtils.parseMimeTypes(request.getHeader(HttpHeaders.ACCEPT));
        return accept.contains(MimeTypeUtils.TEXT_HTML);
    }

    private boolean isAjax(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
