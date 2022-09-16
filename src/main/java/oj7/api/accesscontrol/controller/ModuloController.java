package oj7.api.accesscontrol.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.bean.CsvToBeanBuilder;

import lombok.RequiredArgsConstructor;
import oj7.api.accesscontrol.model.CsvModel;
import oj7.api.accesscontrol.model.Modulo;
import oj7.api.accesscontrol.service.ModuloService;

@RestController
@RequestMapping(path = "modulos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ModuloController {

  @Autowired
  ModuloService service;

  @GetMapping
  public ResponseEntity<List<Modulo>> listAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping(path="csv")
  public ResponseEntity<List<CsvModel>> csvRead(){
    try {
      List<CsvModel> beans = new CsvToBeanBuilder(new FileReader("/home/arsenal/yourfile.csv"))
      .withType(CsvModel.class).build().parse();

      return ResponseEntity.ok(beans);
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
