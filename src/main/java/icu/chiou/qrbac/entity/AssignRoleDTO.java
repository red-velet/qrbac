package icu.chiou.qrbac.entity;

import lombok.Data;

/**
 * author: redvelet
 * createTime: 2024/2/4
 * description: 接受前端传来的用户id和用户所分配的角色id
 */
@Data
public class AssignRoleDTO {
    private Integer uId;
    private Integer[] rId;
}
