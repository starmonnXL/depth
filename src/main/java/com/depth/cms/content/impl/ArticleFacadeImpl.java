package com.depth.cms.content.impl;

import com.depth.cms.commons.page.BizPageResponse;
import com.depth.cms.commons.page.Page;
import com.depth.cms.content.dao.ArticleDao;
import com.depth.cms.content.entity.ArticleEntity;
import com.depth.cms.content.metatype.DTO;
import com.depth.cms.content.service.ArticleFacade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 文章服务实现
 * Created by DongQihong on 2016/12/19.
 */
@Service("articleFacade")
public class ArticleFacadeImpl implements ArticleFacade {

    /**
     * 文章Dao
     */
    @Resource
    private ArticleDao articleDao;

    @Override
    public ArticleEntity add(ArticleEntity article) {
        articleDao.insert(article);
        return article;
    }

    @Override
    public void update(ArticleEntity article) {
        articleDao.updateById(article);
    }

    @Override
    public void deleteById(Long id) {
        articleDao.deleteById(id);
    }

    @Override
    public void deletedByIds(List<Long> ids) {
        articleDao.deleteByIds(ids);
    }

    @Override
    public ArticleEntity findById(Long id) {
        return articleDao.findById(id);
    }

    @Override
    public BizPageResponse<List<ArticleEntity>> findWithPage(Page page) {
        BizPageResponse<List<ArticleEntity>> bizPage = new BizPageResponse();
        List<ArticleEntity> list = articleDao.findlistPage(page);
        bizPage.setPage(page);
        bizPage.setObj(list);
        bizPage.setSuccess(true);
        return bizPage;
    }

    @Override
    public BizPageResponse<List<Map<String, Object>>> findEnableWithPage(Page page) {
        BizPageResponse<List<Map<String, Object>>> bizPage = new BizPageResponse();
        List<Map<String, Object>> list = articleDao.findEnablelistPage(page);
        bizPage.setPage(page);
        bizPage.setObj(list);
        bizPage.setSuccess(true);
        return bizPage;
    }

    @Override
    public List<Map<String, Object>> findEnableLast(Long cId, Integer num) {
        num = (num == null) ? 10 : num;//如果num为空则默认为10条
        return articleDao.findEnableLast(cId, num);
    }

    @Override
    public List<Map<String, Object>> findEnableByFlag(String flag, Integer num,Long cId) {
        num = (num == null) ? 5 : num;//如果num为空则默认为5条
        return articleDao.findEnableByFlag(flag, num,cId);
    }

    @Override
    public List<ArticleEntity> findEnableAllByCatalogId(Long cId, int num) {
        return articleDao.findEnableAllByCatalogId(cId, num);
    }

    @Override
    public List<ArticleEntity> findHotByCataId(Long cId, int num){
        return articleDao.findHotByCataId(cId,num);
    }

    @Override
    public ArticleEntity findLastById(Long aId,Long cId){
        return articleDao.findLastById(aId,cId);
    }

    @Override
    public ArticleEntity findNextById(Long aId,Long cId){
        return articleDao.findNextById(aId,cId);
    }

    @Override
    public List<ArticleEntity> findRelevantById(Long aId, Long cId) {
        return articleDao.findRelevantById(aId,cId);
    }

    @Override
    public void addLikes(ArticleEntity article) {
        articleDao.addLikesById(article);
    }

    @Override
    public BizPageResponse<List<Map<String,Object>>> appFindWithPage(Page page) {
        BizPageResponse<List<Map<String,Object>>> bizPage = new BizPageResponse();
        List<Map<String,Object>> list = articleDao.appFindlistPage(page);
        bizPage.setPage(page);
        bizPage.setObj(list);
        bizPage.setSuccess(true);
        return bizPage;
    }

    @Override
    public ArticleEntity getArticle(Long id) {
        return articleDao.getArticle(id);
    }

    @Override
    public List<DTO> findHotSearch(Integer indexState, Integer showState, Long id) {
        return articleDao.findHotSearch(indexState,showState,id);
    }

    @Override
    public List<ArticleEntity> findListByName(String name) {
        return articleDao.findListByName(name);
    }

    @Override
    public List<DTO> findSearchByPath(String path,int num) {
        return articleDao.findSearchByPath(path,num);
    }

    @Override
    public BizPageResponse<List<DTO>> findlistPageByPathOrCatalogId(Page page) {
        BizPageResponse bizPage = new BizPageResponse();
        List<DTO> list = articleDao.findlistPageByPathOrCatalogId(page);
        bizPage.setPage(page);
        bizPage.setObj(list);
        bizPage.setSuccess(true);
        return bizPage;
    }

}
