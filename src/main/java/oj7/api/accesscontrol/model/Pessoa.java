package oj7.api.accesscontrol.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="ADVU_PESS")
@IdClass(PessoaKey.class)
public class Pessoa {
  @Id
  @Column(name="nr_nip")
  private Long nrNip;

  @Id
  @Column(name="cd_nind")
  private String cdNind;

  @Column(name = "nr_cpf_func_01")
	private String nrCpfFunc01;
	
	@Column(name = "nr_cpf_func_02")
	private String nrCpfFunc02;
	
	@Column(name = "nr_cpf_func_03")
	private String nrCpfFunc03;
	
	@Column(name = "id_dv_cpf_func")
	private String idDvCpfFunc;
	
	@Column(name = "nm_func")
	private String nmFunc;

  @Column(name = "nm_mae")
	private String nmMae;

  @Column(name = "cd_uf_nasc")
	private String cdUfNasc;

  @Column(name = "dtNasc")
	private LocalDate dtNasc;

  @Column(name = "CD_RESC")
	private String cdResc;
}
