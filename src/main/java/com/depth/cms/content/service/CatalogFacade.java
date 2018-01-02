package com.depth.cms.content.service;


import com.depth.cms.content.entity.CatalogEntity;
import com.depth.cms.content.metatype.DTO;

import java.util.List;
import java.util.Map;

/**
 * 栏目服务
 * Created by DongQihong on 2016/12/19.
 */
public interface CatalogFacade {
    /**
     * 添加栏目
     *
     * @param article 栏目
     * @return 栏目
     */
    CatalogEntity add(CatalogEntity article);

    /**
     * 更新栏目
     *
     * @param article 新的栏目信息
     */
    void update(CatalogEntity article);

    /**
     * 删除指定栏目
     *
     * @param id 指定栏目编号
     */
    void deleteById(Long id);

    /**
     * 查找指定栏目
     *
     * @param id 栏目编号
     * @return 栏目
     */
    CatalogEntity findById(Long id);

    /**
     * 通过栏目名获取栏目
     *
     * @param name 栏目名
     * @return 栏目
     */
    CatalogEntity findByName(String name);

    /**
     * 通过栏目目录获取栏目
     *
     * @param dir 目录
     * @return 栏目
     */
    CatalogEntity findByDir(String dir);

    /**
     * 查询顶级栏目
     *
     * @return 栏目列表
     */
    List<CatalogEntity> findTop();

    /**
     * 查询子栏目
     *
     * @param id 栏目的上级编号
     * @return 栏目列表
     */
    List<CatalogEntity> findSub(Long id);

    /**
     * 查询子栏目
     *
     * @param id 栏目的上级编号
     * @param isNavigation 筛选条件
     * @return 栏目列表
     */
    List<DTO> findBySub(Long id, Integer isNavigation);

    /**
     * 查询所有栏目
     *
     * @return 栏目列表
     */
    List<CatalogEntity> findAll();

    /**
     * 查询所有非单页栏目
     *
     * @return 非单页栏目
     */
    List<CatalogEntity> findAllCatalog();

    /**
     * 查询树形栏目
     *
     * @return 栏目开表
     */
    List<Map<String, Object>> findWithTree();

    /**
     * 统计栏目下的文章数
     *
     * @param id 要判断的栏目
     * @return 栏目数
     */
    long countSub(Long id);

    /**
     * 统计栏目下的文章数
     *
     * @param id 要判断的栏目
     * @return 文章数
     */
    long countArticle(Long id);


    /**
     * 查询所有业态
     *
     * @return 业态栏目列表
     */
    List<DTO> findOperation();

    /**
     * 根据业态栏目标识查询所有业态下百科下的栏目数据
     *
     * @return 业态百科栏目列表
     */
    List<CatalogEntity> findEncyclopedias(Long id);

    /**
     * 根据首页或者业态首页查询新闻或咨询栏目
     * @param defaultType 3是首页，4是业态首页
     * @param id 业态栏目编号，defaultType为4则必填
     * @return 栏目
     */
    List<DTO> findCatalogEntityByState(Integer defaultType, Long id);

}
