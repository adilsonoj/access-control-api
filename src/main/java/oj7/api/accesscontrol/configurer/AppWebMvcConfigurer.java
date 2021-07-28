package oj7.api.accesscontrol.configurer;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppWebMvcConfigurer implements WebMvcConfigurer {
  
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    PageableHandlerMethodArgumentResolver pageableHandler = new PageableHandlerMethodArgumentResolver();
    pageableHandler.setFallbackPageable(PageRequest.of(1, 10));
    resolvers.add(pageableHandler);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("http://localhost:3000/");
  }
}
