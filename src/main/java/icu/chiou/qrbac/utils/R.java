package icu.chiou.qrbac.utils;

import lombok.Data;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 统一返回工具类
 */
@Data
public class R<T> {

    private static final long serialVersionUID = 22L;

    private T type;

    // 响应码，返回状态，返回消息，返回数据
    private int code;

    private Boolean state;

    private String message;

    private Map<String, Object> data = new HashMap();

    private long count;

    public R() {
    }

    // 成功
    public static R ok() {
        R responseUtils = new R();
        responseUtils.setCode(0);
        responseUtils.setState(true);
        responseUtils.setMessage("成功");
        return responseUtils;
    }

    // 失败
    public static R error() {
        R r = new R();
        r.setCode(201);
        r.setState(false);
        r.setMessage("失败");
        return r;
    }

    public R count(long count) {
        this.setCount(count);
        return this;
    }

    public R code(int code) {
        this.setCode(code);
        return this;
    }

    public R state(Boolean state) {
        this.setState(state);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R message(String message, Object... objects) {
        this.setMessage(MessageFormat.format(message, objects));
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}

