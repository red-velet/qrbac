package icu.chiou.qrbac.entity;

import lombok.Data;

/**
 * author: redvelet
 * createTime: 2024/2/4
 * description: 接受前端传来的角色id和角色所拥有的权限id
 */
@Data
public class AuthorityDTO {
    private Integer rid;

    private Integer[] pid;
}
