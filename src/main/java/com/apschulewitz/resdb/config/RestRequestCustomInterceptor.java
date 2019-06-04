package com.apschulewitz.resdb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;


@Slf4j
public class RestRequestCustomInterceptor implements HandlerInterceptor {

  private static final String[] HEADERS_TO_LOG = { "accept-language", "accept-encoding", "referer", "content-type", "user-agent", "accept" };

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    log.info("Pre-processing REST request to url {} Auth type {}  Method {} from Remote user {} on host {}", request.getRequestURL(), request.getAuthType(), request.getMethod(), request.getRemoteUser(), request.getRemoteHost());

    Arrays.stream(HEADERS_TO_LOG).forEach(h -> log.info("\tHeader: {} = {}", h, request.getHeader(h)));

//    log.info("\tRemote host = {} on local/remote/server port {} from user {}", request.getRemoteHost(), request.getLocalPort(), request.getRemotePort(), request.getServerPort());
//    Collections.list(request.getHeaderNames()).forEach(h -> log.info("\tHeader [{}] = {}", h, request.getHeader(h)));

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  {
    log.info("Post-processing for REST request to url {} - response content-type {} status {}", request.getRequestURL(), response.getContentType(), response.getStatus());
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  {
    log.info("After-processing REST request {} - response {} exception {}", request, response, ex);
  }
}
