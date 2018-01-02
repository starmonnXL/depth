/**
 * Created by BAI on 2017/3/28 0028.
 * Plug ui
 */
(function ($, D){
    D._mix(D, {
        /**
         *
         * @param src
         * @param options
         * @param callback
         */
        use: function (src, options, callback){
            var type = D.isFunction(options),
                opts,
                optsfun,
                hrefBuild;
            var defaults = {
                css: true
            };
            /**
             * 参数验证
             */
            optsfun = function (){
                if(type){
                    callback = options;
                    opts = defaults;
                } else {
                    opts = $.extend(true, {}, defaults, options);
                }
            };
            /**
             * 链接组装
             */
            hrefBuild = function (){
                var RELEASE = D.config('release'), DGG_URI, URI_ARR = [];
                DGG_URI = D.sites.static + src;
                if(RELEASE){
                    if(DGG_URI.indexOf('src/') > -1 && !(DGG_URI.indexOf('build/') > -1)){
                        DGG_URI = DGG_URI.replace('src/', 'build/') + '.min';
                    } else {
                        DGG_URI = DGG_URI + '.min';
                    }
                } else {
                    if(DGG_URI.indexOf('build/') > -1){
                        DGG_URI = DGG_URI + '.min';
                    }
                }
                URI_ARR = [DGG_URI + '.js', DGG_URI + '.css'];
                D.loadScript(URI_ARR[0], {
                    success: function (){
                        callback && D.isFunction(callback) && callback.call(D);
                        if(!opts.css){
                            return false;
                        }
                        D.loadScript(URI_ARR[1]);
                    },
                    charset: 'utf-8'
                });
            };
            optsfun();
            hrefBuild();
        },
        /**
         *
         * @param attr
         * @param loadFun
         * @param callback
         */
        attrJudge: function (attr, loadFun, callback){
            if(typeof window[attr] !== "undefined"){
                loadFun();
            }
            callback && callback();
        },
        /**
         *
         * @param callback
         * @returns {*}
         */
        lazyload: function (callback){
            D.loadScript(D.sites.static + "build/plugs/jquery.lazyload.1.7.2/jquery.lazyload.min.js", {
                success: function (){
                    $('[data-src]').lazyload({
                        data_attribute: 'src',
                        effect: "fadeIn",
                        load: function (elements_left, settings){
                            var $t = $(this);
                            $t.removeAttr('data-src');
                            callback && callback.call($t, elements_left, settings);
                        }
                    });
                },
                charset: 'utf-8'
            });
            return D;
        },
        /**
         *
         * @param href
         * @param callback
         */
        lazyloads: function (href, callback){
            // 图片预加载
            D.use(href, {css: false}, function (){
                $('[data-src]').lazyload({
                    data_attribute: 'src',
                    effect: "show", //show, fadeIn, slideDown
                    load: function (elements_left, settings){
                        var $t = $(this);
                        $t.removeAttr('data-src');
                        callback && callback.call($t, elements_left, settings);
                    }
                });
            });
        },
        /**
         *
         * @param id
         * @param options
         * @returns {*}
         */
        tabs: function (id, options){
            var
                $id = $(id),
                defaultVal = {
                    btnClass: '.dgg-js-tab-nav',
                    conClass: '.dgg-js-tab-con',
                    bind: 'hover',
                    animation: '0',
                    speed: 300,
                    delay: 200,
                    auto: false,
                    autoSpeed: 3000
                };
            $id.each(function (){
                //全局变量`
                var $t = $(this),
                    opt = $.extend({}, defaultVal, options),
                    evt = opt.bind,
                    btn = $t.find(opt.btnClass),
                    con = $t.find(opt.conClass),
                    anim = opt.animation,
                    conWidth = con.width(),
                    conHeight = con.height(),
                    len = con.children().length,
                    sw = len * conWidth,
                    sh = len * conHeight,
                    i = 0,
                    t, timer;
                //判断动画方向
                function judgeAnim(){
                    var w = i * conWidth,
                        h = i * conHeight;
                    btn.children().removeClass('current').eq(i).addClass('current');
                    switch (anim) {
                        case '0':
                            con.children().hide().eq(i).show();
                            break;
                        case 'left':
                            con.css({position: 'absolute', width: sw}).children().css({
                                float: 'left', display: 'block'
                            }).end().stop().animate({left: -w}, opt.speed);
                            break;
                        case 'up':
                            con.css({
                                position: 'absolute', height: sh
                            }).children().css({display: 'block'}).end().stop().animate({top: -h}, opt.speed);
                            break;
                        case 'fadein':
                            con.children().hide().eq(i).fadeIn();
                            break;
                    }
                }

                if(evt == "hover"){
                    btn.children().hover(function (){
                        var j = $(this).index();

                        function s(){
                            i = j;
                            judgeAnim();
                        }

                        timer = setTimeout(s, opt.delay);
                    }, function (){
                        clearTimeout(timer);
                    })
                } else {
                    btn.children().bind(evt, function (){
                        i = $(this).index();
                        judgeAnim();
                    })
                }
                function startRun(){
                    t = setInterval(function (){
                        i++;
                        if(i >= len){
                            switch (anim) {
                                case 'left':
                                    con.stop().css({left: conWidth});
                                    break;
                                case 'up':
                                    con.stop().css({top: conHeight});
                            }
                            i = 0;
                        }
                        judgeAnim();
                    }, opt.autoSpeed)
                }

                //如果自动运行开启，调用自动运行函数
                if(opt.auto){
                    $(this).hover(function (){
                        clearInterval(t);
                    }, function (){
                        startRun();
                    });
                    startRun();
                }
            });
            return D;
        },
        /**
         *
         * @param id
         * @param options
         * @param callback
         * @returns {*}
         */
        /**
         * !fun-goTop
         */
        goTop: (function (){
            var globalConfig = D.config('global');
            if(!globalConfig || !globalConfig.goTop){
                return false;
            }
            var $window = $(window),
                num_win = $window.height(),
                baseUri = D.sites.static + 'build/images/img/',
                bindhtml,
                notSupport = '',
                /**
                 * 0 全部 1咨询 2订单查询 3 返回顶部
                 * @type {number}
                 */
                type = globalConfig.goTop.type;
            notSupport += '<div class="dgg-fix-box"><ul class="fix-list">';

            if(type == 0 || type == 1){
                notSupport += '<li class="item-hidden" id="dgg-kefu" ><a href="javascript:void(0)"><i class="fix-icon icon1"></i><p class="kefu_button">咨询</p></a></li>';//<iframe id="iframe-kefu" src="' + urlRoot + 'aboutUs/kefu.htm"  frameborder="0"></iframe>
            }
            if(type == 0 || type == 2){
                notSupport += '<li class="scan"><a href="javascript:void(0)"><i class="fix-icon icon2"></i><p>查订单</p></a><div class="scan-box"><img src="' + baseUri + 'code3.jpg"/><p>扫一扫查订单</p><p>实时掌握订单进度</p></div></li>';
            }
            if(type == 0 || type == 1 || type == 2 || type == 3){
                notSupport += '<li class="cancle-top"><a href="javascript:void(0)"><i class="fix-icon icon3"></i><p>顶部</p></a></li>';
            }
            notSupport += '</ul></div>';
            bindhtml = function (callback){
                document.write(notSupport);
                callback && callback.call(null);
            };
            bindhtml(function (){
                /*返回顶部*/
                var $gotop = $('.dgg-fix-box .cancle-top');
                $(window).on('scroll.gotop', function (){
                    var num_top = document.body.scrollTop || document.documentElement.scrollTop;
                    if(num_top > num_win){
                        $gotop.addClass('dgg-gotop-show');
                    }else {
                        $gotop.removeClass('dgg-gotop-show');
                    }
                });
                $gotop.bind("click", function (){
                    $('body,html').animate({scrollTop: 0}, "500");
                }).hover(function (){
                    $(this).toggleClass("dgg-gotop-hover");
                });
                /*咨询对话框*/
                $('#dgg-kefu').click(function (){
                    D.kefu.open();
                });

            });
        })(),
        /**
         * kefu
         */
        kefu:{
            //调用对话
            open: function (){
                //var ifr = document.getElementById('iframe-kefu'), btn = ifr.contentWindow.document.getElementById("kefuButton");
                // btn.click("click");
                DGGkefu.open();
            }
        },
        navSlide: function (id, options, callback){
            var
                $id = $(id),
                defaults = {
                    defaultOne: true,
                    defaultTwo: false,
                    bind: 'hover',
                    on: 'on'
                };
            if(!$id.length){
                return this;
            }
            var type = D.isFunction(options);
            if(type){
                callback = options;
                options = {};
            }
            var opts = $.extend(true, {}, defaults, options),
                evt = opts.bind,
                bindfunc;
            bindfunc = function ($t){
                $t.addClass(opts.on).siblings().removeClass(opts.on);
                callback && callback.call(null, $t);
            };
            $id.each(function (){
                var $t = $(this);
                if(evt == 'mouseover'){
                    $t.bind('mouseover', function (){
                        bindfunc($(this));
                        return false;
                    });
                } else {
                    $t.bind('click', function (){
                        bindfunc($(this));
                        return false;
                    });
                }
            });
            return D;
        },
        /**
         *
         * @param $btn
         * @param enabled
         * @param word
         * @returns {boolean}
         */
        enableBtn: function ($btn, enabled, word){
            if(!$btn){
                return false;
            }
            var DISABLED = 'disabled',
                DATA_NAME = 'cache_html';
            if(D.isUndefined(enabled)){
                enabled = $btn.hasClass(DISABLED);
            } else {
                if(enabled == !$btn.hasClass(DISABLED)){
                    return false;
                }
            }
            if(enabled){
                $btn.removeClass(DISABLED).removeAttr(DISABLED);
                word = $btn.data(DATA_NAME);
                word && $btn.html(word);
            } else {
                $btn.addClass(DISABLED).attr(DISABLED, DISABLED);
                if(word){
                    $btn.data(DATA_NAME, $btn.html());
                    $btn.html(word);
                }
            }
        },
        /**
         *
         * @param str
         * @param num
         * @param isEllipsis
         * @returns {*}
         */
        stringhidden: function (str, num, isEllipsis){
            var r = /[^\x00-\xff]/g;
            if(str.replace(r, "mm").length <= num){
                return str
            }
            var m = Math.floor(num / 2);
            for (var i = m; i < str.length; i++) {
                if(str.substr(0, i).replace(r, "mm").length >= num){
                    return str.substr(0, i) + (isEllipsis || '......');
                }
            }
            return str;

        },
        /**
         *
         * @param option
         */
        showNothing: function (option){
            var opt = $.extend({
                word: '没有数据',
                icon: 'dgg-icon-nodata-100x90',
                skin: '',
                css: null
            }, option || {});
            var $html = $(D.format('<div class="dgg-box-nodata {skin}"><div class="dgg-box-nodata-text"> {word}</div></div>', opt));
            if(opt.icon){
                $html.find('.dgg-box-nodata-text').prepend(D.format('<i class="dgg-box-nodata-icon {0}"></i>', opt.icon));
            }
            if(option && opt.css){
                $html.css(opt.css);
            }
            return $html.get(0).outerHTML;
        },
        uiViewCel: function (callback){
            var dh = $(document).height(),
                ch = $('.dgg-head').height() + $('.dgg-footer').height(),
                mh = $('.dgg-main'),
                gmh = (dh - ch);
            gmh < 260 && (gmh = 260);
            mh.css('height', gmh);
            window.onresize = function (){
                mh.css('height', gmh);
            };
            callback && callback.call(this, mh);
        },
        radioChecked:function (){
            $('.dgg-form-group').on('click', '.dgg-radio-group', function() {
                var $t = $(this);
                if($t.find("input[type='radio']").is(":checked")){
                    $t.parents('.dgg-form-group').find('.dgg-radio-group').removeClass("checked")
                    $t.addClass("checked");
                } else {
                    $t.removeClass("checked");
                }
            });
        },
        loading: {
            start: function (obj,html){
                obj.prepend(html||'<div class="dgg-loading"></div>');
            },
            done: function (obj){
                obj.find('.dgg-loading').remove();
            }
        }
});
    D.attrJudge('layer', function (){
        /**
         * 对话框扩展
         * @options  {icon:{-1:'默认没有图标',0:'感叹号',1：'勾正确',2:'叉错误',3:'问号'，4：'锁符号',5：'红哭脸',6：'绿笑脸',N>6:'感叹号'}}
         */
        D._mix(D, {
            layer: layer,
            /**
             * @param msg
             * @param options
             * @param yes
             * @returns {*}
             */
            alert: function (msg, options, yes){
                var type = D.isFunction(options);
                if(type){
                    yes = options;
                }
                return D.layer.open($.extend({
                    content: msg,
                    shadeClose: true,
                    yes: function (index){
                        yes && D.isFunction(yes) && yes.call(this);
                        D.layer.close(index);
                    }
                }, type ? {} : options));
            },
            /**
             *
             * @param msg
             * @param options
             * @param callback
             */
            msg: function (msg, options, callback){
                var type = D.isFunction(options);
                if(type){
                    callback = options;
                }
                var opt = $.extend({}, {time: 2000}, options || {});
                D.layer.msg(msg, opt, function (){
                    callback && D.isFunction(callback) && callback.call(this);
                });
            },
            /**
             *
             * @param msg
             * @param options
             * @param ok
             * @param cancel
             * @returns {*}
             */
            confirm: function (msg, options, ok, cancel){
                var type = D.isFunction(options);
                if(type){
                    cancel = ok;
                    ok = options;
                }
                return D.layer.open($.extend({
                    content: msg,
                    btn: ['&#x786E;&#x5B9A;', '&#x53D6;&#x6D88;'],
                    btn2: cancel,
                    yes: function (index){
                        ok && D.isFunction(ok) && ok.call(this);
                        D.layer.close(index);
                    }
                }, type ? {} : options));
            },
            /**
             *
             * @param content
             * @param obj
             * @param options tips: [1, '#c00'] 支持上右下左四个方向，通过1-4进行方向设定
             * @returns {*}
             */
            tips: function (content, obj, options){
                return D.layer.open($.extend({
                    type: 4,
                    content: [content, obj],
                    closeBtn: false,
                    time: 2000,
                    shade: false,
                    resize: false,
                    fixed: false,
                    maxWidth: 210
                }, options));
            }
        });
    });
    D.attrJudge('laypage', function (){
        D._mix(D, {
            laypage: laypage,
            /**
             * laypage分页第2次封装
             * @param options
             * @param callback 触发分页后的回调
             * @returns {*}
             */
            Page: function (options, callback){
                var type = D.isFunction(options);
                if(type){
                    callback = options;
                }
                return D.laypage($.extend({
                    skip: true,
                    cont: 'dgg-pages',
                    pages: Math.ceil(options.total / (options.number || 5)),
                    curr: 1,
                    skin: '#1786ef',
                    jump: function (obj, first){
                        if(!first){
                            callback && D.isFunction(callback) && callback.call(this, obj);
                        }
                    }
                }, type ? {} : options));
            }
        });
    }, function (){
        // todo   not laypage
    });
})(jQuery, DGG);


