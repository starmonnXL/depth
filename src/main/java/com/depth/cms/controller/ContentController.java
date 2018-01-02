package com.depth.cms.controller;

import com.alibaba.fastjson.JSON;
import com.depth.cms.commons.page.BizPageResponse;
import com.depth.cms.commons.page.Page;
import com.depth.cms.commons.util.ResourceUtils;
import com.depth.cms.commons.util.StringUtil;
import com.depth.cms.content.entity.ArticleEntity;
import com.depth.cms.content.entity.CatalogEntity;
import com.depth.cms.content.metatype.DTO;
import com.depth.cms.content.metatype.impl.BaseDTO;
import com.depth.cms.content.service.ArticleFacade;
import com.depth.cms.content.service.CatalogFacade;
import com.depth.cms.controller.commons.BaseController;
import com.depth.cms.controller.commons.web.WebResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 内容控制器，主要用于前台展示
 * Created by DongQihong on 2016/12/20.
 */
@Controller
@RequestMapping(value = "/")
public class ContentController extends BaseController {

    /**
     * 文章URL正则，数字结尾
     */
    public static final String ARTICLE_DIR_REG = "^[A-Za-z0-9/]+/\\d+$";

    @Resource
    private CatalogFacade catalogFacade;
    @Resource
    private ArticleFacade articleFacade;

    /**
     * 跳转博客首页
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("blog/index");
        return  mv;
    }

    /**
     * 跳转分类列表页
     * @return
     */
    @RequestMapping(value = "/blogList")
    public ModelAndView blogList(){
        ModelAndView mv = new ModelAndView();
        DTO dto = getParamAsDTO();
        mv.addObject("typeId",dto.getAsString("typeId"));
        mv.setViewName("blog/list");
        return  mv;
    }

    /**
     * 判断是文章路径
     */
    private boolean isArticleDir(String dir) {
        boolean flag = false;
        Pattern pattern = Pattern.compile(ARTICLE_DIR_REG);
        Matcher matcher = pattern.matcher(dir);
        flag = matcher.matches();
        return flag;
    }

    /**
     * 资讯站首页
     * @return
     */
    public String cmsIndex(Model model,HttpServletRequest request){
        //分类，热搜文章
        model = commons(model);
        //热点图
        List<DTO> hotspotImg = articleFacade.findHotSearch(2,null,null);
        //热点文章
        List<DTO> hotspot = articleFacade.findHotSearch(3,null,null);
        if(null != hotspot && StringUtil.isNotNull(hotspot)){
            for (DTO dto: hotspot) {
                if(StringUtil.isNotNull(dto.get("catalogId"))){
                    CatalogEntity catalog = catalogFacade.findById(dto.getAsLong("catalogId"));
                    if(null != catalog && StringUtil.isNotNull(catalog)){
                        dto.put("catalogName",catalog.getAbbreviation());
                    }
                }
            }
        }

        //查询首页列表文章
        List<DTO> classificationList = catalogFacade.findOperation();
        if(null != classificationList && StringUtil.isNotNull(classificationList)){
            for (DTO dto : classificationList) {
                if(StringUtil.isNotNull(dto.get("id"))){
                    List<DTO> sub = catalogFacade.findBySub(dto.getAsLong("id"),null);
                    if(null != sub && StringUtil.isNotNull(sub)){
                        for (DTO catalogSub : sub) {
                            if(StringUtil.isNotNull(catalogSub.get("id"))){
                                List<DTO> article = articleFacade.findSearchByPath(catalogSub.getAsString("id"),11);
                                List<DTO> articles = new ArrayList<DTO>();
                                if(null != article && StringUtil.isNotNull(article)){
                                    for (DTO dtos :article) {
                                        if(dtos.getAsInteger("showHome") == 0){
                                            articles.add(dtos);
                                        }
                                    }
                                }
                                catalogSub.put("child",articles);
                            }
                        }
                        dto.put("child",sub);
                    }
                    //业态推荐文章
                    List<DTO> hotspots = articleFacade.findHotSearch(4,null,dto.getAsLong("id"));
                    if(null != hotspots && StringUtil.isNotNull(hotspots)){
                        dto.put("childHot",hotspots);
                    }

                }
            }
        }

        Map config = ResourceUtils.getResource("global").getMap();
//        String env = config.get("env").toString();
//        String mainDomain = "http://www." + config.get("operate.domain").toString();
        // 获取PC首页
//        model.addAttribute("mainDomain",mainDomain);
        // 热点图
        model.addAttribute("hotspotImg",hotspotImg);
        // 热点文章
        model.addAttribute("hotspot",hotspot);
        // 首页新闻
        model.addAttribute("catalogDto",newsConsulting(3,null));
        // 首页业态列表
        model.addAttribute("classificationList",classificationList);
        // 返回首页路径
        return "jsp/index";
    }

