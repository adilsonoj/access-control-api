package oj7.api.accesscontrol.model;

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
@Table(name="ADVU_MODU")
@IdClass(ModuloKey.class)
public class Modulo {
  @Id
  @Column(name = "CD_PROJ")
	private String cdProj;

  @Id
  @Column(name = "ID_MODU")
	private String idModu;

  @Column(name = "DE_MODU")
	private String deModu;
  
}
