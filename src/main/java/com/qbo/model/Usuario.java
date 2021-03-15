package com.qbo.model;

public class Usuario {
	
	private Long idusuario;
	private String nomusuario;
	private String token;
	
	public Usuario(Long idusuario, String nomusuario, String token) {
		super();
		this.idusuario = idusuario;
		this.nomusuario = nomusuario;
		this.token = token;
	}
	public Long getIdusuario() {
		return idusuario;
	}
	public String getNomusuario() {
		return nomusuario;
	}
	public String getToken() {
		return token;
	}
	

}
