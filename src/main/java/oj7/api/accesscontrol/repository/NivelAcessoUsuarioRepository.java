package oj7.api.accesscontrol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import oj7.api.accesscontrol.model.NivelAcessoUsuario;
import oj7.api.accesscontrol.model.NivelAcessoUsuarioKey;

@Repository
public interface NivelAcessoUsuarioRepository extends JpaRepository<NivelAcessoUsuario, NivelAcessoUsuarioKey>{
  
  @Query("select p from NivelAcessoUsuario p where p.key.nrCpf01||p.key.nrCpf02||p.key.nrCpf03||p.key.idDvCpf = ?1")
  public List<NivelAcessoUsuario> getByCpf(String cpf);

}
