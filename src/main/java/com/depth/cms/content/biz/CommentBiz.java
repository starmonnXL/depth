package com.depth.cms.content.biz;

import com.depth.cms.commons.page.BizPageResponse;
import com.depth.cms.commons.page.Page;
import com.depth.cms.commons.util.IdWorkerUtil;
import com.depth.cms.content.dao.CommentDao;
import com.depth.cms.content.entity.ArticleEntity;
import com.depth.cms.content.entity.CommentEntity;
import com.depth.cms.content.exception.CommentException;
import com.depth.cms.content.exception.SDKException;
import com.depth.cms.content.service.ArticleFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>@Title 文章评论业务逻辑层 </p>
 * <p>@Description 文章评论业务逻辑层 </p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author weifeng 作者</p>
 * <p>@date 2017/3/1 15:42 创建日期</p>
 * <p>weifeng@dgg.net 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Service("commentBiz")
public class CommentBiz {

    @Resource
    private CommentDao commentDao;

    @Resource
    private ArticleFacade articleFacade;


    public CommentEntity insert(Map<String,Object> params) throws SDKException {
        if(null == params){
            throw CommentException.PARAM_IS_NULL;
        }
        Long articleId = (Long) params.get("articleId");
        String content = (String) params.get("content");
        Long aa01 = (Long) params.get("aa01");
        if(null == articleId || StringUtils.isEmpty(content) || null == aa01){
            throw CommentException.LACK_PARAM;
        }

        ArticleEntity article = articleFacade.findById(articleId);
        if(null == article) {
            throw CommentException.PARAM_ERROR;
        }

        CommentEntity comment = new CommentEntity();
        DateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateTime = format2.format(new Date());
        Long addTime = Long.parseLong(dateTime);
        comment.setId(IdWorkerUtil.getId());
        comment.setArticleId(articleId);
        comment.setContent(content);
        comment.setAa01(aa01);
        comment.setAa02(addTime);
        comment.setState(CommentEntity.NORMAL_STATE);
        commentDao.insert(comment); //新增评论

        Integer postCount = article.getPostCount() == null ? 0 : article.getPostCount();
        article.setPostCount(postCount + 1);
        articleFacade.update(article); //修改文章评论数

        return comment;
    }

    public void update(CommentEntity comment) throws SDKException {
        if(null == comment){
            throw CommentException.LACK_PARAM;
        }
        commentDao.update(comment);
    }

    public void deleteById(Long id) throws SDKException {
        if(null == id){
            throw CommentException.LACK_PARAM;
        }
        CommentEntity comment = commentDao.getById(id);
        if(comment != null){
            commentDao.deleteById(id);
            Long articleId = comment.getArticleId();
            ArticleEntity article = articleFacade.getArticle(articleId);
            if(null != article){
                Integer postCount = article.getPostCount() == null ? 0 : article.getPostCount();
                postCount = (postCount - 1) < 0 ? 0 : (postCount - 1);
                article.setPostCount(postCount);
                articleFacade.update(article);
            }
        }
    }

    public void logicDeleteById(Long id) throws SDKException {
        if(null == id){
            throw CommentException.LACK_PARAM;
        }
        CommentEntity comment = commentDao.getById(id);
        if(null != comment){
            comment.setState(CommentEntity.CANCEL_STATE);
            commentDao.update(comment);
            Long articleId = comment.getArticleId();
            ArticleEntity article = articleFacade.getArticle(articleId);
            if(null != article){
                Integer postCount = article.getPostCount() == null ? 0 : article.getPostCount();
                postCount = (postCount - 1) < 0 ? 0 : (postCount - 1);
                article.setPostCount(postCount);
                articleFacade.update(article);
            }
        }
    }

    public CommentEntity getById(Long id) throws SDKException {
        if(null == id){
            throw CommentException.LACK_PARAM;
        }
        return commentDao.getById(id);
    }

    public BizPageResponse<List<CommentEntity>> findWithPage(Page page) throws SDKException {
        BizPageResponse<List<CommentEntity>> bizPage = new BizPageResponse();
        List<CommentEntity> commentList = commentDao.findlistPage(page);
        bizPage.setPage(page);
        bizPage.setObj(commentList);
        bizPage.setSuccess(true);
        return bizPage;
    }

    public BizPageResponse<List<Map<String, Object>>> findNormalWithPage(Page page) throws SDKException {
        BizPageResponse<List<Map<String, Object>>>  bizPage = new BizPageResponse();
        List<Map<String, Object>> commentList = commentDao.findNormallistPage(page);
        bizPage.setPage(page);
        bizPage.setObj(commentList);
        bizPage.setSuccess(true);
        return bizPage;
    }

    public BizPageResponse<List<CommentEntity>> findNormalPage(Page page) throws SDKException {
        BizPageResponse<List<CommentEntity>> bizPage = new BizPageResponse();
        List<CommentEntity> commentList = commentDao.searchNormallistPage(page);
        bizPage.setPage(page);
        bizPage.setObj(commentList);
        bizPage.setSuccess(true);
        return bizPage;
    }

    public BizPageResponse<List<CommentEntity>> findReportComment(Page page) throws SDKException {
        BizPageResponse<List<CommentEntity>> bizPage = new BizPageResponse();
        List<CommentEntity> commentList = commentDao.findReportlistPage(page);
        bizPage.setPage(page);
        bizPage.setObj(commentList);
        bizPage.setSuccess(true);
        return bizPage;
    }

    public Integer countCommentByAid(Long aId) throws SDKException {
        if(null == aId){
            throw CommentException.PARAM_IS_NULL;
        }
        return commentDao.countCommentByAid(aId);
    }
}