    /**
     * 业态首页处理
     *
     * @param model
     * @param id 业态唯一标识
     * @return
     */
    public String index(Model model, Long id, DTO dto) {
        model = commons(model);
        //热点图
        List<DTO> hotspotImg = articleFacade.findHotSearch(null,2,id);
        //热点文章
        List<DTO> hotspot = articleFacade.findHotSearch(null,3,id);


        if(dto.getAsInteger("reId") != 0){
            //查询业态四级栏目
            List<DTO> catalogList = catalogFacade.findBySub(dto.getAsLong("path"),2);
            if(null != catalogList && StringUtil.isNotNull(catalogList)){
                model.addAttribute("catalogList",catalogList);
            }
        }
        id = dto.getAsLong("catalogId");
        //查询业态首页二级栏目
        List<DTO> catalogList = catalogFacade.findBySub(id,2);
        if(null != catalogList && StringUtil.isNotNull(catalogList)){
            List<DTO> listOne = new ArrayList<DTO>();
            List<DTO> listTwo = new ArrayList<DTO>();
            for (DTO catalogSub : catalogList) {
                if(StringUtil.isNotNull(catalogSub.get("id"))){
                    //查询业态首页所有三级栏目
                    List<DTO> catalogOne = catalogFacade.findBySub(catalogSub.getAsLong("id"),2);
                    if(null != catalogOne && StringUtil.isNotNull(catalogOne)){
                        for (DTO dtoOne :catalogOne) {
                            if(StringUtil.isNotNull(dtoOne.get("id"))){
                                //查询业态首页所有四级栏目
                                List<DTO> catalogTwo = catalogFacade.findBySub(dtoOne.getAsLong("id"),2);
                                if(null != catalogTwo && StringUtil.isNotNull(catalogTwo)){
                                    for (DTO dtoTwo: catalogTwo) {
                                        //业态下所有的四级栏目
                                        listTwo.add(dtoTwo);
                                    }
                                }
                            }
                            //业态下所有的三级栏目
                            listOne.add(dtoOne);
                        }
                    }
                }
            }
            model.addAttribute("catalogTwo",listTwo);
            model.addAttribute("catalogOne",listOne);
        }

        dto.put("path",dto.get("catalogLastId"));
        dto.put("showOperation",0);
        Page page = new Page();
        page.setShowCount(8);
        page.setPageDTO(dto);
        //模糊查询业态站下所有的文章列表,默认为第一页
        BizPageResponse<List<DTO>> article = articleFacade.findlistPageByPathOrCatalogId(page);

        //热点图
        model.addAttribute("hotspotImg",hotspotImg);
        //热点文章
        model.addAttribute("hotspot",hotspot);
        //首页新闻
        model.addAttribute("catalogDto",newsConsulting(4,id));
        //业态首页文章
        model.addAttribute("article",article.getObj());
        //业态首页文章总数
        model.addAttribute("articleTotal",article.getPage().getTotalPage());
        //dto数据
        model.addAttribute("dto",dto);

        String typeName =  dto.getAsString("serviceName");
        if(StringUtil.isEmpty(typeName)){
            typeName = "创业发展";
        }

        String operateDomain = ResourceUtils.getResource("global").getMap().get("operate.domain");
        model.addAttribute("operateDomain",operateDomain);

        Map config = ResourceUtils.getResource("global").getMap();
        String env = config.get("env").toString();
        String mainDomain = "http://www." + config.get("operate.domain").toString();
        // 获取PC首页
        model.addAttribute("mainDomain",mainDomain);

        List<ArticleEntity> news = articleFacade.findHotByCataId(id,10);
        model.addAttribute("typeName",typeName);
        model.addAttribute("hotNews",news);

        //返回首页路径
        return "inner_index";
    }

