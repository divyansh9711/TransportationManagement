package com.example.divyanshsingh.transportationmanagement.payloads;
/**
 * @author Divyansh Singh
 *
 */
public class Payload {
    private String APIKey;
    private String info;
    private String id;
    private String searchKey;
    private String sortBy;
    private boolean sortOrder; 	 // default:0, desc-0, asc-1

    private int start;         	 // default=0
    private int limit;

    public Payload() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Payload(String info) {
        this.info = info;
    }
    public Payload(int start , int limit) {
        this.start = start;
        this.limit = limit;
    }

    public Payload(String aPIKey, String campusId, String searchKey, String sortBy, boolean sortOrder, int start,
                   int limit) {
        super();
        APIKey = aPIKey;
        this.searchKey = searchKey;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
        this.start = start;
        this.limit = limit;
    }

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getAPIKey() {
        return APIKey;
    }
    public void setAPIKey(String aPIKey) {
        APIKey = aPIKey;
    }
    public String getSearchKey() {
        return searchKey;
    }
    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
    public String getSortBy() {
        return sortBy;
    }
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
    public boolean isSortOrder() {
        return sortOrder;
    }
    public void setSortOrder(boolean sortOrder) {
        this.sortOrder = sortOrder;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
