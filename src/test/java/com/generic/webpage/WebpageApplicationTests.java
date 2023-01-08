package com.generic.webpage;

import com.generic.webpage.entities.Page;
import com.generic.webpage.services.PageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WebpageApplicationTests {

    @Autowired
    PageService pageService;

    @Test
    public void test() {
        Page page = pageService.findById(1L);
        Assertions.assertEquals(page.getTitle(), "Lorem Ipsum");
    }

}
