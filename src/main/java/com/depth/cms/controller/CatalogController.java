package com.depth.cms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.depth.cms.content.entity.CatalogEntity;
import com.depth.cms.content.metatype.DTO;
import com.depth.cms.content.service.CatalogFacade;
import com.depth.cms.controller.commons.BaseController;
import com.depth.cms.controller.commons.web.WebResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 栏目管理
 */
@Controller
@RequestMapping(value = "/catalog")
public class CatalogController extends BaseController {

    @Resource
    private CatalogFacade catalogFacade;

    /**
     * 栏目管理列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/manage.do")
    public ModelAndView manage() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("catalog/manage");
        return mv;
    }

    /**
     * 查询所有栏目，生成树
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "list.do")
    public WebResponse getMenuBySys() {
        List<Map<String, Object>> list = catalogFacade.findWithTree();
        WebResponse webResponse = new WebResponse();
        webResponse.setAjaxMsg(true, null, null, JSON.toJSONString(list));
        return webResponse;
    }

    /**
     * 栏目添加或修改
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add_update_catalog.do", method = RequestMethod.POST)
    public WebResponse addAndupdateMenu() {
        WebResponse webResponse = new WebResponse();
        DTO d = getParamAsDTO();

        DateFormat format2 = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        String dateTime = format2.format(new Date());

        CatalogEntity catalogEntity = new CatalogEntity();

        if (StringUtils.isEmpty(d.get("id"))) { //新增
            //判断该栏目名是否存在
            String name = d.get("name").toString();
            CatalogEntity cata = catalogFacade.findByName(name);

            if (cata != null && d.getAsString("reId").equals(cata.getReId())) {
                webResponse.setAjaxMsg(false, "该栏目名已存在，请重新输入", null, null);
                return webResponse;
            }

            Long reid = Long.parseLong(d.get("reId").toString());
            Long topid = Long.parseLong(d.get("topId").toString());
            String path = "/";
            if (reid != 0) {
                CatalogEntity reCata = catalogFacade.findById(reid);
                path = reCata.getPath() + reid + "/";
            }
            catalogEntity.setPath(path);
            catalogEntity.setTopId(topid);
            catalogEntity.setReId(reid);
            catalogEntity.setStatus(1);
            catalogEntity.setName(d.get("name").toString());
            catalogEntity.setDir(d.get("dir").toString());
            catalogEntity.setDefaultType(Integer.parseInt(d.get("defaultType").toString()));
            catalogEntity.setIsNavigation(Long.parseLong(d.get("isNavigation").toString()));
            catalogEntity.setDefaultName(d.get("defaultName").toString());
            catalogEntity.setSort(Long.parseLong(d.get("sort").toString()));
            catalogEntity.setType(Integer.parseInt(d.get("type").toString()));
            catalogEntity.setAa01("test");
            catalogEntity.setAa02(Long.parseLong(dateTime));
            catalogEntity.setAb01("test");
            catalogEntity.setAb02(Long.parseLong(dateTime));
            catalogEntity.setAbbreviation(d.get("abbreviation").toString());
            catalogEntity.setServiceName(d.get("serviceName").toString());
            catalogEntity.setFunctionName(Integer.parseInt(d.get("functionName").toString()));
            catalogEntity.setJumpUrl(d.get("jumpUrl").toString());
            catalogEntity = catalogFacade.add(catalogEntity);
            webResponse.setAjaxMsg(true, "添加成功", null, "{\"id\":" + catalogEntity.getId() + ",\"pid\":" + catalogEntity.getReId() + ",\"name\":\"" + catalogEntity.getName() + "\",\"type\":" + catalogEntity.getType() + "}");
        } else { //修改
            Long id = Long.parseLong(d.get("id").toString());

            String addtype = d.get("addtype").toString();
            catalogEntity = catalogFacade.findById(id);     //通过id查询栏目信息

            catalogEntity.setAb01("test");
            catalogEntity.setAb02(Long.parseLong(dateTime));

            if (addtype.equals("tkd")) {      //TKD保存
                catalogEntity.setPageTitle(d.get("pageTitle").toString());
                catalogEntity.setPageKeywords(d.get("pageKeywords").toString());
                catalogEntity.setPageDescription(d.get("pageDescription").toString());
            } else if (addtype.equals("content")) {     //内容保存
                catalogEntity.setContent(d.get("content").toString());
            } else {      //基本信息保存
                //判断该栏目名是否存在
                String name = d.get("name").toString();
                int reId = d.getAsInteger("reId");
                CatalogEntity cata = catalogFacade.findByName(name);
                if (cata != null && !cata.getId().equals(id) && cata.getReId() == reId) {
                    webResponse.setAjaxMsg(false, "该栏目名已存在，请重新输入", null, null);
                    return webResponse;
                }

                catalogEntity.setName(d.get("name").toString());
                catalogEntity.setDir(d.get("dir").toString());
                catalogEntity.setDefaultType(Integer.parseInt(d.get("defaultType").toString()));
                catalogEntity.setIsNavigation(Long.parseLong(d.get("isNavigation").toString()));
                catalogEntity.setDefaultName(d.get("defaultName").toString());
                catalogEntity.setSort(Long.parseLong(d.get("sort").toString()));
                catalogEntity.setType(Integer.parseInt(d.get("type").toString()));
                catalogEntity.setAbbreviation(d.get("abbreviation").toString());
                catalogEntity.setServiceName(d.get("serviceName").toString());
                catalogEntity.setFunctionName(Integer.parseInt(d.get("functionName").toString()));
                catalogEntity.setJumpUrl(d.get("jumpUrl").toString());
            }
            catalogFacade.update(catalogEntity);
            webResponse.setAjaxMsg(true, "修改成功", null, "{\"id\":" + catalogEntity.getId() + ",\"pid\":" + catalogEntity.getReId() + ",\"name\":\"" + catalogEntity.getName() + "\",\"type\":" + catalogEntity.getType() + "}");
        }
        return webResponse;
    }


    /**
     * 获取栏目的目录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findDir_{id}.do", method = RequestMethod.POST)
    public String findDir(@PathVariable final Long id) {
        CatalogEntity reCata = catalogFacade.findById(id);
        return JSONObject.toJSONString(reCata);
    }

    /**
     * 根据id查询栏目信息，用于编辑
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "catalog_{id}.do", method = RequestMethod.POST)
    public WebResponse getMenu(@PathVariable final Long id) {
        WebResponse webResponse = new WebResponse();
        if (StringUtils.isEmpty(id)) {
            webResponse.setAjaxMsg(false, "栏目编号不能为空！", null, "");
        }
        CatalogEntity catalogEntity = catalogFacade.findById(id);     //通过id查询栏目信息
        if(catalogEntity != null){
            if(catalogEntity.getPageTitle() == null){
                catalogEntity.setPageTitle("");
            }
            if(catalogEntity.getPageKeywords() == null){
                catalogEntity.setPageKeywords("");
            }
            if(catalogEntity.getPageDescription() == null){
                catalogEntity.setPageDescription("");
            }
        }
        webResponse.setAjaxMsg(true, "", null, JSONObject.toJSONString(catalogEntity));
        return webResponse;
    }

    /**
     * 删除栏目信息
     *
     * @param id 栏目编号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete_catalog_{id}.do", method = RequestMethod.POST)
    public WebResponse deleteMenu(@PathVariable final Long id) {
        WebResponse webResponse = new WebResponse();
        //通过栏目id，查询该栏目下有没有文章
        long countArt = catalogFacade.countArticle(id);
        //通过栏目id，查询该栏目下是否还有子栏目
        long countSub = catalogFacade.countSub(id);
        if (countSub > 0) {
            webResponse.setAjaxMsg(false, "该栏目下有子栏目，不能删除", "", "");
            return webResponse;
        }
        if (countArt > 0) {
            webResponse.setAjaxMsg(false, "该栏目下有文章，不能删除", "", "");
            return webResponse;
        }
        try {
            catalogFacade.deleteById(id);
            webResponse.setAjaxMsg(true, "删除成功", "", "");
            return webResponse;
        } catch (Exception e) {
            logger.error(e.getMessage());
            webResponse.setAjaxMsg(false, "删除失败", "", "");
            return webResponse;
        }
    }

    /**
     * 修改栏目状态
     *
     * @param id 栏目编号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "status_catalog_{id}.do", method = RequestMethod.POST)
    public WebResponse statusCatalog(@PathVariable final Long id) {
            WebResponse webResponse = new WebResponse();
            CatalogEntity catalogEntity = catalogFacade.findById(id);     //通过id查询栏目信息
            if (catalogEntity.getStatus() == 1) {     //表示当前状态是启用
                //通过栏目id，查询该栏目下有没有文章
                long countArt = catalogFacade.countArticle(id);
                //通过栏目id，查询该栏目下是否还有子栏目
                long countSub = catalogFacade.countSub(id);
                if (countSub > 0) {
                    webResponse.setAjaxMsg(false, "该栏目下有子栏目，不能禁用", "", "");
                    return webResponse;
                }
                if (countArt > 0) {
                    webResponse.setAjaxMsg(false, "该栏目下有文章，不能禁用", "", "");
                    return webResponse;
                }
                catalogEntity.setStatus(0);
                catalogFacade.update(catalogEntity);
                webResponse.setAjaxMsg(true, "禁用成功", "", "{\"status\":0}");
                return webResponse;
            } else {
                catalogEntity.setStatus(1);
                catalogFacade.update(catalogEntity);
                webResponse.setAjaxMsg(true, "启用成功", "", "{\"status\":1}");
                return webResponse;
            }
    }


    /**
     * 修改栏目节点的层级
     *
     * @param id    节点编号
     * @param reId  父节点编号
     * @param topId 父节点编号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update_catalog_parent.do", method = RequestMethod.POST)
    public WebResponse updateMenuParent(Long id, Long reId, Long topId) {
        WebResponse webResponse = new WebResponse();
        try {
            CatalogEntity catalogEntity = catalogFacade.findById(id);     //通过id查询栏目信息
            catalogEntity.setReId(reId);
            catalogEntity.setTopId(topId);
            catalogFacade.update(catalogEntity);
            webResponse.setAjaxMsg(true, "修改成功", null, null);
            return webResponse;
        } catch (Exception e) {
            logger.error(e.getMessage());
            webResponse.setAjaxMsg(false, "修改失败，系统异常:" + e.getMessage(), null, null);
            return webResponse;
        }
    }

    /**
     * 查询非单页栏目(用于新增文章页面、修改文章页面的栏目选择)
     */
    @ResponseBody
    @RequestMapping(value = "/all.do", method = RequestMethod.POST)
    public WebResponse allCatalog() {
        WebResponse webResponse = new WebResponse();
        List<CatalogEntity> list = catalogFacade.findAllCatalog();
        List<CatalogEntity> nodeList = new ArrayList<CatalogEntity>();
        for (CatalogEntity node1 : list) {
            boolean mark = false;
            for (CatalogEntity node2 : list) {
                if ((node1.getReId() != null || node1.getReId() != 0) && node1.getReId().equals(node2.getId())) {
                    mark = true;
                    if (node2.getCatalogs() == null)
                        node2.setCatalogs(new ArrayList<CatalogEntity>());
                    node2.getCatalogs().add(node1);
                    break;
                }
            }
            if (!mark) {
                nodeList.add(node1);
            }
        }
        String json = JSON.toJSONString(nodeList).replaceAll("catalogs", "children").replaceAll("name", "text");
        webResponse.setAjaxMsg(true, "查询成功", null, json);
        return webResponse;
    }
}
