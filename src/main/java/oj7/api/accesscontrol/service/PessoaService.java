package oj7.api.accesscontrol.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Claims;
import oj7.api.accesscontrol.Exceptions.BadRequestException;
import oj7.api.accesscontrol.mapper.PessoaMapper;
import oj7.api.accesscontrol.model.Pessoa;
import oj7.api.accesscontrol.model.PessoaKey;
import oj7.api.accesscontrol.repository.PessoaRepository;
import oj7.api.accesscontrol.requests.PessoaPostRequestBody;
import oj7.api.accesscontrol.util.JwtUtil;

@Service
public class PessoaService {

  @Autowired
  PessoaRepository repository;
  
  public List<Pessoa> list() {
    return repository.findAll();
  }

  // public Pessoa findById(PessoaKey key) {
  //   return repository.findById(key)
  //                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa não encontrada"));
  // }

  public Pessoa findById(PessoaKey key) {
    return repository.findById(key)
                  .orElseThrow(() -> new NoSuchElementException("Pessoa não encontrada"));
  }

  public Page<Pessoa> listAllPageble(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public List<Pessoa> listLikeName(String name) {
    return repository.findByNmFuncStartsWith(name);
  }

  public Pessoa findByCpf(String cpf){
    return repository.findByCpf(cpf);
  }

  public Pessoa save(PessoaPostRequestBody pessoaPostRequestBody) {
    Pessoa pessoa = PessoaMapper.INSTANCE.toPessoa(pessoaPostRequestBody);
    return pessoa;
  }

  public String getToken(){
    String jwt = JwtUtil.createJwt();
    return jwt;
  }
  
}