    /**
     * 栏目列表
     *
     * @param model
     * @return 列表页
     */
    @RequestMapping(value = "**")
    public String listOrInfo(Model model, HttpServletRequest request, Long pageNo) {
        DTO dto = getParamAsDTO();
        List<CatalogEntity> catalogs = catalogFacade.findTop();
        model.addAttribute("navs", catalogs);

        String path = request.getServletPath();
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        // 路径不是数字或“/”结尾
        if (!path.endsWith("/") && !isArticleDir(path)) {
            path += "/";
        }
        //为文章路径
        if (isArticleDir(path)) {
            String pageNoStr = path.replaceAll(".*[^\\d](?=(\\d+))", "");
            try {
                pageNo = Long.parseLong(pageNoStr);
            } catch (Exception e) {
                pageNo = null;
            }
            path = path.replaceAll("/[0-9]+", "/");
        }

        return list(model, path, pageNo,dto,request);
    }

    /**
     * 处理文章详情
     *
     * @param model 数据模型
     * @param id    文章编号
     * @return 文章详情视图
     */
    private String info(Model model, Long id) {
        List<CatalogEntity> catalogs = catalogFacade.findTop();
        model.addAttribute("navs", catalogs);

        ArticleEntity info = articleFacade.findById(id);
        if (info == null) {
            return "not_find";
        }

        // 如果是广告,直接跳转到广告链接
        if (info.getFlags() != null && info.getFlags().contains("a")) {
            if (info.getLink() != null && !"".equals(info.getLink())) {
                return "redirect:" + info.getLink();
            }
        }

        //每进入一次详情页，点击量就+1
        info.setClick(info.getClick() + 1);
        articleFacade.update(info);

        //获取当前所属栏目
        CatalogEntity catalog = catalogFacade.findById(info.getCatalogId());
        CatalogEntity topCatalog = catalogFacade.findById(catalog.getTopId());
        String path = catalog.getPath();
        String[] idStrs = path.split("/");
        List<CatalogEntity> paths = new ArrayList<CatalogEntity>();
        for (int i = 0; i < idStrs.length; i++) {
            try {
                CatalogEntity catalogEntity = catalogFacade.findById(Long.parseLong(idStrs[i]));
                if(catalogEntity.getName().indexOf("百科") == -1){
                    paths.add(catalogEntity);
                }
            } catch (Exception e) {
                continue;
            }
        }

        //获取相关文章
        List<ArticleEntity> relevantArticle = articleFacade.findRelevantById(id, info.getCatalogId());
        model = commons(model);
        model.addAttribute("paths", paths);
        model.addAttribute("info", info);
        model.addAttribute("catalog", catalog);
        model.addAttribute("topCatalog", topCatalog);
        model.addAttribute("relevantArticle", relevantArticle);
        model.addAttribute("info", info);
        Map config = ResourceUtils.getResource("global").getMap();
        String env = config.get("env").toString();
        String mainDomain = "http://www." + config.get("operate.domain").toString();
        // 获取PC首页
        model.addAttribute("mainDomain",mainDomain);
        CatalogEntity path1 = paths.get(0);
        Long cId = path1.getTopId();
        if(null == cId || 0 == cId){
            cId = path1.getId();
        }
        ArticleEntity up = articleFacade.findLastById(id,cId);

        ArticleEntity down = articleFacade.findNextById(id,cId);
        model.addAttribute("up",up);
        model.addAttribute("down",down);

        CatalogEntity path0 = paths.get(0);
        String typeName =  path0.getServiceName();
        if(StringUtil.isEmpty(typeName)){
            typeName = "创业发展";
        }


        String operateDomain = ResourceUtils.getResource("global").getMap().get("operate.domain");
        model.addAttribute("operateDomain",operateDomain);

        return "article_details";
    }

