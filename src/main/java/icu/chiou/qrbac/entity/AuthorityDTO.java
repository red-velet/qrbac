package icu.chiou.qrbac.entity;

import lombok.Data;

/**
 * author: redvelet
 * createTime: 2024/2/4
 * description:
 */
@Data
public class AuthorityDTO {
    private Integer rid;

    private Integer[] pid;
}
