package com.depth.cms.content.impl;

import com.depth.cms.content.dao.CatalogDao;
import com.depth.cms.content.entity.CatalogEntity;
import com.depth.cms.content.metatype.DTO;
import com.depth.cms.content.service.CatalogFacade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 文章服务实现
 * Created by DongQihong on 2016/12/19.
 */
@Service("catalogFacade")
public class CatalogFacadeImpl implements CatalogFacade {

    /**
     * 栏目DaoI
     */
    @Resource
    private CatalogDao catalogDao;

    @Override
    public CatalogEntity add(CatalogEntity catalog) {
        catalogDao.insert(catalog);
        return catalog;
    }

    @Override
    public void update(CatalogEntity catalog) {
        catalogDao.updateByIdWithBLOBs(catalog);
    }

    @Override
    public void deleteById(Long id) {
        catalogDao.deleteById(id);
    }

    @Override
    public CatalogEntity findById(Long id) {
        return catalogDao.findById(id);
    }

    @Override
    public CatalogEntity findByName(String name) {
        return catalogDao.findByName(name);
    }

    @Override
    public CatalogEntity findByDir(String dir) {
        return catalogDao.findByDir(dir);
    }

    @Override
    public List<CatalogEntity> findTop() {
        return catalogDao.findTop();
    }

    @Override
    public List<CatalogEntity> findSub(Long id) {
        return catalogDao.findSub(id);
    }

    @Override
    public List<DTO> findBySub(Long id, Integer isNavigation) {
        return catalogDao.findBySub(id,isNavigation);
    }

    @Override
    public List<CatalogEntity> findAll() {
        return catalogDao.findAll();
    }

    @Override
    public List<CatalogEntity> findAllCatalog() {
        return catalogDao.findAllCatalog();
    }

    @Override
    public List<Map<String, Object>> findWithTree() {
        return catalogDao.findWithTree();
    }

    @Override
    public long countSub(Long id) {
        return catalogDao.countSub(id);
    }

    @Override
    public long countArticle(Long id) {
        return catalogDao.countArticle(id);
    }

    @Override
    public List<DTO> findOperation() {
        return catalogDao.findOperation();
    }

    @Override
    public List<CatalogEntity> findEncyclopedias(Long id) {
        return catalogDao.findEncyclopedias(id);
    }

    @Override
    public List<DTO> findCatalogEntityByState(Integer defaultType,Long id) {
        return catalogDao.findCatalogEntityByState(defaultType,id);
    }
}
