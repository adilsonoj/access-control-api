package oj7.api.accesscontrol.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class NivelAcessoUsuarioKey implements Serializable{
  private static final long serialVersionUID = -1L;

  @Column(name = "NR_CPF_01")
	private String nrCpf01;
	
	@Column(name = "NR_CPF_02")
	private String nrCpf02;
	
	@Column(name = "NR_CPF_03")
	private String nrCpf03;

	@Column(name = "ID_DV_CPF")
	private String idDvCpf;

  @ManyToOne
  @JoinColumns({
    @JoinColumn(name="CD_PROJ", referencedColumnName="CD_PROJ"),
    @JoinColumn(name="ID_MODU", referencedColumnName="ID_MODU"),
    @JoinColumn(name="CD_NIVL_ACES", referencedColumnName="CD_NIVL_ACES"),
  })
  private NivelAcesso nivelAcesso;

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
