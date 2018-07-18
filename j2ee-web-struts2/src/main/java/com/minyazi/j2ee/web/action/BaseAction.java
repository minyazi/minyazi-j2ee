package com.minyazi.j2ee.web.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.minyazi.j2ee.core.util.LogUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 基础Action
 * 
 * @author minyazi
 */
public abstract class BaseAction extends ActionSupport implements ApplicationAware, SessionAware, RequestAware, ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;
    
    protected Map<String, Object> application;
    protected Map<String, Object> session;
    protected Map<String, Object> request;
    protected HttpServletRequest servletRequest;
    protected HttpServletResponse servletResponse;
    protected HttpSession httpSession;
    protected ServletContext servletContext;
    protected ApplicationContext applicationContext;
    
    protected String token; // 令牌
    protected String message; // 提示信息
    
    public BaseAction() {}
    
    @Override
    public void setApplication(Map<String, Object> application) {
        this.application = application;
        /*
        this.application = ServletActionContext.getContext().getApplication();
        this.application = ActionContext.getContext().getApplication();
        */
    }
    
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
        /*
        this.session = ServletActionContext.getContext().getSession();
        this.session = ActionContext.getContext().getSession();
        */
        
        ActionInvocation invocation = ActionContext.getContext().getActionInvocation();
        StringBuilder log = new StringBuilder(500);
        log.append("【Action】").append("（").append(session.get("Username")).append("）");
        log.append("（").append(invocation.getProxy().getNamespace()).append("/").append(invocation.getProxy().getActionName()).append("）");
        log.append(invocation.getAction().getClass().getName()).append(".").append(invocation.getProxy().getMethod()).append("()");
        LogUtil.info(log.toString());
    }
    
    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
        /*
        this.request = (Map<String, Object>) ServletActionContext.getContext().get("request");
        this.request = (Map<String, Object>) ActionContext.getContext().get("request");
        */
    }
    
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
        /*
        this.servletRequest = ServletActionContext.getRequest();
        */
        
        setHttpSession(servletRequest.getSession());
        setServletContext(httpSession.getServletContext());
        setApplicationContext(WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext));
    }
    
    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
        /*
        this.servletResponse = ServletActionContext.getResponse();
        */
    }
    
    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
        /*
        this.httpSession = ServletActionContext.getRequest().getSession();
        */
    }
    
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        /*
        this.servletContext = ServletActionContext.getServletContext();
        */
    }
    
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
