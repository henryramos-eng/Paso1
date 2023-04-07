package com.henryDev.Paso1.service;

import com.henryDev.Paso1.model.Producto;

import java.util.List;
import java.util.Optional;
import com.henryDev.Paso1.model.Producto;
public interface ProductoService {
    /* SE DEFINE LOS CRUDS*/

    List<Producto> buscarTodo();

    Optional<Producto> findByProductoID(Long id);

    Producto guardarProducto(Producto newProducto);

    Producto actualizarProducto(Long idProducto, Producto updateProducto);

    Producto eliminarProducto(Long idProducto);
}
