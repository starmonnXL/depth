package com.depth.cms.content.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 栏目实体
 */
public class CatalogEntity implements Serializable {
    private Long id;

    private Long reId;

    private Long topId;

    private Integer type;

    private Long sort;

    private String name;

    private String dir;

    private Integer defaultType;

    private String defaultName;

    private String pageTitle;

    private String pageKeywords;

    private String pageDescription;

    private Integer status;

    private String aa01;

    private Long aa02;

    private String ab01;

    private Long ab02;

    private String content;

    private Long isNavigation;

    /**
     * 业态名称，必须和商品分类名称一致
     */
    private String serviceName;

    /**
     * 功能名称-默认0,1.查公司,2.申请贷款,3.查商标,4.服务预约
     */
    private int functionName;

    /**
     * 功能跳转链接
     */
    private String jumpUrl;

    /**
     * 分类简称
     */
    private String abbreviation;

    public Long getIsNavigation() {
        return isNavigation;
    }

    public void setIsNavigation(Long isNavigation) {
        this.isNavigation = isNavigation;
    }

    private List<ArticleEntity> articles;

    private List<CatalogEntity> catalogs;

    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleEntity> articles) {
        this.articles = articles;
    }

    public List<CatalogEntity> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<CatalogEntity> catalogs) {
        this.catalogs = catalogs;
    }

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReId() {
        return reId;
    }

    public void setReId(Long reId) {
        this.reId = reId;
    }

    public Long getTopId() {
        return topId;
    }

    public void setTopId(Long topId) {
        this.topId = topId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir == null ? null : dir.trim();
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation == null ? null : abbreviation.trim();
    }

    public Integer getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(Integer defaultType) {
        this.defaultType = defaultType;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName == null ? null : defaultName.trim();
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle == null ? null : pageTitle.trim();
    }

    public String getPageKeywords() {
        return pageKeywords;
    }

    public void setPageKeywords(String pageKeywords) {
        this.pageKeywords = pageKeywords == null ? null : pageKeywords.trim();
    }

    public String getPageDescription() {
        return pageDescription;
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription == null ? null : pageDescription.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAa01() {
        return aa01;
    }

    public void setAa01(String aa01) {
        this.aa01 = aa01 == null ? null : aa01.trim();
    }

    public Long getAa02() {
        return aa02;
    }

    public void setAa02(Long aa02) {
        this.aa02 = aa02;
    }

    public String getAb01() {
        return ab01;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public int getFunctionName() {
        return functionName;
    }

    public void setFunctionName(int functionName) {
        this.functionName = functionName;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl == null ? null : jumpUrl.trim();
    }

    public void setAb01(String ab01) {
        this.ab01 = ab01 == null ? null : ab01.trim();
    }

    public Long getAb02() {
        return ab02;
    }

    public void setAb02(Long ab02) {
        this.ab02 = ab02;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}