package oj7.api.accesscontrol.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import oj7.api.accesscontrol.model.NivelAcesso;
import oj7.api.accesscontrol.service.NivelAcessoService;

@RestController
@RequestMapping(path = "nivelacesso")
@RequiredArgsConstructor
public class NivelAcessoController {
  final NivelAcessoService naService;

  @GetMapping
  public ResponseEntity<List<NivelAcesso>> findByCdProjIdModu(@RequestParam String cdProj,
      @RequestParam String idModu) {
    return ResponseEntity.ok(naService.findByCdProjIdModu(cdProj, idModu));
  }
}
