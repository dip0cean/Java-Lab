package com.spring.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;

@RestController
public class ArgumentsController {

    @GetMapping("/springRequest")
    public String[] webRequest(WebRequest webRequest, NativeWebRequest nativeWebRequest) {
        return new String[]{webRequest.getClass().getName(), nativeWebRequest.getClass().getName()};
    }

    @GetMapping("javaServlet")
    public String[] javaServlet(ServletRequest request, ServletResponse response) {
        return new String[]{request.getClass().getName(), response.getClass().getName()};
    }

    @GetMapping("/javaHttpServlet")
    public String[] javaHttpServlet(HttpServletRequest request, HttpServletResponse response) {
        return new String[]{request.getClass().getName(), response.getClass().getName()};
    }

    @GetMapping("/stream")
    public String[] stream(InputStream inputStream, OutputStream outputStream) {
        return new String[]{inputStream.getClass().getName(), outputStream.getClass().getName()};
    }

    @GetMapping("/readerAndWriter")
    public void readerAndWriter(HttpServletRequest request, HttpServletResponse response, Reader reader, Writer writer) throws IOException {


        String[] classNames = new String[]{request.getReader().getClass().getName(), response.getWriter().getClass().getName(), reader.getClass().getName(), writer.getClass().getName()};

        request.getReader().close();
        response.getWriter().close();
        reader.close();
        writer.close();

        System.out.println(Arrays.toString(classNames));
        /*
            void 인 이유 > 핸들러 메소드의 리턴이 정의되어 있다면 Servlet 에 의해
                          OutputStream 이 열리게 되는데, 이 때 Writer 및 하위 클래스들과 중복이 되서
                          stream 이 항상 열려 있다는 에러를 떨어트린다.
         */
    }
}
