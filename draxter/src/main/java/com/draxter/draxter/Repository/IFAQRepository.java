package com.draxter.draxter.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.draxter.draxter.Entity.FAQ;

@Repository
public interface IFAQRepository extends JpaRepository<FAQ, Long> {
}
