package com.draxter.draxter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.draxter.draxter.Entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{
    
}
