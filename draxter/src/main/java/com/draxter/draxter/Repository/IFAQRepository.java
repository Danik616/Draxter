package com.draxter.draxter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.draxter.draxter.Entity.FAQ;

@Repository
public interface IFAQRepository extends JpaRepository<FAQ, Long> {

    @Query("FROM FAQ WHERE id_faq LIKE :id")
    public FAQ obtenerFaqPorID(@Param("id") long id);
}
