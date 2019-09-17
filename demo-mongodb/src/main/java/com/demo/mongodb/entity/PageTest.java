package com.demo.mongodb.entity;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageTest extends PageRequest {

    public PageTest(int page, int size) {
        super(page, size);
    }
}
