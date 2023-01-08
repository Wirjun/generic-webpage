package com.generic.webpage.repos;

import com.generic.webpage.entities.Menu;
import com.generic.webpage.entities.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MenuRepo extends JpaRepository<Menu, Integer>, CrudRepository<Menu,Integer> {
}
