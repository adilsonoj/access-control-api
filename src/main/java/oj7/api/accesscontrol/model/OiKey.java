package oj7.api.accesscontrol.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OiKey implements Serializable{
	private String cdSupe;
	private String cdDept;
	private String cdDivs;
	private String cdScao;
	private String cdSetr;
}
