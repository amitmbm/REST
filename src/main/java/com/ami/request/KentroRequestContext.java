package com.ami.request;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;


public class KentroRequestContext {
    private String requestId;
    private long inTime;
    private String serviceUrl;
    private final Map<String, String[]> parameterMap;
    private final String contextPath;
    private final String queryString;
    private final String method;

    public KentroRequestContext(HttpServletRequest request) {
        this.requestId = UUID.randomUUID().toString();
        this.serviceUrl = request.getRequestURI();
        this.inTime = System.currentTimeMillis();
        parameterMap = request.getParameterMap();
        contextPath = request.getContextPath();
        queryString = request.getQueryString();
        method = request.getMethod();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public long getInTime() {
        return inTime;
    }

    public void setInTime(long inTime) {
        this.inTime = inTime;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    public String getMethod() {
        return method;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getContextPath() {
        return contextPath;
    }

    @Override
    public String toString() {
        return "KentroRequestContext{" +
                "requestId='" + requestId + '\'' +
                ", inTime=" + inTime +
                ", serviceUrl='" + serviceUrl + '\'' +
                ", parameterMap=" + parameterMap +
                ", contextPath='" + contextPath + '\'' +
                ", queryString='" + queryString + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