    /**
     * 处理列表
     *
     * @param model 数据模型
     * @param dir   目录
     * @return 列表视图
     */
    private String list(Model model, String dir, Long pageNo,DTO dto,HttpServletRequest request) {
        CatalogEntity catalog = catalogFacade.findByDir(dir);
        //首页
        if (catalog == null) {
            return cmsIndex(model,request);
        }
        //单页
        CatalogEntity topCatalog = catalogFacade.findById(catalog.getTopId());
        if (catalog.getType() == 1) {
            return single(model, catalog);
        }

        dto.put("path",catalog.getId());
        //业态首页
        if (catalog.getIsNavigation() != 2) {
            model.addAttribute("catalogName",catalog.getName());
            String[] path = catalog.getPath().split("/");
            Long id = catalog.getId();
            if((catalog.getIsNavigation() == 3) || (path.length-1 == 3)){
                //三级或四级分类
                if(catalog.getReId() != 0 && StringUtil.isNotNull(catalog.getTopId())){
                    //顶级分类
                    CatalogEntity catalogEn = catalogFacade.findById(catalog.getTopId());
                    if(StringUtil.isNotNull(catalogEn)){
                        //四级类别
                        if(path.length-1 == 3){
                            //查询三级类别
                            CatalogEntity catalogEns = catalogFacade.findById(catalog.getReId());
                            if(StringUtil.isNotNull(catalogEns)){
                                dto.put("selectCatalogId",catalogEns.getId());
                                dto.put("selectCatalogName",catalogEns.getName());
                                dto.put("path",catalogEns.getId());
                                dto.put("reId",catalogEns.getReId());
                                dto.put("catalogIdOnt",catalog.getId());
                                dto.put("catalogName",catalogEns.getName());
                                dto.put("catalogDir",catalogEns.getDir());
                                dto.put("catalogNameOne",catalog.getName());
                                dto.put("catalogDirOne",catalog.getDir());
                            }
                            //三级类别
                        }else{
                            dto.put("selectCatalogId",catalog.getId());
                            dto.put("selectCatalogName",catalog.getName());
                            dto.put("path",catalog.getId());
                            dto.put("reId",catalog.getReId());
                            dto.put("catalogName",catalog.getName());
                            dto.put("catalogDir",catalog.getDir());
                        }
                        dto.put("catalogId",catalogEn.getId());
                        dto.put("dir",catalogEn.getDir());
                    }
                    id = catalogEn.getId();
                }else{
                    dto.put("catalogId",catalog.getId());
                    dto.put("catalogName",catalog.getName());
                    dto.put("catalogDir",catalog.getDir());
                    dto.put("path",catalog.getId());
                    dto.put("reId",catalog.getReId());
                    dto.put("dir",catalog.getDir());
                }
                dto.put("catalogLastId",catalog.getId());
                dto.put("catalog",catalog);
                dto.put("serviceName",catalog.getServiceName());
                return index(model, id,dto);
            }
        }
        String path = catalog.getPath();
        String[] idStrs = path.split("/");
        List<CatalogEntity> paths = new ArrayList<CatalogEntity>();
        if(null != idStrs && idStrs.length > 0){
            for (int i = 0; i < idStrs.length; i++) {
                try {
                    CatalogEntity catalogEntity = catalogFacade.findById(Long.parseLong(idStrs[i]));
                    if(catalogEntity.getName().indexOf("百科") == -1){
                        paths.add(catalogEntity);
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }


        Page p = new Page();
        dto.put(Page.SHOWCOUNT, 8);
        if (pageNo == null) {
            pageNo = 1L;
        }
        dto.put(Page.CURRENTPAGE, pageNo);
        p.setPageDTO(dto);
        BizPageResponse<List<DTO>> article = articleFacade.findlistPageByPathOrCatalogId(p);
//        BizPageResponse<List<Map<String, Object>>> pageData = articleFacade.findEnableWithPage(p);
//        List<Map<String, Object>> list = pageData.getObj();
//
//        for (int i = 0; i < list.size(); i++) {
//            ArticleEntity artic = (ArticleEntity) list.get(i);
//            String aa02 = artic.getAa02().toString();
//            aa02 = Utils.getDate(aa02);
//            ((ArticleEntity) list.get(i)).setAddtime(aa02);
//        }
//        pageData.setObj(list);

//        //获取顶级栏目id，用于查找热门排行和推荐阅读
//        Long topId = catalog.getTopId();
//        if (topId == 0) {
//            topId = catalog.getId();
//        }
        model = commons(model);
//        //获取热门排行
//        List<ArticleEntity> hotList = articleFacade.findHotByCataId(topId, 10);
//        //获取推荐阅读
//        List<Map<String, Object>> recommList = articleFacade.findEnableByFlag("c", 10, topId);

        model.addAttribute("paths", paths);
//        model.addAttribute("pageData", pageData);
        model.addAttribute("pageData", article);
//        model.addAttribute("hotList", hotList);
        model.addAttribute("catalog", catalog);
        model.addAttribute("catalogId", catalog.getId());

        model.addAttribute("topCatalog", topCatalog);
        model.addAttribute("totalNum",article.getPage().getTotalResult());
//        model.addAttribute("recommList", recommList);

        String typeName =  dto.getAsString("catalogName");
        if(StringUtil.isEmpty(typeName)){
            typeName = "创业发展";
        }


        String operateDomain = ResourceUtils.getResource("global").getMap().get("operate.domain");
        model.addAttribute("operateDomain",operateDomain);

        Map config = ResourceUtils.getResource("global").getMap();
        String env = config.get("env").toString();
        String  mainDomain = "http://www." + config.get("operate.domain").toString();
        // 获取PC首页
        model.addAttribute("mainDomain",mainDomain);

        List<ArticleEntity> news = articleFacade.findHotByCataId(dto.getAsLong("id"),10);

        model.addAttribute("hotNews",news);
        model.addAttribute("typeName",typeName);


        return "search_result";
    }

    /**
     * 处理单页
     *
     * @param model   数据模型
     * @param catalog 栏目
     * @return
     */
    private String single(Model model, CatalogEntity catalog) {
        model.addAttribute("catalog", catalog);
        return "single";
    }

    /**
     * 文章详情
     *
     * @param id 文章编号
     * @return 详情页
     */
    @RequestMapping(value = "article/{id}.html")
    public String detail(@PathVariable Long id, Model model) {
        return info(model, id);
    }

    /**
     * 头部公共方法，分类，热搜文章
     * @return
     */
    public Model commons(Model model){
        //热搜文章
        List<DTO> hot = articleFacade.findHotSearch(1,null,null);

        //查询所有的业态顶级目录
        List<DTO> classification = catalogFacade.findOperation();
        if(null != classification && StringUtil.isNotNull(classification)){
            for (DTO dto : classification) {
                if(StringUtil.isNotNull(dto.get("id"))){
                    List<CatalogEntity> sub = catalogFacade.findEncyclopedias(dto.getAsLong("id"));
                    dto.put("child",sub);
                }
            }
        }

        //热搜文章
        model.addAttribute("hot",hot);
        //头部业态分类
        model.addAttribute("classification",classification);
        return model;
    }

    /**
     * 首页新闻（业态首页咨询）
     * @param defaultType 3是首页新闻栏目，4是业态首页咨询栏目
     * @param id (业态唯一标识)defaultType为4必填
     * @return
     */
    public DTO newsConsulting(Integer defaultType,Long id){
        DTO catalogDto = new BaseDTO();
        if(defaultType == 4){
            if(null == id && !StringUtil.isNotNull(id)){
                catalogDto.put("status","0");
                return catalogDto;
            }
        }
        //查询栏目
        List<DTO> catalog = catalogFacade.findCatalogEntityByState(defaultType,id);
        if(null != catalog && StringUtil.isNotNull(catalog)){
            for (DTO catalogD: catalog) {
                //新闻（咨询）文章
                List<ArticleEntity> journalism = articleFacade.findEnableAllByCatalogId(catalogD.getAsLong("id"),12);
                List<ArticleEntity> journalisms = new ArrayList<ArticleEntity>();
                if(null != journalism && StringUtil.isNotNull(journalism)){
                    for (ArticleEntity article : journalism) {
                        if((defaultType == 3 && article.getShowHome() == 0) || (defaultType == 4 && article.getShowOperation() == 0)){
                            journalisms.add(article);
                        }
                    }
                }
                catalogDto.put("name",catalogD.getAsString("name"));
                catalogDto.put("id",catalogD.get("id"));
                catalogDto.put("abbreviation",catalogD.get("abbreviation"));
                catalogDto.put("dir",catalogD.get("dir"));
                catalogDto.put("child",journalisms);
                break;
            }
        }
        return catalogDto;
    }

    /**
     * 首页
     *
     * @param model
     * @param id 业态唯一标识
     * @return
     */
    public String index(Model model, Long id) {
        //一级栏目
        CatalogEntity catalog = null;
        //主导航
        List<CatalogEntity> catalogs = catalogFacade.findTop();
        if (id == null) {
            if (catalogs.size() > 0) {
                catalog = catalogs.get(0);
            }
        } else {
            catalog = catalogFacade.findById(id);
        }
        if (catalog == null) {
            return "catalog_not_find";
        }
        //banner
        List<Map<String, Object>> tNews = articleFacade.findEnableByFlag("f", 5, catalog.getId());
        //推荐
        List<Map<String, Object>> cNews = articleFacade.findEnableByFlag("c", 6, catalog.getId());
        //二级栏目
        List<CatalogEntity> twoCatalogs = catalogFacade.findSub(catalog.getId());
        Map<CatalogEntity, List<Map<String, Object>>> pics = new HashMap<CatalogEntity, List<Map<String, Object>>>();
        for (CatalogEntity twoCatalog : twoCatalogs) {
            //三级栏目
            List<CatalogEntity> threeCatalogs = catalogFacade.findSub(twoCatalog.getId());
            for (CatalogEntity threeCatalog : threeCatalogs) {
                List<Map<String, Object>> aNews = articleFacade.findEnableByFlag("a", 2, threeCatalog.getId());
                pics.put(threeCatalog, aNews);
                List<CatalogEntity> fourCatalogs = catalogFacade.findSub(threeCatalog.getId());
                List<ArticleEntity> threeArtcles = articleFacade.findEnableAllByCatalogId(threeCatalog.getId(), 6);
                threeCatalog.setArticles(threeArtcles);
                threeCatalog.setCatalogs(fourCatalogs);
            }
            twoCatalog.setCatalogs(threeCatalogs);
        }
        catalog.setCatalogs(twoCatalogs);

        model.addAttribute("pics", pics);
        model.addAttribute("catalog", catalog);
        model.addAttribute("topCatalog", catalog);
        model.addAttribute("navs", catalogs);
        model.addAttribute("fNews", tNews);
        model.addAttribute("cNews", cNews);
        return "catalog";
    }

    /**
     * 列表分页
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "articlePage.html")
    public void articlePage(HttpServletResponse response) throws IOException {
        WebResponse wb = new WebResponse();
        DTO dto = getParamAsDTO();
        if(dto.size()<0){
            wb.setAjaxMsg(false,"查询失败！",null,null);
        }
        Page page = new Page();
        page.setShowCount(8);
        page.setCurrentPage(dto.getAsInteger("currentPage"));
        page.setPageDTO(dto);
        //如果栏目标识有值并且是第一次加载，则为业态首页分类异步查询
        if(StringUtil.isNotNull(dto.get("cId")) && page.getCurrentPage() == 1){
            //根据分类标识查询子栏目
            List<CatalogEntity> list = catalogFacade.findSub(dto.getAsLong("cId"));
            if(null != list && list.size() > 0){
                wb.put("catalogList",list);
            }
        }
        BizPageResponse<List<DTO>> article = articleFacade.findlistPageByPathOrCatalogId(page);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(article.getObj()));
    }

    /**
     * 搜索列表页
     * @return
     */
    @RequestMapping(value = "articleSearch/{title}/{catalogId}.html")
    public String articlePage(@PathVariable String title, Model model,Integer currentPage,@PathVariable String catalogId){
        WebResponse wb = new WebResponse();
        DTO dto = new BaseDTO();
        if(dto.size()<0){
            wb.setAjaxMsg(false,"查询失败！",null,null);
        }
        Page page = new Page();
        if(null != currentPage){
            page.setCurrentPage(currentPage);
        }
        if(StringUtil.equals("catalogId",catalogId)){
            dto.put("title",title);
            model.addAttribute("title",title);
            model.addAttribute("catalogId",null);
        }else{
            CatalogEntity catalogEntity = catalogFacade.findById(Long.parseLong(catalogId));
            if(null != catalogEntity){
                String path = catalogEntity.getPath();
                String[] idStrs = path.split("/");
                List<CatalogEntity> paths = new ArrayList<CatalogEntity>();
                if(null != idStrs && idStrs.length > 0){
                    for (int i = 0; i < idStrs.length; i++) {
                        try {
                            paths.add(catalogFacade.findById(Long.parseLong(idStrs[i])));
                        } catch (Exception e) {
                            continue;
                        }
                    }
                }
                model.addAttribute("paths",paths);
                model.addAttribute("catalogId",catalogId);
                model.addAttribute("catalog",catalogEntity);
                model.addAttribute("title",null);
                dto.put("path",catalogId);
            }

        }
        page.setPageDTO(dto);
        page.setShowCount(8);
        BizPageResponse<List<DTO>> article = articleFacade.findlistPageByPathOrCatalogId(page);
        model = commons(model);
        model.addAttribute("pageData", article);
        if(null != article){
            model.addAttribute("totalNum",article.getPage().getTotalResult());
        }

        String typeName =  dto.getAsString("catalogName");
        if(StringUtil.isEmpty(typeName)){
            typeName = "创业发展";
        }

        String operateDomain = ResourceUtils.getResource("global").getMap().get("operate.domain");
        model.addAttribute("operateDomain",operateDomain);

        List<ArticleEntity> news = articleFacade.findHotByCataId(dto.getAsLong("id"),10);

        model.addAttribute("hotNews",news);

        return "search_result";
    }

//    /**
//     * 在crm上保存客户信息
//     * <P>@param customerName     客户姓名
//     * <P>@param customerPhone    客户电话
//     * <p>@param sex              性别
//     * <P>@param money           贷款金额
//     * <P>@param cusState        状态，1为贷款信息，2为预约服务信息
//     * </P>
//     */
//    @RequestMapping(value = "addClue.htm")
//    public JSONObject addClue() {
//        DTO dto = getParamAsDTO();
//        WebResponse wb = new WebResponse();
//        Map<String,Object> params = new HashMap<String,Object>();
//        //设置全局变量值
//        if(!StringUtil.isNotNull(dto.get("cusState")) && !StringUtil.isNotNull(dto.get("customerPhone"))){
//            wb.setAjaxMsg(false,"参数不能为空",null,null);
//        }else{
//            if(StringUtil.equals("1",dto.getAsString("cusState"))){
//                params.put("cusName",dto.getAsString("customerName")+((null != dto.getAsInteger("sex") && StringUtil.isNotNull(dto.getAsInteger("sex")))? 1 == dto.getAsInteger("sex")?"女士":"先生":""));
//                if(StringUtil.isNotNull(dto.getString("money"))){
//                    params.put("remark","贷款金额：￥"+dto.getString("money")+"万元");
//                }else{
//                    params.put("remark","贷款申请");
//                }
//            }
//        }
//
//        params.put("cusTel",dto.getAsString("customerPhone"));
//        params.put("adviserId","0");
//        params.put("adviserPhone","0");
//        params.put("adviserName","无");
//        params.put("sourcesTer","1");
//        params.put("siteId","10000");
//        params.put("siteName","成都站");
//        Long result = consultFacade.addPhoneConsult(params);
//        String msg = "";
//        if(null != result && StringUtil.isNotNull(result)){
//            msg = "{\"status \":\"2\",\"message\":\"保存成功\"}";
//        }else{
//            msg = "{\"status \":\"0\",\"message\":\"保存失败\"}";
//        }
//        JSONObject object = JSONObject.parseObject(msg);
//        return object;
//    }
//
//    /**
//     * 模板通用方法
//     * @param model
//     * @return
//     */
//    public Model commonsTemplat(Model model){
//
//        // 根据站点标识查询当前站点下的所有模板信息数据
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("siteId", "100000");
//        map.put("ttype", "4");//模板类型 挂件模板
//        map.put("channel", "7");//模板使用终端 wx
//        map.put("siteState", "1"); //站定模板状态 启用
//
//        List<DTO> list = templateFacade.selectTeamplatesByTypeAndYt(map);
//        if (list != null && list.size() != 0) {
//            for (DTO dto: list) {
//                model.addAttribute(dto.getAsString("ptPosition"), dto.getAsString("temId"));
//            }
//        }
//
//        return model;
//    }
}
