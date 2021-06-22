package com.yisquare.springboot.controller;


import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


@Controller
@RequestMapping(value = "/api")
public class ForwardController {

    @Value("${proxy.path}")
    private String url;

    @PostMapping(value = "/graphql")
    public void forwardGraphql(HttpServletRequest request, HttpServletResponse response) {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            httpPost.addHeader(headerName, request.getHeader(headerName));
        }
        List<NameValuePair> params = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            for (String value : request.getParameterValues(parameterName)) {
                if (value!=null&&!value.equals("null")) params.add(new BasicNameValuePair(parameterName, value));
            }
        }

        HttpResponse httpResponse = null;
        try {
            InputStream inputstream =  request.getInputStream(); //获取post请求中的流，并直接以流的形式转发到新网关
            InputStreamEntity entity = new InputStreamEntity(inputstream);
            httpPost.setEntity(entity);
            httpPost.removeHeaders("Content-Length");
            httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null) {
                HttpEntity responseEntity = httpResponse.getEntity();
                if (responseEntity != null) {
                    responseEntity.writeTo(response.getOutputStream());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpResponse != null) {
            response.setStatus(httpResponse.getStatusLine().getStatusCode());
            //logger.info(httpResponse.toString());
            HeaderIterator headerIterator = httpResponse.headerIterator();
            while (headerIterator.hasNext()) {
                Header header = headerIterator.nextHeader();
                if (header.getName().equals("Content-Type")) {
                    //response.addHeader(header.getName(), header.getValue());
                    response.setHeader(header.getName(), header.getValue());
                }
            }
        }

}}
