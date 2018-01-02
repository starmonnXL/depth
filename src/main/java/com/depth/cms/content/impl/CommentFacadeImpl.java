package com.depth.cms.content.impl;

import com.depth.cms.commons.page.BizPageResponse;
import com.depth.cms.commons.page.Page;
import com.depth.cms.content.biz.CommentBiz;
import com.depth.cms.content.entity.CommentEntity;
import com.depth.cms.content.exception.SDKException;
import com.depth.cms.content.service.CommentFacade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 文章评论服务实现
 * <p>@author weifeng 作者</p>
 */
@Service("commentFacade")
public class CommentFacadeImpl implements CommentFacade {

    @Resource
    private CommentBiz commentBiz;


    @Override
    public CommentEntity add(Map<String,Object> params) throws SDKException {
        return commentBiz.insert(params);
    }

    @Override
    public void update(CommentEntity comment) throws SDKException {
        commentBiz.update(comment);
    }

    @Override
    public void deleteById(Long id) throws SDKException {
        commentBiz.deleteById(id);
    }

    @Override
    public void logicDeleteById(Long id) throws SDKException {
        commentBiz.logicDeleteById(id);
    }


    @Override
    public CommentEntity getById(Long id) throws SDKException {
        return commentBiz.getById(id);
    }

    @Override
    public BizPageResponse<List<CommentEntity>> findWithPage(Page page) throws SDKException {
        return commentBiz.findWithPage(page);
    }

    @Override
    public BizPageResponse<List<Map<String, Object>>> findNormalWithPage(Page page) throws SDKException {
        return commentBiz.findNormalWithPage(page);
    }

    @Override
    public BizPageResponse<List<CommentEntity>> findNormalPage(Page page) throws SDKException {
        return commentBiz.findNormalPage(page);
    }

    @Override
    public BizPageResponse<List<CommentEntity>> findReportComment(Page page) throws SDKException {
        return commentBiz.findReportComment(page);
    }

    @Override
    public Integer countCommentByAid(Long aId) throws SDKException {
        return commentBiz.countCommentByAid(aId);
    }
}
