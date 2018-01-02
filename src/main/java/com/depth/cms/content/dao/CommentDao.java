package com.depth.cms.content.dao;

import com.depth.cms.commons.page.Page;
import com.depth.cms.content.entity.CommentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 文章评论Dao
 * <p>@author weifeng 作者</p>
 */
public interface CommentDao {

    int insert(CommentEntity comment);

    int update(CommentEntity comment);

    int deleteById(Long id);

    CommentEntity getById(Long id);

    /**
     * 查询一页的评论
     <p>
     * 可接受的查询参数：<br/>
     * <code>aId</code>文章编号<br/>
     * <code>state</code>状态<br/>
     * <code>beginDate</code>评论时间日期起<br/>
     * <code>endDate</code>评论时间日期止<br/>
     * <code>commentatorId</code>评论人编号<br/>
     * </p>
     *
     * @return 评论分页
     */
    List<CommentEntity> findlistPage(Page page);

    /**
     *  查询一页状态正常的评论
     <p>
     * 可接受的查询参数：<br/>
     * <code>aId</code>文章编号<br/>
     * <code>beginDate</code>评论时间日期起<br/>
     * <code>endDate</code>评论时间日期止<br/>
     * <code>commentatorId</code>评论人编号<br/>
     * </p>
     *
     * @return 评论分页
     */
    List<Map<String,Object>> findNormallistPage(Page page);

    /**
     *  查询一页状态正常的评论
     <p>
     * 可接受的查询参数：<br/>
     * <code>aId</code>文章编号<br/>
     * <code>beginDate</code>评论时间日期起<br/>
     * <code>endDate</code>评论时间日期止<br/>
     * <code>commentatorId</code>评论人编号<br/>
     * </p>
     *
     * @return 评论分页
     */
    List<CommentEntity> searchNormallistPage(Page page);

    /**
     * 查询一页状态正常且被举报过的评论
     <p>
     * 可接受的查询参数：<br/>
     * <code>aId</code>文章编号<br/>
     * <code>beginDate</code>评论时间日期起<br/>
     * <code>endDate</code>评论时间日期止<br/>
     * </p>
     *
     * @return 评论分页
     */
    List<CommentEntity> findReportlistPage(Page page);

    /**
     * 根据文章编号统计正常评论数
     * @param aId 文章编号
     * @return 评论数量
     */
    Integer countCommentByAid(@Param("aId") Long aId);

}
