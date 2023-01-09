package com.generic.webpage.repos;

import com.generic.webpage.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepo extends JpaRepository<Menu, Integer>, CrudRepository<Menu,Integer> {
}
