package com.depth.cms.content.service;


import com.depth.cms.commons.page.BizPageResponse;
import com.depth.cms.commons.page.Page;
import com.depth.cms.content.entity.CommentEntity;
import com.depth.cms.content.exception.SDKException;

import java.util.List;
import java.util.Map;

/**
 * 文章评论服务
 * <p>@author weifeng 作者</p>
 */
public interface CommentFacade {

    /**
     * 添加评论
     * @param params (articleId:文章id; content:评论内容; aa01:评论人id)
     * @return 评论
     */
    CommentEntity add(Map<String, Object> params) throws SDKException;

    /**
     * 修改评论
     * @param comment 新的评论内容
     */
    void update(CommentEntity comment) throws SDKException;

    /**
     *  删除指定评论
     * @param id 指定评论id
     */
    void deleteById(Long id) throws SDKException;

    /**
     * 逻辑删除评论
     * @param id 指定评论id
     * @throws SDKException
     */
    void logicDeleteById(Long id) throws SDKException;

    /**
     * 查询指定评论
     * @param id 评论id
     * @return 评论
     */
    CommentEntity getById(Long id) throws SDKException;

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
    BizPageResponse<List<CommentEntity>> findWithPage(Page page) throws SDKException;

    /**
     * 查询一页状态正常的评论
     **<p>
     * 可接受的查询参数：<br/>
     * <code>aId</code>文章编号<br/>
     * <code>beginDate</code>评论时间日期起<br/>
     * <code>endDate</code>评论时间日期止<br/>
     * <code>commentatorId</code>评论人编号<br/>
     * </p>
     *
     * @return 评论分页
     * <pre>  id  评论id
     * <pre>  articleId  文章id
     * <pre>  content 评论内容
     * <pre>  report 举报量
     * <pre>  likes 点赞数
     * <pre>  aa01 评论人
     * <pre>  aa02 评论时间
     *
     */
    BizPageResponse<List<Map<String,Object>>> findNormalWithPage(Page page) throws SDKException;

    /**
     * 查询一页状态正常的评论
     **<p>
     * 可接受的查询参数：<br/>
     * <code>aId</code>文章编号<br/>
     * <code>beginDate</code>评论时间日期起<br/>
     * <code>endDate</code>评论时间日期止<br/>
     * <code>commentatorId</code>评论人编号<br/>
     * </p>
     *
     * @return 评论分页
     */
    BizPageResponse<List<CommentEntity>> findNormalPage(Page page) throws SDKException;

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
    BizPageResponse<List<CommentEntity>> findReportComment(Page page) throws SDKException;

    /**
     * 根据文章编号统计正常评论数
     * @param aId 文章编号
     * @return 评论数量
     */
    Integer countCommentByAid(Long aId) throws SDKException;
}
