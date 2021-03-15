package com.qbo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import com.qbo.model.Estado;
import com.qbo.repository.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService implements BaseService<Estado> {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public List<Estado> findAll() {
		List<Estado> entities = estadoRepository.findAll(); 	
		return entities;
	}

	@Override
	public Optional<Estado> findById(Long id) {
		Optional<Estado> entityOptional = estadoRepository.findById(id);
		if(entityOptional.isEmpty()) {
			return Optional.empty();
		}else {
			return entityOptional;
		}
	}

	@Override
	public Estado save(Estado entity) {
		return estadoRepository.save(entity);
	}


	@Override
	public HashMap<String, String> deleteById(Long id) {
		HashMap<String, String> respuesta = new HashMap<>();
		estadoRepository.deleteById(id);
		respuesta.put("mensaje", "Elemento eliminado correctamente");
		return respuesta;
	}
	
	

}
