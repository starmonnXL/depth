/*
 * 登录、注册、找回密码
 * Author:xtt
 * 2017.03.15
 */
/**
 * gotoPage==1 刷新页面
 * gotoPage==2 去个人中心
 * gotoPage==3 结算
 * gotoPage==4 不刷新页面登录
 */
var mainDomain = "http://www.dggpp.net/";
$(function (){
    $('#phone').on('input', function (){
        activeteMemberCode(function (data){
        });
    });

    $(".search-form-tab").find("li").click(function (){
        $(".search-form-tab li").removeClass("active");
        $(this).addClass("active");
    });

    $("#queryOrerInIndex").submit(function (e){
        e.stopPropagation();
        e.preventDefault();
        queryOrder();
    });

    //复选框样式美化js
    $('.change-check').change(function (){
        var ischeck = $(this).prop("checked");
        if(ischeck){
            $(this).parents('label').addClass('checked');
        } else {
            $(this).parents('label').removeClass('checked');
        }
    });
    $("#getPhoneCode").click(function (){
        giveValidate();
    });

    //tab切换
    $('.login-box-tab').myTab({
        content: '.type-main',
        tabTitle: '.type-tit'
    });

    //表单提交

    $("#validateSubmitBus").keyup(function (e){
        var code = e.keyCode;
        if(code == 13){
            busLogin();
        }
        //console.log(code);
    });

    window.searchSubmitHandel = function (e){
        if(e.target.nodeName == "A"){
            return;
        }
        var id = $(".search-form-tab>li[class=active]").attr("id");
        var keyword = $("#keyword").val().trim();
        if(keyword == "请输入关键字查询"){
            keyword = "";
        }
        //找服务
        if(id === "li1"){
            buildFormndSubmit("http://www.dggpp.net/goods/search.htm", {
                keyword: keyword
            });
        }
        //查公司
        if(id === "li2"){
            buildFormndSubmit(mainDomain + "company/search.htm", {
                keyword: keyword,
            });
        }
        //找顾问
        if(id === "li3"){
            buildFormndSubmit(adviserDomain + "adviser/search.htm", {
                keyword: keyword
            });
        }
        //查商标
        if(id === "li4"){
            buildFormndSubmit(mainDomain + "trademark/search.htm", {
                keyword: keyword
            });
        }
    };
    //会员head搜索
    $('body').on("click", ".search-form-box>button", searchSubmitHandel);
    $("#querySearch").submit(function (e){
        e.stopPropagation();
        e.preventDefault();
        searchSubmitHandel(e);
    });
});

function buildFormndSubmit(url, keywords){
    var temp_form = document.createElement("form");
    temp_form.action = url;
    //如需打开新窗口，form的target属性要设置为'_blank'
    // temp_form.target = "_blank";
    temp_form.method = "get";
    temp_form.style.display = "none";
    for (var key in keywords) {
        var opt = document.createElement("textarea");
        opt.name = key;
        opt.value = keywords[key];
        temp_form.appendChild(opt);
    }
    document.body.appendChild(temp_form);
    //提交数据
    temp_form.submit();
}