package com.depth.cms.member.realm;

/**
 * 会员登录
 * <p>@Title LoginMemberAppRealm</p>
 * <p>@Description com.dgg.business.member</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年2月4日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class AgentLoginRealm {
//public class AgentLoginRealm extends AuthorizingRealm {
//
//    @Resource
//    private MemberFrontFacade memberFrontFacade;
//
//    @Resource
//    private WeiXinMemberFacade weiXinMemberFacade;
//
//    @Resource
//    private OperateSiteFacade operateSiteFacade;
//
//    @Resource
//    private AgentFacade agentFacade;
//
//    @Override
//    public AuthenticationInfo doGetAuthenticationInfo(String userName, String passwd, String... params)
//            throws PermissionsException {
//        if (BocUtils.isEmpty(userName)) {
//            throw PermissionsException.INSTANCE.newInstance("用户名不能为空");
//        }
//        DTO dto;
//        String loginAccount = "";//用于解绑界面展示的当前登录账号
//        if (StringUtil.equals(params[1].split("@")[0], "2")) {
////			dto = memberFrontFacade.loginByPhone(userName,params[0], "1");
//            String shareId = "";
//            if (params[1].split("@").length > 1) {
//                shareId = params[1].split("@")[1];
//            }
//
//            dto = new BaseDTO(weiXinMemberFacade.memberLoginInvite(userName, params[0], "2", shareId));
//            if (null != dto) {
//                dto.put("phone", userName);
//                dto.put("loginName", userName);
//                loginAccount = userName;
//            }
//        } else if (StringUtil.equals(params[1], "3")) {// 用户名 + 密码
//            dto = memberFrontFacade.queryMemberDetailInfo(userName, passwd, null);
//            if (null != dto) {
//                loginAccount = userName;
//            }
//        } else if (StringUtil.equals(params[1], "4")) {//unionID
//            //由于传入的字符串数组参数只截取了第一个元素，所以当类型为4时，unionID的值用userName传入
//            dto = memberFrontFacade.queryMemberDetailInfo(null, null, userName);
//            if (null != dto) {
//                loginAccount = "0";
//            }
//        } else {
//            dto = memberFrontFacade.memberLogin(userName, passwd, params[0], "1");
//        }
//        if (dto == null) {
//            throw PermissionsException.ACCOUNT_FAIL.newInstance("用户登陆失败");
//        }
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        Session session = MemberSecurityManager.getSubject().getSession();
//
//        String siteId = "", siteName = "";
//        String cookieSiteId = ResourceUtils.getResource("global").getMap().get("siteName");
//        String cookieSiteName = ResourceUtils.getResource("global").getMap().get("siteName");
//        // 判断session里面是否有当前站点数据
//        if (session != null) {
//            siteId = (String) session.getAttribute("siteId");
//            siteName = (String) session.getAttribute("siteName");
//            if (StringUtil.isEmpty(siteId) || StringUtil.isEmpty(siteName)) {
//                //直接从MemberBaseController获取
//                siteId = MemberBaseController.getSiteId(request);
//                siteName = MemberBaseController.getSiteName(request);
//            }
//            // 如果session里面没有站点标识，则直接获取
//        } else {
//            siteId = MemberBaseController.getSiteId(request);
//            siteName = MemberBaseController.getSiteName(request);
//        }
//        // 将当前选中的站点标识存入session中
//        DTO site = operateSiteFacade.loadSiteInfo(siteId);
//        session.setAttribute("operateSite", site);
//        session.setAttribute("siteId", siteId);
//        session.setAttribute("siteName", siteName);
//        ResourceUtils.getResource("global").getMap();
//        dto.put("url", ResourceUtils.getResource("global").getMap().get("dgg.file.fileprifix"));
//        String memberId = dto.getAsString("memberId");
//        session.setAttribute("memberId", memberId);
//        session.setAttribute("member", dto);
//        session.setAttribute("phone", dto.getAsString("loginName"));
//        session.setAttribute("loginAccount", loginAccount);
//        String isAdviser = dto.getAsString("isAdviser");
//        if (!StringUtil.isEmpty(isAdviser) && "1".equals(isAdviser)) {
//            session.setAttribute("isAdviser", isAdviser);
//            session.setAttribute("memberId", dto.getAsString("ID"));
//            memberId = dto.getAsString("ID");
//            session.setAttribute("phone", dto.getAsString("PHONE"));
//        }
//
//        //添加代理商角色信息
//        DTO agent = agentFacade.getAgentInfoByMemberId(NumberUtils.createLong(memberId));
//        if (dto == null) {
//            throw PermissionsException.ACCOUNT_FAIL.newInstance("该用户不是合法的代理商系统用户");
//        }
//        if (agent!=null && "1".equals(agent.getAsString("state"))){
//            session.setAttribute("agent", agent);
//        }
//        return new MemberAuthenticationInfo(getTokenEncryption().encrypt(memberId, userName, passwd, params[1]));
//    }

}
