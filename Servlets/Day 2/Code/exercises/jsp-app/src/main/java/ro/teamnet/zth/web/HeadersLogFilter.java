package ro.teamnet.zth.web;

import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by user on 7/13/2016.
 */
public class HeadersLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        Enumeration headers =((HttpServletRequest)request).getHeaderNames();
      //  LogFileWriter log = new LogFileWriter();

        while (headers.hasMoreElements()){
            String header = headers.nextElement().toString();
            String value =  ((HttpServletRequest)request).getHeader(header);
            LogFileWriter.logHeader(header,value);
        }

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
