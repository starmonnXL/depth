package com.depth.cms.controller.commons.web;

import com.depth.cms.commons.page.BizPageResponse;
import com.depth.cms.commons.page.Page;
import com.depth.cms.content.metatype.DTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 15:51 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class WebResponse extends HashMap<String, Object> {
    private static final long serialVersionUID = -7266609079324764152L;

    public WebResponse() {
    }

    public void addValue(String key, Object value) {
        this.put(key, value);
    }

    public void addValue(Map map) {
        if(map != null) {
            this.putAll(map);
        }

    }

    public void addDto(DTO dto) {
        if(dto != null) {
            this.putAll(dto);
        }

    }

    public void addValue(String key, String value) {
        this.put(key, value);
    }

    public void addResult(List value) {
        this.addValue("result", (Object)value);
    }

    public void setAppCode(String code) {
        this.put("appcode", code);
    }

    public void setAppCode(String code, String msg) {
        this.put("appcode", code);
        this.put("appmsg", msg);
    }

    public void setAjaxMsg(boolean status, String Info, String url, String data) {
        this.put("status", Boolean.valueOf(status));
        this.put("info", Info);
        this.put("url", url);
        this.put("data", data);
    }

    public void setDataGridMsg(boolean status, String Info, List<?> rows, Integer total) {
        this.put("status", Boolean.valueOf(status));
        this.put("info", Info);
        this.put("rows", rows);
        this.put("total", total);
    }

    private void setPageValue(Page page) {
        this.put("showCount", Integer.valueOf(page.getShowCount()));
        this.put("totalPage", Integer.valueOf(page.getTotalPage()));
        this.put("totalResult", Integer.valueOf(page.getTotalResult()));
        this.put("currentPage", Integer.valueOf(page.getCurrentPage()));
        this.put("currentResult", Integer.valueOf(page.getCurrentResult()));
    }

    public void setPageResult(String resultKey, BizPageResponse<?> pageResult) {
        this.put(resultKey, pageResult.getObj());
        this.setPageValue(pageResult.getPage());
    }

    public void setPageResult(BizPageResponse<?> pageResult) {
        this.put("result", pageResult.getObj());
        this.setPageValue(pageResult.getPage());
    }
}

