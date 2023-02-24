package com.draxter.draxter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.draxter.draxter.Entity.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Long> {

}
