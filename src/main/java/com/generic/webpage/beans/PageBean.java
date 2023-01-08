package com.generic.webpage.beans;

import com.generic.webpage.entities.Page;
import com.generic.webpage.services.PageService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
@Scope("view")
public class PageBean {

    @Autowired
    private PageService pageService;

    @Getter
    @Setter
    private Page page;

    @Getter
    @Setter
    private Page selectedPage;

    @Getter
    private List<Page> pages;

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if(request.getRequestURI().equals("/backend/pages.xhtml")){
            pages = pageService.findAll();
            return;
        }
        page = pageService.findByAlias(request.getRequestURI());
    }

    public void delete(final Page page) {
        pageService.delete(page);
        init();
    }

    public void initCreateDialog() {
        selectedPage = new Page();
    }
    public void initUpdateDialog(final Page page) {
        selectedPage = page;
    }

    public void saveOrUpdate() {
        selectedPage = pageService.saveOrUpdate(selectedPage);
        init();
    }
}