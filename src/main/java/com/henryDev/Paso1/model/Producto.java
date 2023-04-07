package com.henryDev.Paso1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Autoincrementable
    @Column(name = "producto_id", nullable = false, updatable = false, unique = true)
    private Long productoId;

    @Column(name = "name_product", length = 70)
    private String nombreProducto;

    @Column(name = "descripcion", length = 200)
    private String descripcion;

    @Column(name = "enabled")
    private boolean isEnabled;
}
