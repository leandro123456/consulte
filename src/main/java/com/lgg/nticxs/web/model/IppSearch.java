package com.lgg.nticxs.web.model;

/**
 * Created by movasim on 12/12/16.
 */
public class IppSearch {
    public static final String SEARCH_BY_NAME= "name";
    public static final String SEARCH_BY_CATEGORY= "cat";
    public static final String SEARCH_BY_CLASE= "cla";
    public static final String SEARCH_BY_TYPE= "typ";

    private String searchText;
    private String searchBy;

    public IppSearch(String searchText, String searchBy){
        this.searchText = searchText;
        this.searchBy = searchBy;
    }

    public IppSearch(){
    }

    public String getSearchText() {
        return searchText;
    }

    public String getSearchBy() {
        return searchBy;
    }
}
