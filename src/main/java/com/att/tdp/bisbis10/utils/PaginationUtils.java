package com.att.tdp.bisbis10.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.att.tdp.bisbis10.exception.pagination.InvalidPageSizeValueException;
import com.att.tdp.bisbis10.exception.pagination.NegativePageValueException;

public class PaginationUtils {
    static private final int DEFAULT_PAGE_SIZE = 10;

    static public Pageable createPagable(Integer page, Integer pageSize) {
        if(page == null) {
            page = 0;
        }
        if(pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        if(page < 0) {
            throw new NegativePageValueException();
        }
        if(pageSize < 1) {
            throw new InvalidPageSizeValueException();
        }

        Pageable pageable = PageRequest.of(page, pageSize);
        return pageable;
    }
}
