package com.rmi.server.util;

import org.springframework.lang.NonNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ServletStreamConverterUtil {

    public static javax.servlet.ServletInputStream convertInputStreamFromJakartaToJavax(jakarta.servlet.ServletInputStream stream) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while ((bytesRead = stream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return new javax.servlet.ServletInputStream() {
            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

    public static javax.servlet.ServletOutputStream convertOutputStreamFromJakartaToJavax(jakarta.servlet.ServletOutputStream stream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        return new javax.servlet.ServletOutputStream() {
            @Override
            public void write(int b) throws IOException {
                byteArrayOutputStream.write(b);
                stream.write(b);
            }

            @Override
            public void write(@NonNull byte[] b, int off, int len) throws IOException {
                byteArrayOutputStream.write(b, off, len);
                stream.write(b, off, len);
            }
        };
    }

}
