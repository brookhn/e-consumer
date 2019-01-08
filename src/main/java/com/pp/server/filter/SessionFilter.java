package com.pp.server.filter;

import brave.propagation.ExtraFieldPropagation;
import org.apache.catalina.servlet4preview.GenericFilter;
import org.springframework.cloud.sleuth.instrument.web.SleuthHttpProperties;
import org.springframework.cloud.sleuth.instrument.web.SleuthWebProperties;
import org.springframework.cloud.sleuth.instrument.web.TraceWebServletAutoConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Component
@Order(TraceWebServletAutoConfiguration.TRACING_FILTER_ORDER+1)
public class SessionFilter extends GenericFilter {

    private Pattern skipPattern = Pattern.compile(SleuthWebProperties.DEFAULT_SKIP_PATTERN);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)||
                !(response instanceof HttpServletResponse))
        {
            throw new ServletException("");
        }

        HttpServletRequest request1 = (HttpServletRequest)request;
        boolean skip = skipPattern.matcher(((HttpServletRequest) request).getRequestURI()).matches();
        if (!skip)
        {
            String sessionId = request1.getSession().getId();
            System.out.println(sessionId);
            ExtraFieldPropagation.set("SessionId", request1.getSession().getId());
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
