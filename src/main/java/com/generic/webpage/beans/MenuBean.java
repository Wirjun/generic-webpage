package com.generic.webpage.beans;

import com.generic.webpage.entities.Menu;
import com.generic.webpage.services.MenuService;
import com.generic.webpage.util.GrowlUtil;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Scope("view")
public class MenuBean {

    @Autowired
    private MenuService menuService;

    @Getter
    private MenuModel menuModel;

    @Getter
    private List<Menu> menus;

    @Autowired
    private GrowlUtil growlUtil;

    @Getter
    @Setter
    private Menu selectedMenu;

    @PostConstruct
    public void init() {
        menuModel = new DefaultMenuModel();
        menus = menuService.findAll();
        for (Menu menu : menus) {
            if(menu.getPage() != null) {
                DefaultMenuItem item = DefaultMenuItem.builder()
                        .value(menu.getLabel())
                        .url("/" + menu.getPage().getAlias())
                        .icon((menu.getIcon() == null || menu.getIcon().isBlank()) ? null : menu.getIcon())
                        .rendered(menu.isVisible())
                        .build();

                menuModel.getElements().add(item);
            }

        }
    }

    public void initCreateDialog() {
        selectedMenu = new Menu();
    }

    public void initUpdateDialog(final Menu menu) {
        selectedMenu = menu;
    }

    public void delete(final Menu menu) {
        menuService.delete(menu);
        growlUtil.showInfo("der Menüeintrag wurde gespeichert");
        init();
    }

    public void saveOrUpdate() {
        selectedMenu = menuService.saveOrUpdate(selectedMenu);
        growlUtil.showInfo("der Menüeintrag wurde gelöscht");
        init();
    }
}