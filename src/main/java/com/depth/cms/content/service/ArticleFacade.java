package com.depth.cms.content.service;


import com.depth.cms.commons.page.BizPageResponse;
import com.depth.cms.commons.page.Page;
import com.depth.cms.content.entity.ArticleEntity;
import com.depth.cms.content.metatype.DTO;

import java.util.List;
import java.util.Map;

/**
 * 文章服务
 * Created by DongQihong on 2016/12/19.
 */
public interface ArticleFacade {
    /**
     * 添加文章
     *
     * @param article 文章
     * @return 文章
     */
    ArticleEntity add(ArticleEntity article);

    /**
     * 更新文章
     *
     * @param article 新的文章信息
     */
    void update(ArticleEntity article);

    /**
     * 删除指定文章
     *
     * @param id 指定文章编号
     */
    void deleteById(Long id);

    /**
     * 删除多条指定的文章
     *
     * @param ids 文章编号列表
     */
    void deletedByIds(List<Long> ids);

    /**
     * 查找指定文章
     *
     * @param id 文章编号
     * @return 文章
     */
    ArticleEntity findById(Long id);

    /**
     * 查询一页的文章
     * <p>
     * 可接受的查询参数：<br/>
     * <code>cId</code>栏目编号<br/>
     * <code>beginDate</code>发布时间起<br/>
     * <code>endDate</code>发布日期止<br/>
     * <code>id</code>文章编号<br/>
     * <code>title</code>文章标题<br/>
     * </p>
     *
     * @return 文章分页
     */
    BizPageResponse<List<ArticleEntity>> findWithPage(Page page);

    /**
     * 查询一页的仅启用的文章
     * <p>
     * 可接受的查询参数：<br/>
     * <code>cId</code>栏目编号<br/>
     * </p>
     *
     * @return 文章分页
     */
    BizPageResponse<List<Map<String, Object>>> findEnableWithPage(Page page);

    /**
     * 查询指定栏目最新的文章
     *
     * @param cId 栏目编号，不指定是显示全局最新
     * @param num 最大数量，不指定时默认为10
     * @return 文章列表
     */
    List<Map<String, Object>> findEnableLast(Long cId, Integer num);

    /**
     * 查询指定栏目，指定标识的最新文章
     *
     * @param flag 标识
     * @param num  最大数量，不指定时默认为5
     * @param cId  栏目编号
     * @return 文章列表
     */
    List<Map<String, Object>> findEnableByFlag(String flag, Integer num, Long cId);

    /**
     * 查询栏目下的所有启用文章
     *
     * @param cId 栏目编号
     * @param num 查询的条数
     * @return 文章列表
     */
    List<ArticleEntity> findEnableAllByCatalogId(Long cId, int num);

    /**
     * 根据栏目id，查询该栏目下的热门文章
     *
     * @param cId 栏目编号
     * @param num 查询的数量
     * @return
     */
    List<ArticleEntity> findHotByCataId(Long cId, int num);

    /**
     * 根据文章id，查询该栏目下的上一条文章
     * @param aId   文章id
     * @param cId   栏目id
     * @return
     */
    ArticleEntity findLastById(Long aId, Long cId);

    /**
     * 根据文章id，查询该栏目下的下一条文章
     * @param aId   文章id
     * @param cId   栏目id
     * @return
     */
    ArticleEntity findNextById(Long aId, Long cId);

    /**
     * 根据文章id，查询该栏目下的三条相关文章
     * @param aId   文章id
     * @param cId   栏目id
     * @return
     */
    List<ArticleEntity> findRelevantById(Long aId, Long cId);

    /**
     * 修改文章点赞数
     *
     * @param article 新的文章信息
     */
    void addLikes(ArticleEntity article);

    /**
     * app查询一页启用的文章
     * <p>
     * 可接受的查询参数：<br/>
     * <code>cId</code>栏目编号<br/>
     * <code>beginDate</code>发布时间起<br/>
     * <code>endDate</code>发布日期止<br/>
     * <code>title</code>文章标题<br/>
     * </p>
     * @return 文章分页
     * <pre> id 文章id
     * <pre> name 文章名
     * <pre> title 文章标题
     * <pre> title2 文章副标题
     * <pre> summary 简介
     * <pre> tags 标签
     * <pre> picName 缩略图
     * <pre> link 外部链接
     * <pre> writer 作者
     * <pre> click 点击量（浏览量）
     * <pre> likes 点赞量
     * <pre> pubDate 发布时间
     * <pre> postCount 评论数
     * <pre> catalogName 栏目名
     */
    BizPageResponse<List<Map<String,Object>>> appFindWithPage(Page page);

    /**
     * 查询文章详情
     * @param id 文章编号
     * @return 文章详情
     * <pre>  id 文章id
     * <pre>  catalogId 栏目id
     * <pre>  name  文章名
     * <pre>  title 文章标题
     * <pre>  titleColor 标题颜色
     * <pre>  title2 文章副标题
     * <pre>  summary 简介
     * <pre>  pageTitle 页面标题
     * <pre>  pageKeywords 页面关键字
     * <pre>  pageDescription 页面描述
     * <pre>  tags 标签
     * <pre>  picName 缩略图
     * <pre>  picId 缩略图编号
     * <pre>  link 外部链接
     * <pre>  source 文章来源
     * <pre>  writer 作者
     * <pre>  notPost 禁止评论1禁止0允许
     * <pre>  click 点击量（浏览量）
     * <pre>  power 排序权重，大的靠前，自增
     * <pre>  pubDate 发布时间
     * <pre>  notHtml 禁止静态1禁止0允许
     * <pre>  filename 生成静态的文件名
     * <pre>  flags 推荐属性f幻灯c推荐a广告
     * <pre>  shows 展示属性
     * <pre>  postCount 评论数
     * <pre>  lastPost 最后评论
     * <pre>  status 状态0草稿1启用2禁用
     * <pre>  aa01 创建人
     * <pre>  aa02 创建时间
     * <pre>  ab01 修改人
     * <pre>  ab02 修改时间
     * <pre>  content 内容
     * <pre>  path 路径
     * <pre>  likes 点赞数
     *
     */
    ArticleEntity getArticle(Long id);

    /**
     * 查询（热点，热搜,热点图）文章
     * @param indexState 首页：默认0，1热搜，2热点图，3热点，4业态推荐
     * @param showState 业态首页：默认0，2热点图，3热点
     * @param id 业态唯一标示
     * @return
     */
    List<DTO> findHotSearch(Integer indexState, Integer showState, Long id);

    /**
     * 根据文章标题模糊查询文章列表
     * @param name 文章标题
     * @return
     */
    List<ArticleEntity> findListByName(String name);

    /**
     * 根据栏目标识模糊查询此栏目下的所有文章
     * @param path 查询条件
     * @param num 查询条数
     * @return
     */
    List<DTO> findSearchByPath(String path, int num);

    /**
     * 根据栏目标识分页查询此栏目下的所有文章
     * <p>
     * 可接受的查询参数：<br/>
     * <code>path</code>路径<br/>
     * <code>cId</code>所属栏目编号<br/>
     * <code>title</code>文章标题<br/>
     * </p>
     */
    BizPageResponse<List<DTO>> findlistPageByPathOrCatalogId(Page page);

}
