package com.henryDev.Paso1.controller;

import com.henryDev.Paso1.model.Producto;
import com.henryDev.Paso1.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController //Manejar el API
@RequestMapping("/api1/v1")//Definicion de la ruta de la API Rest
@RequiredArgsConstructor // Inyeccion de dependencias
@Slf4j
public class ProductoController {
    private final ProductoService productoService;

    // Construcción de las API Rest, para la clase producto
    @GetMapping("/productos") //Trae la informacion de la entidad
    public ResponseEntity<List<Producto>> listarProductos() {
        try {
            log.debug("Listado de Productos");

            List<Producto> productos = new ArrayList<>();
            productoService.buscarTodo().forEach(productos::add);
            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (DataAccessException daex) {
            log.error("Error al obtener el listado de productos" + daex.getMostSpecificCause());

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/productosPorId/{id}")
    public ResponseEntity<Optional<Producto>> obtenerProductoPorId(@Valid @PathVariable Long id) {
        Optional<Producto> soloProducto;
        try {
            soloProducto = productoService.findByProductoID(id);
            if (soloProducto.isPresent()) {
                log.debug("Registro encontrado");
                return ResponseEntity.ok().body(Optional.of(soloProducto.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (DataAccessException daex) {
            log.error("Registro no encontrado" + daex.getMostSpecificCause());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/guardarProd")
    public ResponseEntity<Producto> guardarProducto(@RequestBody Producto producto) {
        try {
            log.debug("registro guardado ");
            Producto nuevoProducto = productoService.guardarProducto(producto);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        } catch (DataAccessException daex) {
            log.error("Error al registrar el producto" + daex.getMostSpecificCause());

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/actualizaProd/{id}")
    public ResponseEntity<Producto> actualizaProducto(@Valid @PathVariable Long id, @RequestBody Producto producto) {
        Producto productoActualizado;
        try {
            log.debug("Registro Actualizado");
            productoActualizado = productoService.actualizarProducto(id, producto);
            return new ResponseEntity<>(productoActualizado, HttpStatus.ACCEPTED);
        } catch (DataAccessException daex) {
            log.error("Erro al Actualizar el registro" + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/eliminaProd/{id}")
    public ResponseEntity<Producto> eliminarProducto(@Valid @PathVariable Long id) {

        try {
            log.debug("Registro Eliminado");
            return new ResponseEntity<>(productoService.eliminarProducto(id), HttpStatus.NO_CONTENT);
        } catch (DataAccessException daex) {
            log.error("Erro al eliminar el registro" + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
