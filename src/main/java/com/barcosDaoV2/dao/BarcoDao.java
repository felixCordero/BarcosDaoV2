package com.barcosDaoV2.dao;

import com.barcosDaoV2.model.Barco;

import java.util.List;

public interface BarcoDao extends GenericDaoBarcos<Barco,Integer>{
    List<Barco> findByNombre(String nombre);
    List<Barco> findByNombreOrderByCapacidadDesc(String nombre);
    Long countByTipo(String tipo);
}
