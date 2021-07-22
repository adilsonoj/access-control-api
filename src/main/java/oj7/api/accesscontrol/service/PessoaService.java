package oj7.api.accesscontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import oj7.api.accesscontrol.Exceptions.NoContentException;
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

  public Pessoa findById(PessoaKey key) {
    return repository.findById(key)
                  .orElseThrow(() -> new NoContentException("Pessoa não encontrada"));
  }

  public Page<Pessoa> listAllPageble(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public List<Pessoa> listLikeName(String name) {
    return repository.findByNmFuncStartsWith(name);
  }

  public List<Pessoa> findByNrNip(Long nip) {
    return repository.findByNrNip(nip);
  }

  public Pessoa findByCpf(String cpf){
    return repository.findByCpf(cpf)
                  .orElseThrow(() -> new NoContentException("Pessoa não encontrada"));
  }

  public Pessoa save(PessoaPostRequestBody pessoaPostRequestBody) {
    Pessoa pessoa = PessoaMapper.INSTANCE.toPessoa(pessoaPostRequestBody);
    return pessoa;
  }

  public String getToken(){
    return JwtUtil.createJwt();
  }
  
}
