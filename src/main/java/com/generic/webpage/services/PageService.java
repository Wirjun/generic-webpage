package com.generic.webpage.services;

import com.generic.webpage.entities.Page;
import com.generic.webpage.repos.PageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PageService {

    @Autowired
    private PageRepo pageRepo;

    public List<Page> findAll() {
        return pageRepo.findAll();
    }

    public Page findById(final Integer id) {
        return pageRepo.findById(id).orElseThrow();
    }

    public Page findByAlias(final String alias) {
        return pageRepo.findByAlias(alias);
    }

    @Transactional
    public void delete(Page page) {
        pageRepo.delete(page);
    }

    @Transactional
    public Page saveOrUpdate(Page page) {
        return pageRepo.save(page);
    }
}
