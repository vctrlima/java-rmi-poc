package com.rmi.server.exporter;

import com.rmi.server.util.ServletStreamConverterUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.util.Assert;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;

public class SampleExporter extends HessianServiceExporter implements HttpRequestHandler {

    public SampleExporter() {
        super();
    }

    public Object skeletonInvoker() {
        try {
            Field field = HessianServiceExporter.class.getDeclaredField("skeletonInvoker");
            field.setAccessible(true);
            return field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            return null;
        }
    }

    @Override
    public void handleRequest(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response) throws ServletException {
        Object skeletonInvoker = skeletonInvoker();
        Assert.notNull(skeletonInvoker, "HessianServiceExporter has not been initialized");
        if (!"POST".equals(request.getMethod())) {
            throw new HttpRequestMethodNotSupportedException("POST", Collections.singleton("HessianServiceExporter only supports POST requests"));
        }
        try {
            Method invokeMethod = skeletonInvoker.getClass().getMethod("invoke", InputStream.class, OutputStream.class);
            invokeMethod.setAccessible(true);
            ServletInputStream inputStream = ServletStreamConverterUtil.convertInputStreamFromJakartaToJavax(request.getInputStream());
            ServletOutputStream outputStream = ServletStreamConverterUtil.convertOutputStreamFromJakartaToJavax(response.getOutputStream());
            invokeMethod.invoke(skeletonInvoker, inputStream, outputStream);
        } catch (Throwable throwable) {
            throw new ServletException("Hessian skeleton invocation failed", throwable);
        }
    }

}
