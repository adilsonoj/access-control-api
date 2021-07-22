package oj7.api.accesscontrol.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oj7.api.accesscontrol.model.NivelAcessoUsuario;
import oj7.api.accesscontrol.repository.NivelAcessoUsuarioRepository;

@Service
public class NivelAcessoUsuarioService {

  @Autowired
  NivelAcessoUsuarioRepository nauRepository;
  
  public List<NivelAcessoUsuario> findByCpf(String cpf) {
    return nauRepository.getByCpf(cpf);
  }

  @Transactional
  public NivelAcessoUsuario save(NivelAcessoUsuario nivelRequest) {
    return nauRepository.save(nivelRequest);
  }
}
