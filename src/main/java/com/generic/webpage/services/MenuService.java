package com.generic.webpage.services;

import com.generic.webpage.entities.Menu;
import com.generic.webpage.entities.Page;
import com.generic.webpage.repos.MenuRepo;
import com.generic.webpage.repos.PageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuService {

    @Autowired
    private MenuRepo menuRepo;

    public List<Menu> findAll() {
        return menuRepo.findAll();
    }

    public Menu findById(final Integer id) {
        return menuRepo.findById(id).orElseThrow();
    }

    @Transactional
    public void delete(Menu menu) {
        menuRepo.delete(menu);
    }

    @Transactional
    public Menu saveOrUpdate(Menu menu) {
        return menuRepo.save(menu);
    }
}
