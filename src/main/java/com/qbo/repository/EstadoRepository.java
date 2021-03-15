package com.qbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qbo.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
