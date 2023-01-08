package com.generic.webpage.repos;

import com.generic.webpage.entities.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PageRepo extends JpaRepository<Page, Integer>, CrudRepository<Page,Integer> {

    @Query("SELECT p FROM Page p WHERE p.alias LIKE :alias")
    Page findByAlias(@Param("alias") String alias);
}
