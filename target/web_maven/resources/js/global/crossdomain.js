var fileUpmsPath = '';
var fileurl = '';
var goodsPath = '';
$(function(){
    fileurl = $("#fileAccessUrl").val()+"getDomain.sec";
    $.ajax({
        type:"POST",
        url:fileurl,
        dataType:'jsonp',
        jsonp:'callback',
        success:function(data) {
            document.domain = data.domain;
            fileUpmsPath = data.fileAccessUrl;
            goodsPath = data.goodsAccessUrl;
        }
    });
});