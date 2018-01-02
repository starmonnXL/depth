package com.depth.cms.commons.util;

/**
 * <p>@Title  标题</p>
 * <p>@Description  描述</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Li Hongqiang 作者</p>
 * <p>@date 2017.3.2 创建日期</p>
 * <p>lihongqiang@dgg.net 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class PagingUtil {

    /**
     * 计算分页起始位置
     * @param pageIndex 页码(为空默认第一页)
     * @param pageSize  分页大小（为空默认5）
     * @return
     */
    public static Integer countStartOffset(Integer pageIndex,Integer pageSize){
        if(null == pageIndex) pageIndex = 1;
        if(null == pageSize) pageSize = 5;
        return (pageIndex - 1) * pageSize;
    }
}
