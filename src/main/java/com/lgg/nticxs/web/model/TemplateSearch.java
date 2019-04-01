package com.lgg.nticxs.web.model;

/**
 * Created by movasim on 12/12/16.
 */
public class TemplateSearch {
    public static final String SEARCH_BY_DEFAULT= "default";
    public static final String SEARCH_BY_NAME= "name";
    public static final String SEARCH_BY_CATEGORY= "category";
    public static final String SEARCH_BY_CLASE= "clase";
    public static final String SEARCH_BY_TYPE= "type";

    private String searchText;
    private String searchBy;

    public TemplateSearch(String searchText, String searchBy){
        this.searchText = searchText;
        this.searchBy = searchBy;
    }

    public TemplateSearch(){
    }

    public String getSearchText() {
        return searchText;
    }

    public String getSearchBy() {
        return searchBy;
    }
}
