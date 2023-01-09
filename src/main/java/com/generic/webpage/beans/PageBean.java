package com.generic.webpage.beans;

import com.generic.webpage.entities.Menu;
import com.generic.webpage.entities.Page;
import com.generic.webpage.services.MenuService;
import com.generic.webpage.services.PageService;
import com.generic.webpage.util.GrowlUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("view")
public class PageBean {

    @Autowired
    private PageService pageService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private GrowlUtil growlUtil;

    @Getter
    @Setter
    private Page page;

    @Getter
    @Setter
    private Page selectedPage;

    @Getter
    @Setter
    private List<Menu> availableMenus;

    @Getter
    private List<Page> pages;

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getRequestURI().equals("/backend/pages.xhtml")) {
            pages = pageService.findAll();
            selectedPage = new Page();
            return;
        }
        page = pageService.findByAlias(request.getRequestURI());
    }

    public void initCreateDialog() {
        availableMenus = new ArrayList<>();
        initAvailableMenus();
        selectedPage = new Page();
    }

    public void initUpdateDialog(final Page page) {
        availableMenus = new ArrayList<>();
        initAvailableMenus();
        if(page.getMenu() != null) {
            availableMenus.add(page.getMenu());
        }
        selectedPage = page;
    }

    private void initAvailableMenus() {
        for (Menu menu : menuService.findAll()) {
            if (menu.getPage() == null) {
                availableMenus.add(menu);
            }
        }
    }

    public void saveOrUpdate() {
        selectedPage = pageService.saveOrUpdate(selectedPage);
        growlUtil.showInfo("der Beitrag wurde gespeichert");
        init();
    }

    public void delete(final Page page) {
        pageService.delete(page);
        growlUtil.showInfo("der Beitrag wurde gelöscht");
        init();
    }
}