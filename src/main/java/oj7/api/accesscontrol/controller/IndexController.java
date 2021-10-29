package oj7.api.accesscontrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(path = "/")
public class IndexController {

  @RequestMapping()
  public String index(){
    // Autenticacao auth = new Autenticacao();
    // auth.setCdNind("00");
    // System.out.println(auth.getCdNind());
    return "pages/perfil/index.html";
  }

  @RequestMapping(path="login")
  public String login(){
    return "pages/login/index.html";
  }
}
