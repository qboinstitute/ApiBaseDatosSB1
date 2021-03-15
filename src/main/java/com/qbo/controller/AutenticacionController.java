package com.qbo.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.qbo.model.Empleado;
import com.qbo.model.Usuario;
import com.qbo.service.EmpleadoService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("api/v1/autenticacion")
public class AutenticacionController {
		
	@Autowired
	EmpleadoService empleadoService;
	
	@PostMapping("")
	public Usuario login(@RequestParam("usuario")String usuario, 
			@RequestParam("password")String password) {
		Empleado empleado = empleadoService
				.autenticarEmpleado(usuario, password);
		if(empleado != null) {
			String token = generarToken(usuario);
			Usuario objusuario = new Usuario(empleado.getIdempleado(),
					empleado.getNombre(),
					token);
			return objusuario;
		}
		return null;
	}

	
	private String generarToken(String usuario) {
		String clavesecreta = "@QBO2021";// Trabajarlo desde base de datos. 
		List<GrantedAuthority> lstautorizacion = 
				AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		//Los roles de los usuarios deben cargarse de manera dínamica (base de datos)
		String token = Jwts
				.builder()
				.setId("@qboJWT")
				.setSubject(usuario)
				.claim("authorities", 
						lstautorizacion
						.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 300000))
				.signWith(SignatureAlgorithm.HS512, clavesecreta.getBytes())
				.compact();
		return "Bearer "+ token;
	}

}
