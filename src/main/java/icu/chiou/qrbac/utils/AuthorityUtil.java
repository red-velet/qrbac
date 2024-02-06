package icu.chiou.qrbac.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * author: redvelet
 * createTime: 2024/2/4
 * description: 权限容器/权限校验工具类
 */
public class AuthorityUtil {
    private static final Map<String, Collection<String>> CONTAINER = new HashMap<>();

    public static Map<String, Collection<String>> getContainer() {
        return CONTAINER;
    }

    private AuthorityUtil() {
        
    }

    public static void setAuthority(String userId, Collection<String> authorities) {
        CONTAINER.put(userId, authorities);
    }

    public static Boolean verify(String userId, String currAuthorityMark) {
        Collection<String> strings = CONTAINER.get(userId);
        return strings.contains(currAuthorityMark);
    }

    public static Boolean isExistAuthority(String userId) {
        return CONTAINER.get(userId) != null;
    }
}
