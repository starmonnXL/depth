package com.depth.cms.content.dao;

import com.depth.cms.commons.page.Page;
import com.depth.cms.content.entity.ArticleEntity;
import com.depth.cms.content.metatype.DTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 文章Dao
 * Created by DongQihong on 2016/12/19.
 */
public interface ArticleDao {
    int deleteById(Long id);

    int insert(ArticleEntity record);

    ArticleEntity findById(Long id);

    int updateByIdSelective(ArticleEntity record);

    int updateByIdWithBLOBs(ArticleEntity record);

    int updateById(ArticleEntity record);

    List<ArticleEntity> findlistPage(Page page);

    List<Map<String, Object>> findEnablelistPage(Page page);

    int deleteByIds(List<Long> ids);

    /**
     * 查询最新的文章
     *
     * @param cId 栏目名
     * @param num 查询数量
     * @return 文章列表
     */
    List<Map<String, Object>> findEnableLast(@Param("cId") Long cId, @Param("num") Integer num);

    /**
     * 查询指定标记启用的最新文章
     *
     * @param flag 标记
     * @param num  最大条数
     * @return 文章列表
     */
    List<Map<String, Object>> findEnableByFlag(@Param("flag") String flag, @Param("num") Integer num, @Param("cId") Long cId);

    /**
     * 查询栏目下的所有启用文章
     *
     * @param cId 栏目编号
     * @param num 查询的条数
     * @return 文章列表
     */
    List<ArticleEntity> findEnableAllByCatalogId(@Param("cId") Long cId, @Param("num") int num);

    /**
     * 根据栏目id，查询该栏目下的热门文章
     * @param cId   栏目编号
     * @param num   查询的数量
     * @return
     */
    List<ArticleEntity> findHotByCataId(@Param("cId") Long cId, @Param("num") int num);

    /**
     * 根据文章id，查询该栏目下的上一条文章
     * @param aId   文章id
     * @param cId   栏目id
     * @return
     */
    ArticleEntity findLastById(@Param("aId") Long aId, @Param("cId") Long cId);

    /**
     * 根据文章id，查询该栏目下的下一条文章
     * @param aId   文章id
     * @param cId   栏目id
     * @return
     */
    ArticleEntity findNextById(@Param("aId") Long aId, @Param("cId") Long cId);

    /**
     * 根据文章id，查询该栏目下的三条相关文章
     * @param aId   文章id
     * @param cId   栏目id
     * @return
     */
    List<ArticleEntity> findRelevantById(@Param("aId") Long aId, @Param("cId") Long cId);

    /**
     * 修改文章点赞数
     *
     * @param article 新的文章信息
     */
    int addLikesById(ArticleEntity article);

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
     */
    List<Map<String,Object>> appFindlistPage(Page page);

    /**
     * 查询文章详情
     * @param id 文章编号
     * @return 文章详情
     */
    ArticleEntity getArticle(Long id);

    /**
     * 查询（热点，热搜,热点图）文章
     * @param indexState 首页：默认0，1热搜，2热点图，3热点
     * @param showState 业态首页：默认0，2热点图，3热点
     *
     * @return
     */
    List<DTO> findHotSearch(@Param("indexState") Integer indexState, @Param("showState") Integer showState, @Param("id") Long id);

    /**
     * 根据文章标题模糊匹配文章列表
     * @param name 文章标题
     * @return
     */
    List<ArticleEntity> findListByName(@Param("name") String name);

    /**
     * 根据栏目标识模糊查询此栏目下的所有文章
     * @param path 查询条件
     * @param num 查询条数
     * @return
     */
    List<DTO> findSearchByPath(@Param("path") String path, @Param("num") int num);

    /**
     * 根据栏目标识分页查询此栏目下的所有文章
     * <p>
     * 可接受的查询参数：<br/>
     * <code>path</code>路径<br/>
     * <code>catalogId</code>所属栏目编号<br/>
     * <code>title</code>文章标题<br/>
     * </p>
     */
    List<DTO> findlistPageByPathOrCatalogId(Page page);
}
