package oj7.api.accesscontrol.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "ADVU_CODI_NIVL_ACES")
public class CodigoNivelAcesso implements Serializable{
  private static final long serialVersionUID = -1L;

  @Id
	@Column(name = "CD_NIVL_ACES")
	private Long cdNivlAces;
	
	@Column(name = "ID_NIVL_ACES")
	private String idNivlAces;
	
	@Column(name = "DE_NIVL_ACES")
	private String deNivlAces;
}
