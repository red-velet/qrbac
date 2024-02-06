package icu.chiou.qrbac.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * author: redvelet
 * createTime: 2024/1/24
 * description: 菜单
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = 6706118778121029788L;
    private Integer id;

    private Integer pId;

    private String path;

    private String href;

    private String icon;

    private String title;

    private String target;

    private Integer isMenu;
    private List<Menu> child;
}
