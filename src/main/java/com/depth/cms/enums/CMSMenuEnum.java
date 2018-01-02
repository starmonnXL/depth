package com.depth.cms.enums;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 15:53 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public enum CMSMenuEnum implements MenuEnums{
    /**
     * 栏目管理
     */
    CATALOG_URI("catalog/manage.do"),
    CATALOG_URI_QUERY(CATALOG_URI,"CATALOGQUERY"),
    CATALOG_URI_ADD(CATALOG_URI,"CATALOGADD"),
    CATALOG_URI_UPDATE(CATALOG_URI,"CATALOGUPDATE"),
    CATALOG_URI_DELETE(CATALOG_URI,"CATALOGDELETE"),
    CATALOG_URI_STATUS(CATALOG_URI,"CATALOGSTATUS"),


    /**
     * 文章管理
     */
    ARTICLE_URI("article/manage.do"),
    ARTICLE_URI_QUERY(ARTICLE_URI,"QUERY"),
    ARTICLE_URI_ADD(ARTICLE_URI,"ADD"),
    ARTICLE_URI_UPDATE(ARTICLE_URI,"UPDATE"),
    ARTICLE_URI_DELETE(ARTICLE_URI,"DELETE"),
    ARTICLE_URI_POST(ARTICLE_URI,"POST"),
    ARTICLE_URI_ENABLE(ARTICLE_URI,"ENABLE"),
    ARTICLE_URI_HTML(ARTICLE_URI,"HTML");


    private String uri;
    private String code;

    private CMSMenuEnum(String uri) {
        this.uri = uri;
    }
    private CMSMenuEnum(MenuEnums uri, String code) {
        this.uri = uri.getUri();
        this.code = code;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public String getCode() {
        return code;
    }
}

