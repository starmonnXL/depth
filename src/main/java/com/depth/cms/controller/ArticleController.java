package com.depth.cms.controller;


import com.depth.cms.commons.page.BizPageResponse;
import com.depth.cms.commons.page.Page;
import com.depth.cms.commons.util.StringUtil;
import com.depth.cms.commons.util.Utils;
import com.depth.cms.content.entity.ArticleEntity;
import com.depth.cms.content.entity.CatalogEntity;
import com.depth.cms.content.metatype.DTO;
import com.depth.cms.content.service.ArticleFacade;
import com.depth.cms.content.service.CatalogFacade;
import com.depth.cms.controller.commons.BaseController;
import com.depth.cms.controller.commons.web.WebResponse;
import com.depth.cms.enums.ArticleEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 文章管理
 * <p>
 * Created by ZouYing on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {

    @Resource
    private ArticleFacade articleFacade;

    @Resource
    private CatalogFacade catalogFacade;


    /**
     * 文章主页
     */
    @RequestMapping(value = "/manage.do")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
//        modelMap.put("systems", SecurityUtils.getWebSecurityManager().getUserSysArray());
//        mv.addObject("sid",SecurityUtils.getWebSecurityManager().getSid());
//        mv.addObject("fileAccessUrl", getFileAccessAddress());
        mv.setViewName("article/manage");
        return mv;
    }

    /**
     * 分页获取数据列表
     */
    @ResponseBody
    @RequestMapping(value = "/pageList.do", method = RequestMethod.POST)
    public Object pageList() {
        WebResponse webResponse = new WebResponse();
        Page page = getPage(getParamAsDTO());
        Object startTime = getParamAsDTO().get("timeBegin");
        Object endTime = getParamAsDTO().get("timeEnd");
        if (startTime != null && !"".equals(startTime)) {
            String timeBegin = startTime.toString().replaceAll("-", "") + "000000";
            Long beginDate = Long.parseLong(timeBegin);
            page.getDto().put("beginDate", beginDate);
        }
        if (endTime != null && !"".equals(endTime)) {
            String timeEnd = endTime.toString().replaceAll("-", "") + "235959";
            Long endDate = Long.parseLong(timeEnd);
            page.getDto().put("endDate", endDate);
        }
        BizPageResponse<List<ArticleEntity>> pageResponse = articleFacade.findWithPage(page);
        List<ArticleEntity> list = pageResponse.getObj();
        for (int i = 0, j = list.size(); i < j; i++) {
            String pubdate = list.get(i).getPubDate().toString();
            pubdate = Utils.getTime(pubdate);
            list.get(i).setAddtime(pubdate);
        }
        pageResponse.setObj(list);
        webResponse.setDataGridMsg(true, "查询成功", pageResponse.getObj(), pageResponse.getPage().getTotalResult());
        return webResponse;
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping(value = "/add_page.do", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView mv = new ModelAndView();
        DTO dto = getParamAsDTO();
        Long cId = dto.getAsLong("cId");
        if (cId != null) {
            mv.addObject("catalogId",cId);
        }
//        mv.addObject("systems", SecurityUtils.getWebSecurityManager().getUserSysArray());
        mv.setViewName("article/add");
        return mv;
    }

    /**
     * 添加文章
     */
    @ResponseBody
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public WebResponse add(final ArticleEntity articleEntity) {
        WebResponse webResponse = new WebResponse();
        DateFormat format2 = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        String dateTime = format2.format(new Date());
        Long catalogId = articleEntity.getCatalogId();
        if (catalogId == null) {
            webResponse.setAjaxMsg(false, "栏目编号不能为空！", null, "");
            return webResponse;
        }
        CatalogEntity catalog = catalogFacade.findById(catalogId);
        if (catalog == null) {
            webResponse.setAjaxMsg(false, "未能查询到栏目！", null, "");
            return webResponse;
        }
        String path = catalog.getPath() + catalogId + "/";
        articleEntity.setPath(path);
        articleEntity.setAa01("test");
        articleEntity.setAb01("test");
        Long time = Long.parseLong(dateTime);
        articleEntity.setAa02(time);
        articleEntity.setAb02(time);
        articleEntity.setPubDate(time);
        articleEntity.setTitleColor("#" + articleEntity.getTitleColor());
        articleEntity.setNotPost(Integer.parseInt(ArticleEnum.ARTICLE_POST_NO.value));
        articleEntity.setNotHtml(Integer.parseInt(ArticleEnum.ARTICLE_HTML_NO.value));
        articleEntity.setStatus(Integer.parseInt(ArticleEnum.ARTICLE_STATUS_ENABLE.value));
        articleEntity.setTitle2Color("#" + articleEntity.getTitle2Color());

        ArticleEntity article = articleFacade.add(articleEntity);
        if (article != null) {
            webResponse.setAjaxMsg(true, "添加成功", null, null);
        } else {
            webResponse.setAjaxMsg(false, "请联系系统管理员或重试!", null, null);
        }
        return webResponse;
    }

    /**
     * 修改文章页面
     */
    @RequestMapping(value = "/edit_page.do", method = RequestMethod.GET)
    public ModelAndView editPage() {
        ModelAndView mv = new ModelAndView();
        DTO dto = getParamAsDTO();
        if (dto.getAsLong("id") == null) {
            mv.setViewName("article/manage");
            mv.addObject("message", "文章id信息不能为空!");
            return mv;
        }
        ArticleEntity article = articleFacade.findById(dto.getAsLong("id"));
        mv.addObject("article", article);
//        mv.addObject("systems", SecurityUtils.getWebSecurityManager().getUserSysArray());
        mv.setViewName("article/edit");
        return mv;
    }

    /**
     * 修改文章
     */
    @ResponseBody
    @RequestMapping(value = "/edit.do", method = RequestMethod.POST)
    public WebResponse edit(final ArticleEntity articleEntity) {
        WebResponse webResponse = new WebResponse();
        if (articleEntity.getId() == null) {
            webResponse.setAjaxMsg(false, "参数异常id!", null, null);
            return webResponse;
        }
        ArticleEntity article = articleFacade.findById(articleEntity.getId());
        if (article == null) {
            webResponse.setAjaxMsg(false, "没有该条数据信息!", null, null);
            return webResponse;
        }
        DateFormat format2 = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        String dateTime = format2.format(new Date());
        Long catalogId = articleEntity.getCatalogId();
        CatalogEntity catalog = catalogFacade.findById(catalogId);
        String path = catalog.getPath() + catalogId + "/";
        articleEntity.setPath(path);
        articleEntity.setAb01("test");
        articleEntity.setAb02(Long.parseLong(dateTime));
        articleEntity.setAa01(article.getAa01());
        articleEntity.setAa02(article.getAa02());
        articleEntity.setPubDate(Long.parseLong(dateTime));
        articleEntity.setTitleColor("#" + articleEntity.getTitleColor());
        articleEntity.setNotPost(article.getNotPost());
        articleEntity.setNotHtml(article.getNotHtml());
        articleEntity.setStatus(article.getStatus());
        if(null == articleEntity.getPicName() || StringUtil.equals("",articleEntity.getPicName())){
            articleEntity.setPicName(article.getPicName());
        }
        articleEntity.setTitle2Color("#" + articleEntity.getTitle2Color());
        articleFacade.update(articleEntity);
        webResponse.setAjaxMsg(true, "修改成功", null, null);
        return webResponse;
    }

    /**
     * 删除文章
     */
    @ResponseBody
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    public WebResponse delete() {
        WebResponse webResponse = new WebResponse();
        DTO dto = getParamAsDTO();
        if (dto.getAsInteger("id") == null) {
            webResponse.setAjaxMsg(false, "参数不正确!", null, null);
            return webResponse;
        }
        ArticleEntity articleEntity = articleFacade.findById(dto.getAsLong("id"));
        if (articleEntity == null) {
            webResponse.setAjaxMsg(false, "没有该条记录信息!", null, null);
            return webResponse;
        }
        articleFacade.deleteById(dto.getAsLong("id"));
        webResponse.setAjaxMsg(true, "删除成功!", null, null);
        return webResponse;
    }

    /**
     * 批量删除文章
     */
    @ResponseBody
    @RequestMapping(value = "/delete_ids.do", method = RequestMethod.POST)
    public WebResponse deleteByIds(final Long[] id) {
        WebResponse webResponse = new WebResponse();
//                DTO dto = getParamAsDTO();
        if (id == null) {
            webResponse.setAjaxMsg(false, "参数不正确!", null, null);
            return webResponse;
        }
//                ArticleEntity articleEntity = articleFacade.findById(dto.getAsLong("id"));
//                if (articleEntity == null) {
//                    webResponse.setAjaxMsg(false, "没有该条记录信息!", null, null);
//                    return webResponse;
//                }
        Integer sucNum = 0;
        for (int i = 0; i < id.length; i++) {
            ArticleEntity articleEntity = articleFacade.findById(id[i]);
            if (articleEntity != null) {
                sucNum++;
            }
        }
        List<Long> ids = Arrays.asList(id);
        articleFacade.deletedByIds(ids);
        String msg = "删除成功";
        if (sucNum != id.length) {
            Integer failNum = id.length - sucNum;
            msg = "删除完成，其中失败数量" + failNum + "条";
        }
        webResponse.setAjaxMsg(true, msg, null, null);
        return webResponse;
    }

    /**
     * 启用或是禁用文章操作
     * @param id 文章ID信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/{id}/enable.do",method=RequestMethod.POST)
    public WebResponse enableCode(@PathVariable final Long id){
            WebResponse wb = new WebResponse();
            ArticleEntity articleEntity = articleFacade.findById(id);
            if(articleEntity.getStatus().toString().equals(ArticleEnum.ARTICLE_STATUS_ENABLE.value)){
                articleEntity.setStatus(Integer.parseInt(ArticleEnum.ARTICLE_STATUS_DISABLE.value));
            } else {
                articleEntity.setStatus(Integer.parseInt(ArticleEnum.ARTICLE_STATUS_ENABLE.value));
            }
            articleFacade.update(articleEntity);
            wb.setAjaxMsg(true, "操作成功!", null, null);
            return wb;
    }
}
