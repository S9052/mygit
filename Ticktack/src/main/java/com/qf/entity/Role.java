package com.qf.entity;

import java.util.List;

public class Role {
	
    private Integer id;

    private String info;

    private String name;

    private Integer parentid;
    
    private List<Authority> autho;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

	public List<Authority> getAutho() {
		return autho;
	}

	public void setAutho(List<Authority> autho) {
		this.autho = autho;
	}

}