package com.henryDev.Paso1.service.impl;

import com.henryDev.Paso1.model.Producto;
import com.henryDev.Paso1.repository.ProductoRepository;
import com.henryDev.Paso1.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service //Disponible para todas las clases
@RequiredArgsConstructor //para la inyeccion de dependencias del productoService y llamar a los metodos
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarTodo() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findByProductoID(Long id) {
        return productoRepository.findByProductoId(id);
    }

    @Override
    @Transactional
    public Producto guardarProducto(Producto newProducto) {
        return productoRepository.save(newProducto);
    }

    @Override
    @Transactional
    public Producto actualizarProducto(Long idProducto, Producto updateProducto) {
        var ProductoActualizar = productoRepository.findByProductoId(idProducto);
        if (ProductoActualizar.isPresent()) {
            ProductoActualizar.get().setNombreProducto(updateProducto.getNombreProducto());
            ProductoActualizar.get().setDescripcion(updateProducto.getDescripcion());
        }
        return productoRepository.save(ProductoActualizar.get());
    }

    @Override
    @Transactional
    public Producto eliminarProducto(Long idProducto) {
        var ProductoEliminar = productoRepository.findByProductoId(idProducto);
        if (ProductoEliminar.isPresent()) {
            ProductoEliminar.get().setEnabled(false);
        }
        return productoRepository.save(ProductoEliminar.get());
    }
}
