package icu.chiou.qrbac.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * author: redvelet
 * createTime: 2024/2/4
 * description:
 */
@Data
public class Tree implements Serializable {
    private static final long serialVersionUID = -8791258973419382138L;
    private Integer id;

    private Integer pId;

    private String title;

    private List<Tree> children;

    private Boolean spread;
}
