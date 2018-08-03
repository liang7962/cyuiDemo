package com.example.cyui.demo.filter;


import com.example.cyui.demo.util.SysContextUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @FileName: GetContent.java
 * @Description: 设置当前线程安全的request, response
 * @author: huangchuang
 * @version: v1.0
 * @create at: 2017年12月4日 下午6:22:18
 * @reviewer:
 * @review at:
 */
@WebFilter(filterName = "GetContent")
public class GetContent implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        SysContextUtil.setRequest((HttpServletRequest) request);
        SysContextUtil.setResponse((HttpServletResponse) response);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
