package com.bravo.user.utility;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class PageUtilTest {
    MockHttpServletResponse mHttpServletResponse;
    Page<?> mPage;
    List<String> stringList;
    final int listSize = 4;

    @BeforeAll
    void setupPage() {
        stringList = new ArrayList<String>();
        stringList.add("Jake");
        stringList.add("Josh");
        stringList.add("Sam");
        stringList.add("Steve");
    }

    @Test
    void shouldCreatePageOfSpecifiedSizeGivenValidSize() {
        // createPageRequest(page, size)
        PageRequest pRequest = PageUtil.createPageRequest(1, 10);
        assertEquals(pRequest.getPageSize(), 10);
    }

    @Test
    void pageRequestShouldAlwaysHaveNonZeroPositiveSize() {
        // createPageRequest(page, size)
        final int PAGE_NUMBER = 1;
        final int NEGATIVE_SIZE = -10;
        final int ZERO_SIZE = 0;

        PageRequest pRequest1 = PageUtil.createPageRequest(PAGE_NUMBER, NEGATIVE_SIZE);
        PageRequest pRequest2 = PageUtil.createPageRequest(PAGE_NUMBER, ZERO_SIZE);
        PageRequest pRequest3 = PageUtil.createPageRequest(PAGE_NUMBER, null);

        assertTrue(pRequest1.getPageSize() > 0);
        assertTrue(pRequest2.getPageSize() > 0);
        assertTrue(pRequest3.getPageSize() > 0);
    }

    @Test
    void pageRequestShouldUseDefaultSizeIfInputLTEZero() {
        final int PAGE_NUMBER = 1;
        final int NEGATIVE_SIZE = -10;
        final int DEFAULT_SIZE = 15;
        PageRequest pRequest = PageUtil.createPageRequest(PAGE_NUMBER, NEGATIVE_SIZE, DEFAULT_SIZE);

        assertEquals(pRequest.getPageSize(), DEFAULT_SIZE);
    }

    @Test
    void shouldUpdatePageHeaders() {
        mHttpServletResponse = new MockHttpServletResponse();
        PageRequest pRequest = PageRequest.of(0, 4);
        mPage = new PageImpl<>(stringList, pRequest, listSize);

        PageUtil.updatePageHeaders(mHttpServletResponse, mPage, pRequest);

        assertEquals(mHttpServletResponse.getHeader("page-count"), mPage.getTotalPages());
        assertEquals(mHttpServletResponse.getHeader("page-number"), pRequest.getPageNumber());
        assertEquals(mHttpServletResponse.getHeader("page-size"), pRequest.getPageSize());
    }

}
