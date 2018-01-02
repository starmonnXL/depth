package com.depth.cms.enums;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 16:22 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public enum ArticleEnum {

    ARTICLE_POST_YES("0","可评论"),
    ARTICLE_POST_NO("1","禁止评论"),

    ARTICLE_HTML_YES("0","可静态"),
    ARTICLE_HTML_NO("1","禁止静态"),

    ARTICLE_STATUS_DISABLE("2","禁用"),
    ARTICLE_STATUS_ENABLE("1","启用"),
    ARTICLE_STATUS_DRAFT("0","草稿");

    // 成员变量
    public String value;
    public String name;

    // 构造方法
    private ArticleEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

