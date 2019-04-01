package com.lgg.nticxs.web.model;

/**
 * Created by movasim on 05/09/16.
 */
public class EuiccSearch {

    public static final String SEARCH_BY_EID = "eid";
    public static final String SEARCH_BY_MSISDN = "msisdn";
    public static final String SEARCH_BY_ICCID = "iccid";
    public static final String SEARCH_BY_IMSI = "imsi";

    private String searchText;
    private String searchBy;

    public EuiccSearch(String searchText, String searchBy) {
        this.searchText = searchText;
        this.searchBy = searchBy;
    }

    public EuiccSearch() {

    }

    public String getSearchText() {
        return searchText;
    }

    public String getSearchBy() {
        return searchBy;
    }
}
