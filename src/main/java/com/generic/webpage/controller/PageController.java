package com.generic.webpage.controller;

import com.generic.webpage.entities.Page;
import com.generic.webpage.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(value = "/")
    public ModelAndView getByAlias() {
        return findModelAndView("");
    }

    @GetMapping(value = "/{alias}")
    public ModelAndView getByAlias(@PathVariable String alias) {
        return findModelAndView(alias);
    }

    private ModelAndView findModelAndView(String alias) {
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
