package com.example.crm.repo;

import com.example.crm.entity.Client;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;

public interface ClientRepo extends JpaRepository<Client, Long> {
    @Query("select c from Client c where (:q is null or lower(c.name) like lower(concat('%',:q,'%')) or lower(c.email) like lower(concat('%',:q,'%')))")
    Page<Client> search(@Param("q") String q, Pageable pageable);
}
