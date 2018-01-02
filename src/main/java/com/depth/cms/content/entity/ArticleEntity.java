package com.depth.cms.content.entity;

import java.io.Serializable;

/**
 * 栏目实体
 */
public class ArticleEntity implements Serializable {
    private Long id;

    private Long catalogId;

    private String name;

    private String title;

    private String titleColor;

    private String title2;

    private String summary;

    private String pageTitle;

    private String pageKeywords;

    private String pageDescription;

    private String tags;

    private String picName;

    private Long picId;

    private String link;

    private String source;

    private String writer;

    private Integer notPost;

    private Integer click;

    private Long power;

    private Long pubDate;

    private Integer notHtml;

    private String filename;

    private String flags;

    private String shows;

    private Integer postCount;

    private Long lastPost;

    private Integer status;

    private String aa01;

    private Long aa02;

    private String addtime;

    private String ab01;

    private Long ab02;

    private String content;

    private String path;

    private String dir;     //栏目所在目录

    private Integer likes; //点赞数

    private Integer showHome; //是否显示到首页：0否，1是

    private Integer showOperation; //是否显示到业态首页：0否，1是

    private String title2Color; //副标题颜色

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor == null ? null : titleColor.trim();
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2 == null ? null : title2.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName == null ? null : picName.trim();
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer == null ? null : writer.trim();
    }

    public Integer getNotPost() {
        return notPost;
    }

    public void setNotPost(Integer notPost) {
        this.notPost = notPost;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Long getPubDate() {
        return pubDate;
    }

    public void setPubDate(Long pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getNotHtml() {
        return notHtml;
    }

    public void setNotHtml(Integer notHtml) {
        this.notHtml = notHtml;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags == null ? null : flags.trim();
    }

    public String getShows() {
        return shows;
    }

    public void setShows(String shows) {
        this.shows = shows == null ? null : shows.trim();
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public Long getLastPost() {
        return lastPost;
    }

    public void setLastPost(Long lastPost) {
        this.lastPost = lastPost;
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getShowHome() {
        return showHome;
    }

    public void setShowHome(Integer showHome) {
        this.showHome = showHome == null ? 0 : showHome;
    }

    public Integer getShowOperation() {
        return showOperation;
    }

    public void setShowOperation(Integer showOperation) {
        this.showOperation = showOperation == null ? 0 : showOperation;
    }

    public String getTitle2Color() {
        return title2Color;
    }

    public void setTitle2Color(String title2Color) {
        this.title2Color = title2Color == null ? null : title2Color.trim();
    }
}