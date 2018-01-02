package com.depth.cms.content.dao;

import com.depth.cms.content.entity.CatalogEntity;
import com.depth.cms.content.metatype.DTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 栏目Dao
 * Created by DongQihong on 2016/12/19.
 */
public interface CatalogDao {

    int deleteById(Long id);

    /**
     * 添加栏目
     *
     * @param catalog 栏目
     * @return 栏目
     */
    int insert(CatalogEntity catalog);

    int insertSelective(CatalogEntity record);

    CatalogEntity findById(Long id);

    int updateByIdSelective(CatalogEntity record);

    int updateByIdWithBLOBs(CatalogEntity record);

    int updateById(CatalogEntity record);

    /**
     * 为树菜单查询所有栏目
     *
     * @return 树菜单列表
     */
    List<Map<String, Object>> findWithTree();

    /**
     * 查询所有栏目
     *
     * @return 栏目列表
     */
    List<CatalogEntity> findAll();

    /**
     * 查询所有非单页栏目
     *
     * @return 非单页栏目列表
     */
    List<CatalogEntity> findAllCatalog();

    /**
     * 查询顶层栏目
     *
     * @return 栏目列表
     */
    List<CatalogEntity> findTop();

    /**
     * 查询指定栏目的子栏目
     *
     * @param id 指定栏目编号
     * @return 子栏目列表
     */
    List<CatalogEntity> findSub(Long id);

    /**
     * 统计子栏目数
     *
     * @param id 要统计的栏目编号
     * @return 子栏目数
     */
    long countSub(Long id);

    /**
     * 统计栏目下的文章数
     *
     * @param id 要统计的栏目编号
     * @return 文章数
     */
    long countArticle(Long id);

    /**
     * 通过栏目名查询栏目
     *
     * @param name 栏目名
     * @return 栏目
     */
    CatalogEntity findByName(String name);

    /**
     * 通过目录查询栏目
     *
     * @param dir 目录
     * @return 栏目
     */
    CatalogEntity findByDir(String dir);

    /**
     * 查询所有业态
     *
     * @return 业态栏目列表
     */
    List<DTO> findOperation();

    /**
     * 查询所有业态下百科
     *
     * @return 业态百科栏目列表
     */
    List<CatalogEntity> findEncyclopedias(Long id);

    /**
     * 根据首页或者业态首页查询新闻或咨询栏目
     * @param defaultType 3是首页，4是业态首页
     * @return 栏目
     */
    List<DTO> findCatalogEntityByState(@Param("defaultType") Integer defaultType, @Param("id") Long id);

    /**
     * 查询子栏目
     *
     * @param id 栏目的上级编号
     * @param isNavigation 筛选条件
     * @return 栏目列表
     */
    List<DTO> findBySub(@Param("id") Long id, @Param("isNavigation") Integer isNavigation);

}
