package com.depth.cms.content.metatype.util;

import com.depth.cms.content.metatype.DTO;
import com.depth.cms.content.metatype.JsonValueProcessorImpl;
import com.depth.cms.content.metatype.impl.BaseDTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 11:31 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class JsonHelper {
    private static Log log = LogFactory.getLog(JsonHelper.class);

    public JsonHelper() {
    }

    public static final String encodeObject2Json(Object pObject) {
        String jsonString = "[]";
        if(!BocUtils.isEmpty(pObject)) {
            if(pObject instanceof ArrayList) {
                JSONArray jsonObject = JSONArray.fromObject(pObject);
                jsonString = jsonObject.toString();
            } else {
                JSONObject jsonObject1 = JSONObject.fromObject(pObject);
                jsonString = jsonObject1.toString();
            }
        }

        if(log.isInfoEnabled()) {
            log.info("序列化后的JSON资料输出:\n" + jsonString);
        }

        return jsonString;
    }

    public static final String encodeObject2Json(Object pObject, String pFormatString) {
        String jsonString = "[]";
        if(!BocUtils.isEmpty(pObject)) {
            JsonConfig cfg = new JsonConfig();
            cfg.registerJsonValueProcessor(Timestamp.class, new JsonValueProcessorImpl(pFormatString));
            cfg.registerJsonValueProcessor(Date.class, new JsonValueProcessorImpl(pFormatString));
            cfg.registerJsonValueProcessor(java.sql.Date.class, new JsonValueProcessorImpl(pFormatString));
            if(pObject instanceof ArrayList) {
                JSONArray jsonObject = JSONArray.fromObject(pObject, cfg);
                jsonString = jsonObject.toString();
            } else {
                JSONObject jsonObject1 = JSONObject.fromObject(pObject, cfg);
                jsonString = jsonObject1.toString();
            }
        }

        if(log.isInfoEnabled()) {
            log.info("序列化后的JSON资料输出:\n" + jsonString);
        }

        return jsonString;
    }

    private static String encodeJson2PageJson(String jsonString, Integer totalCount) {
        jsonString = "{TOTALCOUNT:" + totalCount + ", ROOT:" + jsonString + "}";
        if(log.isInfoEnabled()) {
            log.info("合并后的JSON资料输出:\n" + jsonString);
        }

        return jsonString;
    }

    public static final String encodeList2PageJson(List list, Integer totalCount, String dataFormat) {
        String subJsonString = "";
        if(BocUtils.isEmpty(dataFormat)) {
            subJsonString = encodeObject2Json(list);
        } else {
            subJsonString = encodeObject2Json(list, dataFormat);
        }

        String jsonString = "{TOTALCOUNT:" + totalCount + ", ROOT:" + subJsonString + "}";
        if(log.isInfoEnabled()) {
            log.info("序列化后的JSON资料输出:\n" + jsonString);
        }

        return jsonString;
    }

    public static String encodeDto2FormLoadJson(DTO pDto, String pFormatString) {
        String jsonString = "";
        String sunJsonString = "";
        if(BocUtils.isEmpty(pFormatString)) {
            sunJsonString = encodeObject2Json(pDto);
        } else {
            sunJsonString = encodeObject2Json(pDto, pFormatString);
        }

        jsonString = "{success:" + (BocUtils.isEmpty(pDto.getAsString("success"))?"true":pDto.getAsString("success")) + ",data:" + sunJsonString + "}";
        if(log.isInfoEnabled()) {
            log.info("序列化后的JSON资料输出:\n" + jsonString);
        }

        return jsonString;
    }

    public static DTO parseSingleJson2Dto(String jsonString) {
        BaseDTO dto = new BaseDTO();
        if(BocUtils.isEmpty(jsonString)) {
            return dto;
        } else {
            JSONObject jb = JSONObject.fromObject(jsonString);
            dto = (BaseDTO)JSONObject.toBean(jb, BaseDTO.class);
            return dto;
        }
    }

    public static List parseJson2List(String jsonString) {
        ArrayList list = new ArrayList();
        JSONObject jbJsonObject = JSONObject.fromObject(jsonString);
        Iterator iterator = jbJsonObject.keySet().iterator();

        while(iterator.hasNext()) {
            DTO dto = parseSingleJson2Dto(jbJsonObject.getString(iterator.next().toString()));
            list.add(dto);
        }

        return list;
    }
}
