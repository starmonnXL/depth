package com.depth.cms.content.metatype.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/22 13:51 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

public class MybatisHepler {
    public MybatisHepler() {
    }

    public static void main(String[] args) {
        String sql = "SELECT \tCAST(TBSS.SPUID AS CHAR) AS spuId, \tTSP.`NAME` AS goodsName, \tTSP.BANNER AS banner, \tTSP.ATTENTION AS attention, \tTSP.VOMULE AS vomule, \tTSP.SCORE AS goodsScore, \tCAST(FS.skuId AS CHAR) AS skuId, \tFS.priceSku, \tFS.priceOfMarket, \tFS.proId, \tFS.priceOfSale, \tFS.type, \tFS.beginTime, \tFS.endTime, \tDATE_FORMAT(NOW(), \'%Y%m%d%H%i%s\') AS systemTime, \tFS.inventoryOfSurplus FROM \tpt_business.pt_business_adviser TBADV LEFT JOIN pt_business.pt_business_site_spu TBSS ON TBSS.BID = TBADV.BID AND TBSS.SID = TBADV.SID LEFT JOIN pt_goods.pt_goods_spu TSP ON TSP.ID = TBSS.SPUID LEFT JOIN ( \tSELECT \t\tGS.* \tFROM \t\t( \t\t\tSELECT \t\t\t\tOTHER.* \t\t\tFROM \t\t\t\t( \t\t\t\t\tSELECT \t\t\t\t\t\tTGS.ID AS skuId, \t\t\t\t\t\tTGS.SPUID AS spuId, \t\t\t\t\t\tTGS.PRICE AS priceOfMarket, \t\t\t\t\t\tTOPS.PID AS proId, \t\t\t\t\t\tTOPS.PRICEOFSALE AS priceOfSale, \t\t\t\t\t\tTP.TYPE AS type, \t\t\t\t\t\tTP.BEGINTIME AS beginTime, \t\t\t\t\t\tTP.ENDTIME AS endTime, \t\t\t\t\t\tTOPS.INVENTORYOFSURPLUS AS inventoryOfSurplus,  \t\t\t\t\tIF ( \t\t\t\t\t\tTSPU.ALLOWSELFPRICE = 1,  \t\t\t\t\tIF ( \t\t\t\t\t\tTC.MODIFYPRICE = 0,  \t\t\t\t\tIF ( \t\t\t\t\t\tTSKU.PRICEOFB IS NULL, \t\t\t\t\t\tTGS.PRICE, \t\t\t\t\t\tTSKU.PRICEOFB \t\t\t\t\t),  \t\t\t\tIF ( \t\t\t\t\tPA.PRICEOFA IS NULL,  \t\t\t\tIF ( \t\t\t\t\tTSKU.PRICEOFB IS NULL, \t\t\t\t\tTGS.PRICE, \t\t\t\t\tTSKU.PRICEOFB \t\t\t\t), \t\t\t\tPA.PRICEOFA \t\t\t\t) \t\t\t\t\t), \t\t\t\t\tTGS.PRICE \t\t\t\t\t) AS priceSku \t\t\t\t\tFROM \t\t\t\t\t\tpt_business.pt_business_site_spu KA \t\t\t\t\tLEFT JOIN pt_goods.pt_goods_sku TGS ON TGS.SPUID = KA.SPUID \t\t\t\t\tLEFT JOIN pt_goods.pt_goods_spu TSPU ON TSPU.ID = TGS.SPUID \t\t\t\t\tLEFT JOIN pt_operate.pt_operate_promotion_sku TOPS ON TOPS.SKUID = TGS.ID \t\t\t\t\tAND TOPS.STATE = \'1\' \t\t\t\t\tAND TOPS.PID IN ( \t\t\t\t\t\tSELECT \t\t\t\t\t\t\tTOP.ID \t\t\t\t\t\tFROM \t\t\t\t\t\t\tpt_operate.pt_operate_promotion_adviser PROAD \t\t\t\t\t\tLEFT JOIN pt_operate.pt_operate_promotion TOP ON PROAD.PID = TOP.ID \t\t\t\t\t\tAND \'01\' IN (TOP.TERMINAL) -- 活动终端类型 \t\t\t\t\t\tAND ( \t\t\t\t\t\t\t( \t\t\t\t\t\t\t\tTOP.STATE = \'5\' \t\t\t\t\t\t\t\tAND TOP.BEGINTIME <= 20170527152035 -- 活动时间判断 \t\t\t\t\t\t\t\tAND TOP.ENDTIME > 20170527152035 \t\t\t\t\t\t\t) \t\t\t\t\t\t\tOR ( \t\t\t\t\t\t\t\tTOP.STATE = \'3\' \t\t\t\t\t\t\t\tAND TOP.BEGINTIME <= 20170527152035 -- 活动时间判断 \t\t\t\t\t\t\t\tAND TOP.ENDTIME > 20170527152035 -- 活动时间判断 \t\t\t\t\t\t\t) \t\t\t\t\t\t) \t\t\t\t\t\tWHERE \t\t\t\t\t\t\tPROAD.AID = 866911031098740736 \t\t\t\t\t\tAND PROAD.STATE = \'1\' \t\t\t\t\t) \t\t\t\t\tLEFT JOIN pt_operate.pt_operate_promotion TP ON TP.ID = TOPS.PID \t\t\t\t\tAND \t\t\t\t\tIF ( \t\t\t\t\t\tTP.TYPE = \'3\', \t\t\t\t\t\tTOPS.INVENTORYOFSURPLUS > 0, \t\t\t\t\t\t1 = 1 \t\t\t\t\t) \t\t\t\t\tAND \'01\' IN (TP.TERMINAL) -- 活动终端类型 \t\t\t\t\tLEFT JOIN pt_business.pt_business_adviser TA ON TA.SID = KA.SID \t\t\t\t\tLEFT JOIN pt_business.pt_business_spu_confirm TC ON TC.SPUID = TGS.SPUID \t\t\t\t\tAND ( \t\t\t\t\t\tTC.SID = TA.SID \t\t\t\t\t\tOR TC.SID IS NULL \t\t\t\t\t) \t\t\t\t\tLEFT JOIN pt_business.pt_business_skuprice TSKU ON TSKU.SKUID = TGS.ID \t\t\t\t\tAND ( \t\t\t\t\t\tTSKU.SID = TA.SID \t\t\t\t\t\tOR TSKU.SID IS NULL \t\t\t\t\t) \t\t\t\t\tLEFT JOIN pt_business.pt_business_adviser_skuprice PA ON PA.SKUID = TGS.ID \t\t\t\t\tAND PA.AID = TA.ID \t\t\t\t\tAND ( \t\t\t\t\t\tPA.SID = TA.SID \t\t\t\t\t\tOR PA.SID IS NULL \t\t\t\t\t) \t\t\t\t\tWHERE \t\t\t\t\t\tTGS.STATE = \'1\' \t\t\t\t\tAND KA.BID = 863930221374607360 \t\t\t\t\tAND KA.SITEID = \'100000\' \t\t\t\t\tAND KA.STATE = \'1\' -- 顾问商家编号和站点信息 \t\t\t\t\tAND TA.ID = 866911031098740736 \t\t\t\t) AS OTHER \t\t\tORDER BY \t\t\t\tOTHER.endTime IS NULL, \t\t\t\tOTHER.type = 4, \t\t\t\tOTHER.type DESC, \t\t\t\tOTHER.endTime ASC, \t\t\t\tOTHER.priceOfSale ASC, \t\t\t\tOTHER.priceSku = - 1, \t\t\t\tOTHER.priceSku ASC \t\t) AS GS \tGROUP BY \t\tGS.spuId ) AS FS ON FS.spuId = TBSS.SPUID WHERE \tTBSS.STATE = \'1\' AND TBADV.ID NOT IN ( \tSELECT \t\tTAS.ADVISERID \tFROM \t\tpt_business.pt_business_adviser_spuwhite TAS \tWHERE \t\tTAS.SPUID = TBSS.SPUID \tAND TAS.SID = TBSS.SID \tAND TAS.SID = TBADV.SID ) AND TBADV.ID = 866911031098740736 -- 顾问编号 AND skuId IS NOT NULL AND TBSS.SITEID = \'100000\' -- 站点 GROUP BY \tTBSS.SPUID ORDER BY \tpriceOfSale IS NULL, \tFS.type = 4, \tFS.type DESC, \tpriceOfSale ASC, \tpriceSku IS NULL, \tpriceSku = - 1, \tpriceSku ASC, \tskuId ASC";
        long t = System.currentTimeMillis();
        System.out.println((new MybatisHepler()).getCountSql(sql));
        System.out.println(System.currentTimeMillis() - t);
        System.out.println((new MybatisHepler()).getPagingSql(sql, 0, 10));
    }

