package oj7.api.accesscontrol.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuloKey implements Serializable{
  private String cdProj;
	private String idModu;
}
