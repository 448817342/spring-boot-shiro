package com.mye.dto.add;

import javax.validation.constraints.NotBlank;

/**
 * @author zb
 * @date 2019-09-04 17:03
 **/
public class AddPermission {

    @NotBlank
    private String name;
    @NotBlank
    private String url;
    @NotBlank
    private String type;
    private String route;
    private Integer parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
