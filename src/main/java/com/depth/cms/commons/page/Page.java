package com.depth.cms.commons.page;

import com.depth.cms.content.metatype.DTO;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 11:47 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class Page implements Serializable {
    private static final long serialVersionUID = 8010409295674435347L;
    public static final String SHOWCOUNT = "rows";
    public static final String CURRENTPAGE = "page";
    private int showCount = 10;
    private int totalPage;
    private int totalResult;
    private int currentPage = 1;
    private int currentResult;
    private boolean entityOrField;
    private Map dto;

    public Page() {
    }

    public int getTotalPage() {
        if(this.totalResult <= 0) {
            this.totalResult = 0;
            return 0;
        } else {
            if(this.totalResult % this.showCount == 0) {
                this.totalPage = this.totalResult / this.showCount;
            } else {
                this.totalPage = this.totalResult / this.showCount + 1;
            }

            return this.totalPage;
        }
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalResult() {
        return this.totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getCurrentPage() {
        if(this.currentPage <= 0) {
            this.currentPage = 1;
        }

        if(this.currentPage > this.getTotalPage()) {
            this.currentPage = this.getTotalPage();
        }

        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage >= 1?currentPage:1;
        this.setCurrentResult((this.currentPage - 1) * this.getShowCount());
    }

    public int getShowCount() {
        return this.showCount;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public int getCurrentResult() {
        this.currentResult = (this.getCurrentPage() - 1) * this.getShowCount();
        if(this.currentResult < 0) {
            this.currentResult = 0;
        }

        return this.currentResult;
    }

    public void setCurrentResult(int currentResult) {
        this.currentResult = currentResult;
    }

    public boolean isEntityOrField() {
        return this.entityOrField;
    }

    public void setEntityOrField(boolean entityOrField) {
        this.entityOrField = entityOrField;
    }

    public Map getDto() {
        return this.dto;
    }

    public void setDto(Map dto) {
        this.dto = dto;
    }

    public void setPageDTO(DTO dto) {
        if(!StringUtils.isEmpty(dto.getAsString("rows")) && !StringUtils.isEmpty(dto.getAsString("page"))) {
            this.setShowCount(Integer.parseInt(dto.getAsString("rows")));
            if(dto.containsKey("page")) {
                this.setCurrentPage(Integer.parseInt(dto.getAsString("page")));
            }
        } else if(!StringUtils.isEmpty(dto.getAsString("currentPage")) && !StringUtils.isEmpty(dto.getAsString("showCount"))) {
            this.setShowCount(Integer.parseInt(dto.getAsString("showCount")));
            this.setCurrentPage(Integer.parseInt(dto.getAsString("currentPage")));
        }

        this.dto = dto;
    }

    public void setRows(int rows) {
        this.setShowCount(rows);
    }

    public void setPage(int page) {
        this.setCurrentPage(page);
    }
}

