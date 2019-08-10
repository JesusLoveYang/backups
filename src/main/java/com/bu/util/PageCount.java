package com.bu.util;

public class PageCount {
    public static int pageIndexToRow(int pageIndex, int pageSize) {
        return pageIndex > 0 ? (pageIndex - 1) * pageSize : 0;
    }
}
