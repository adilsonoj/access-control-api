package oj7.api.accesscontrol.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UrlPathHelper;

public class LoginInterceptor implements HandlerInterceptor{
  UrlPathHelper urlPathHelper = new UrlPathHelper();
  @Override
  public boolean preHandle(
     HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      // System.out.println("interceptor");

      // if("/login".equals(urlPathHelper.getLookupPathForRequest(request))){
      //   System.out.println(urlPathHelper.getLookupPathForRequest(request));
      //   System.out.println(request.getQueryString().contains("idSess"));
      //   System.out.println(request.getParameter("idSess"));
      //   //System.out.println(request.authenticate(arg0));
      //   return true;
      // }


      // System.out.println(urlPathHelper.getLookupPathForRequest(request));
      // System.out.println(request.getContextPath());
      // String encodedRedirectURL = response.encodeRedirectURL(
      //         request.getContextPath() + "/login?idSess=asdfasf4sf4s4f4sf4s4fsaf4s5a4f");
      //       response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
      //       response.setHeader("Location", encodedRedirectURL);

     return true;
  }
}
