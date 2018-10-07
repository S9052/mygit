package com.qf.entity;

import java.util.List;

public class Authority {
    private Integer id;

    private String aicon;

    private String aurl;

    private Integer parentId;

    private String title;

    private Integer type;
    
    private List<Authority> children;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAicon() {
        return aicon;
    }

    public void setAicon(String aicon) {
        this.aicon = aicon == null ? null : aicon.trim();
    }

    public String getAurl() {
        return aurl;
    }

    public void setAurl(String aurl) {
        this.aurl = aurl == null ? null : aurl.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	public List<Authority> getChildren() {
		return children;
	}

	public void setChildren(List<Authority> children) {
		this.children = children;
	}
}