package com.mye.dto;

import com.mye.entity.Permission;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zb
 * @date 2019-09-04 17:06
 **/
public class PermissionDTO {

    private Integer id;
    private String name;
    private String url;
    private String route;
    private String type;
    private Integer parentId;
    private List<PermissionDTO> children;

    public static PermissionDTO of(Permission permission) {
        if (permission == null) {
            return null;
        }
        PermissionDTO dto = new PermissionDTO();
        BeanUtils.copyProperties(permission, dto);
        return dto;
    }

    /**
     * @param list 数据库里面获取到的全量菜单列表
     */
    public static List<PermissionDTO> parsePermissionTree(List<PermissionDTO> list) {
        List<PermissionDTO> result = new ArrayList<>();

        // 1、获取第一级节点
        for (PermissionDTO dto : list) {
            if (0 == dto.getParentId()) {
                result.add(dto);
            }
        }

        // 2、递归获取子节点
        for (PermissionDTO parent : result) {
            recursiveTree(parent, list);
        }

        return result;
    }

    private static void recursiveTree(PermissionDTO parent, List<PermissionDTO> list) {
        for (PermissionDTO dto : list) {
            if (parent.getId().equals(dto.getParentId())) {
                if (CollectionUtils.isEmpty(parent.children)) {
                    parent.setChildren(new ArrayList<>());
                }
                recursiveTree(dto, list);
                parent.getChildren().add(dto);
            }
        }
    }

    @Override
    public String toString() {
        return "PermissionDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", route='" + route + '\'' +
                ", type='" + type + '\'' +
                ", parentId=" + parentId +
                '}';
    }

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<PermissionDTO> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionDTO> children) {
        this.children = children;
    }
}