    public String getCountSql(String querySelect) {
        querySelect = this.compress(querySelect);
        int orderIndex = this.getLastOrderInsertPoint(querySelect);
        int formIndex = this.getAfterFormInsertPoint(querySelect);
        String select = querySelect.substring(0, formIndex);
        return select.toLowerCase().indexOf("select distinct") == -1 && querySelect.toLowerCase().indexOf("group by") == -1?(new StringBuffer(querySelect.length())).append("select count(1) count ").append(querySelect.substring(formIndex, orderIndex)).toString():(new StringBuffer(querySelect.length())).append("select count(1) count from (").append(querySelect.substring(0, orderIndex)).append(" ) t").toString();
    }

    private int getLastOrderInsertPoint(String querySelect) {
        int orderIndex = querySelect.toLowerCase().lastIndexOf("order by");
        if(orderIndex == -1) {
            orderIndex = querySelect.length();
        } else if(!this.isBracketCanPartnership(querySelect.substring(orderIndex, querySelect.length()))) {
            throw new RuntimeException("My SQL 分页必须要有Order by 语句!");
        }

        return orderIndex;
    }

    public String getPagingSql(String querySelect, int offset, int limit) {
        querySelect = this.compress(querySelect);
        String sql = querySelect.replaceAll("[^\\s,]+\\.", "") + " limit " + offset + " ," + limit;
        return sql;
    }

    private String compress(String sql) {
        return sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
    }

    private int getAfterFormInsertPoint(String querySelect) {
        String regex = "\\s+FROM\\s+";
        Pattern pattern = Pattern.compile(regex, 2);
        Matcher matcher = pattern.matcher(querySelect);

        int fromStartIndex;
        String text;
        do {
            if(!matcher.find()) {
                return 0;
            }

            fromStartIndex = matcher.start(0);
            text = querySelect.substring(0, fromStartIndex);
        } while(!this.isBracketCanPartnership(text));

        return fromStartIndex;
    }

    private boolean isBracketCanPartnership(String text) {
        return text != null && this.getIndexOfCount(text, '(') == this.getIndexOfCount(text, ')');
    }

    private int getIndexOfCount(String text, char ch) {
        int count = 0;

        for(int i = 0; i < text.length(); ++i) {
            count = text.charAt(i) == ch?count + 1:count;
        }

        return count;
    }
}
