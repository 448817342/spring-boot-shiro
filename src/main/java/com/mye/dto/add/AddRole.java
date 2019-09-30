package com.mye.dto.add;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author zb
 * @date 2019-09-04 16:33
 **/
public class AddRole {

    @NotBlank
    private String name;
    private String displayName;
    @Size(max = 200)
    private String description;

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
