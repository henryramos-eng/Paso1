package com.henryDev.Paso1.repository;

import com.henryDev.Paso1.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByProductoId(Long id);

}
