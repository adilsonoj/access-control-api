package oj7.api.accesscontrol.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import oj7.api.accesscontrol.model.NivelAcessoUsuario;
import oj7.api.accesscontrol.service.NivelAcessoUsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "nivelusuario")
@RequiredArgsConstructor
public class NivelAcessoUsuarioController {
  final NivelAcessoUsuarioService nauService;

  @GetMapping
  public ResponseEntity<List<NivelAcessoUsuario>> findByCpf(@RequestParam String cpf){
    return ResponseEntity.ok(nauService.findByCpf(cpf));
  }

  @GetMapping(path = "findByCpfProj")
  public ResponseEntity<List<NivelAcessoUsuario>> findByCpfProj(@RequestParam String cpf, @RequestParam String cdProj, @RequestParam String idModu){
    return ResponseEntity.ok(nauService.findByCpfProj(cpf, cdProj, idModu));
  }

  @PostMapping
  public ResponseEntity<NivelAcessoUsuario> createNivelAcessoUsuario(@RequestBody NivelAcessoUsuario nivelRequest){
    return new ResponseEntity<>(nauService.save(nivelRequest), HttpStatus.CREATED);
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteNivelAcessoUsuario(@RequestBody NivelAcessoUsuario nivelRequest){
    nauService.delete(nivelRequest);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
