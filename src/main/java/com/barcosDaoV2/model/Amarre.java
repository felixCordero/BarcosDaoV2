package com.barcosDaoV2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Amarre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ubicacion")
    private String ubicacion;
    @Column(name = "precio")
    private double precio;
    @Column(name = "profundidad")
    private int profundidad;
    @Column(name = "longitud")
    private int longitud;
    @Column(name = "electricidad",nullable = false)
    private boolean electricidad;
    @OneToOne
    private Barco barco;
}
