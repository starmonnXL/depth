/**
 * Created by zhanghong on 2017/6/26.
 */
/**
 * 廣告位代碼，
 * 2017/6/9
 * zhanghong
 */

$(function(){
    var timecount = 1;
    var urlRoot = "http://www.dgg.net/";
    window.addEventListener('load',function () {
        //開起一個定時器，延時1000毫秒  以錯過load 回調邏輯的高峰執行時間段
        setTimeout(timeOurHander,200);
    });

    function timeOurHander() {
        timecount ++;
        var $ = jQuery;
        var advlist = $(".dgg-adv-box");
        if(advlist.length == 0){
            return;
        }else{
            var ADids = "";
            advlist.each(function(index,value){
                var adv_box = $(value);
                if(adv_box.attr("isInit") == "1"){
                    return;
                }
                var advId = adv_box.attr("adv-id");
                adv_box.attr("isInit","1");
                ADids += ","+advId;
            });
            //console.log(ADids);
            loadADState(ADids.slice(1),advlist);
        }
        if(timecount >5){
            return;
        }
        setTimeout(timeOurHander,200*timecount);
    }

    function loadADState(ADids,advlist) {
        var baseurl = urlRoot || rootPath;
        if(ADids == null || ADids == ""){
            return;
        }
        //debugger;
        $.ajax({
            type: "POST",
            url: baseurl + "AD/getADState.htm",
            data: {
                advId:ADids
            },
            dataType:"jsonp",
            success: function(data) {
                if(data.status){
                    if(data.rst == null){
                        return;
                    }

                    builADbox(data.rst,baseurl,advlist);

                }
            }
        });
    }

    function builADbox(list,baseurl,advlist) {

        for(var i=0;i<list.length;i++){
            var temp = list[i];
            if(temp.contentState != "1" || temp.data==null || temp.data.length<1){
                continue;
            }
            var adv_box = $('.dgg-adv-box[adv-id='+list[i].advId+']');
            //console.log(adv_box);
            var data = list[i];
            var type = data.adType;
            var advId = data.advId;
            var width = adv_box.attr("adv-width");
            var height = adv_box.attr("adv-height");
            adv_box.css("width",width+"px");
            adv_box.css("height",height+"px");




            var iframe = document.createElement('iframe');
            iframe.width = width;
            iframe.height = height;
            iframe.scrolling = "no";
            $(iframe).css("border","none");
            //$(iframe).css("overflow","hidden");


            $(iframe).load(function () {
                // console.log(this);
                this.parentNode.style.display = "block";
            });
            if(type == 1){

                iframe.src = baseurl + "AD/goImgAD.htm?width="+width +"&height=" + height + "&ADid="+advId;


            }else if(type == 2){

                iframe.src = baseurl + "AD/goAdviserAD.htm?width="+width +"&height=" + height + "&ADid="+advId;

            }else if(type == 3){

                iframe.src = baseurl + "AD/goGoodsAD.htm?width="+width +"&height=" + height + "&ADid="+advId;

            }
            adv_box.empty().append(iframe);
        }

    }
})
