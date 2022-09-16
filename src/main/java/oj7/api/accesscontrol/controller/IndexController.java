package oj7.api.accesscontrol.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opencsv.bean.CsvToBeanBuilder;

import oj7.api.accesscontrol.model.CsvModel;



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

 

//   public static void main(String[] args) {
//     try {
//       List<CsvModel> beans = new CsvToBeanBuilder(new FileReader("/home/arsenal/yourfile.csv"))
//       .withType(CsvModel.class).build().parse();
// System.out.println(beans);

//     } catch (IllegalStateException e) {
//       // e.printStackTrace();
//     } catch (FileNotFoundException e) {
//       System.out.println("arquivo nao encontrado");
//       // e.printStackTrace();
//     }
//   }

}
