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
  @Column(name="NR_NIP")
  private Long nrNip;

  @Id
  @Column(name="CD_NIND")
  private String cdNind;

  @Column(name = "NR_CPF_FUNC_01")
	private String nrCpfFunc01;
	
	@Column(name = "NR_CPF_FUNC_02")
	private String nrCpfFunc02;
	
	@Column(name = "NR_CPF_FUNC_03")
	private String nrCpfFunc03;
	
	@Column(name = "ID_DV_CPF_FUNC")
	private String idDvCpfFunc;
	
	@Column(name = "NM_FUNC")
	private String nmFunc;

  @Column(name = "NM_MAE")
	private String nmMae;

  @Column(name = "CD_UF_NASC")
	private String cdUfNasc;

  @Column(name = "DT_NASC")
	private LocalDate dtNasc;

  @Column(name = "CD_RESC")
	private String cdResc;

  @Column(name = "CD_SUPE")
	private String cdSupe;

  @Column(name = "CD_DEPT")
	private String cdDept;

  @Column(name = "CD_DIVS")
	private String cdDivs;

  @Column(name = "CD_SCAO")
	private String cdScao;
  
  @Column(name = "CD_SETR")
	private String cdSetr;

  
}
