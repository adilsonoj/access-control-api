package oj7.api.accesscontrol.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import oj7.api.accesscontrol.model.Pessoa;
import oj7.api.accesscontrol.model.PessoaKey;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, PessoaKey>{

  public List<Pessoa> findByNmFuncStartsWith(String nmFunc);
  
  @Query("select p from Pessoa p where p.nrCpfFunc01||p.nrCpfFunc02||p.nrCpfFunc03||p.idDvCpfFunc = ?1")
  public Optional<Pessoa> findByCpf(String cpf);

  public List<Pessoa> findByNrNip(Long nrNip);
}
