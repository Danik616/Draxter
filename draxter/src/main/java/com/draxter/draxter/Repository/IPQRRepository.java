package com.draxter.draxter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.draxter.draxter.Entity.PQR;

@Repository
public interface IPQRRepository extends JpaRepository<PQR, Long>{
    
}
