package com.depth.cms.content.biz;

import com.depth.cms.content.dao.ArticleDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hj87080234@gmail.com
 * @date 2016年8月18日
 */
@Service("contentBiz")
public class ContentBiz {
    @Resource
    private ArticleDao articleDao;


}
