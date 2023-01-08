package com.generic.webpage.controller;

import com.generic.webpage.entities.Page;
import com.generic.webpage.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping(value = "/administration")
    public ModelAndView getAdministration() {
        return new ModelAndView("/backend/administration.xhtml");
    }

    @GetMapping(value = "/pages")
    public ModelAndView getPages() {
        return new ModelAndView("/backend/pages.xhtml");
    }

    @GetMapping(value = "/menus")
    public ModelAndView getMenus() {
        return new ModelAndView("/backend/menus.xhtml");
    }

    @GetMapping(value = "/{alias}")
    public ModelAndView getByAlias(@PathVariable String alias) {
        Page page = pageService.findByAlias(alias);
        if (page != null && page.isVisible()) {
            ModelAndView modelAndView = new ModelAndView("/generated/page.xhtml");
            modelAndView.addObject("title", page.getTitle());
            modelAndView.addObject("content", page.getContent());
            return modelAndView;
        }
        return null;
    }
}
