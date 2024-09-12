package com.barcosDaoV2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Barco {
    @Id
    private Long id;
    private String nombre;
    private String tipo;
    private int eslora;
    private int manga;
    private int capacidad;
    private Amarre amarre;
}
