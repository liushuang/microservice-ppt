package org.newit.microservice.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StopWatch;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DemoInterceptor extends HandlerInterceptorAdapter {

    ThreadLocal<StopWatch> stopWatch = new ThreadLocal<StopWatch>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        StopWatch s = new StopWatch();
        s.start();
        stopWatch.set(s);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        stopWatch.get().stop();
        System.out.println("user time:" + stopWatch.get().getTotalTimeMillis() + "ms");
        super.postHandle(request, response, handler, modelAndView);
    }
}
