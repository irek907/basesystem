package com.tangzongyun.basesystem.sys.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.tangzongyun.basesystem.com.BaseEntry;

@Entity
@Data
//@Log4j
@NoArgsConstructor
@AllArgsConstructor
@Table(name="sys_menu")
public class Menu implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String menuName;
	private String menuurl;

}
