package com.mye.dto.update;

import javax.validation.constraints.Min;

/**
 * @author zb
 * @date 2019-09-04 17:54
 **/
public class UpdatePermission {

    @Min(value = 1)
    private Integer id;
    private String name;
    private String url;
    private String route;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
