package oj7.api.accesscontrol.controller;

import java.util.List;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import oj7.api.accesscontrol.model.Pessoa;
import oj7.api.accesscontrol.model.PessoaKey;
import oj7.api.accesscontrol.requests.PessoaPostRequestBody;
import oj7.api.accesscontrol.service.PessoaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "pessoas")
@RequiredArgsConstructor
public class PessoaController {
  final PessoaService pessoaService;

  @GetMapping
  public ResponseEntity<List<Pessoa>> list(){
    return ResponseEntity.ok(pessoaService.list());
  }

  @PostMapping
  public ResponseEntity<Pessoa> save(PessoaPostRequestBody pessoaPostRequestBody){
    return ResponseEntity.ok(pessoaService.save(pessoaPostRequestBody));
  }

  @GetMapping(path = "likename")
  public ResponseEntity<List<Pessoa>> listLikeName(@RequestParam String name){
    return ResponseEntity.ok(pessoaService.listLikeName(name));
  }

  //ex.: pageable?size=5&page=1&sort=nmFunc
  @GetMapping(path = "pageable")
  public ResponseEntity<Page<Pessoa>> page(@ParameterObject Pageable pageable){
    return ResponseEntity.ok(pessoaService.listAllPageble(pageable));
  }

  @GetMapping(path = "find/{nip}/{nind}")
  public ResponseEntity<Pessoa> findById(@PathVariable Long nip, @PathVariable String nind){
    PessoaKey key = new PessoaKey(nip, nind);
    Pessoa pessoa = pessoaService.findById(key);
    return ResponseEntity.ok(pessoa);
  }

  @GetMapping(path = "findbynip")
  public ResponseEntity<List<Pessoa>> findbynip(@RequestParam Long nip){
    return ResponseEntity.ok(pessoaService.findByNrNip(nip));
  }

  @GetMapping(path = "findbycpf")
  public ResponseEntity<Pessoa> findByCpf(@RequestParam String cpf){
    Pessoa pessoa = pessoaService.findByCpf(cpf);
    return ResponseEntity.ok(pessoa);
  }

  // @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping(path = "token")
  public ResponseEntity<String> getToken(){
    return ResponseEntity.ok(pessoaService.getToken());
  }
}
