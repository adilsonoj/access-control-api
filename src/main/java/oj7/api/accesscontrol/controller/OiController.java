package oj7.api.accesscontrol.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import oj7.api.accesscontrol.model.Oi;
import oj7.api.accesscontrol.service.OiService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "oi")
@RequiredArgsConstructor
public class OiController {
  final OiService service;

  @GetMapping(path = "like")
  public ResponseEntity<List<Oi>> findLikeOi(@RequestParam String oi){
    return ResponseEntity.ok(service.findLikeOi(oi));
  }
  
}
