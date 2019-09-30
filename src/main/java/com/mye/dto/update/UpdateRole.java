package com.mye.dto.update;

import javax.validation.constraints.Min;

/**
 * @author zb
 * @date 2019-09-04 16:44
 **/
public class UpdateRole {

    @Min(value = 1)
    private Integer id;
    private String name;
    private String displayName;
    private String description;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
