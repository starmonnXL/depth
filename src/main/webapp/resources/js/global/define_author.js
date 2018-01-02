/*
*权限判断
* author：xtt
 */
;(function(){
	var author = function(ele,opt) {
		this.$element=ele,
		defaults = {
			authorbox:'#handle-bar',//按钮父级层
			authority:['del','add'],//权限
			attrval:'data-code',//操作权限
		},
		this.options=$.extend({}, defaults,opt);
	};
	author.prototype = {
		start:function() {
			var authorbox=$(this.$element),
				authority=this.options.authority,
				attrval=this.options.attrval;
			authorbox.each(function(){
				var $code=new Array();
				$(this).children().each(function(i){
					$code[i]=$(this).attr(attrval);
				});
				for(var j=0;j<$code.length;j++) {
					var ishas=authority.indexOf($code[j]);
					if(ishas>-1) {
						authorbox.children().show();
					}else{
						$(this).find("["+attrval+"="+$code[j]+"]").remove();
					}
				}
			});
			
			
		}
	};
	$.fn.defineAuthor = function(options) {
		var authors = new author(this,options);
		return authors.start();
	}
})();