
/**
 * 拓展easyui 校验
 */
$.extend($.fn.validatebox.defaults.rules, {
   equals: {
       validator: function(value,param){
            return value == $(param[0]).val();
        },
       message: '两次输入的内容不一致！'
    },
    mPhone: {
       validator: function(value,param){
    	  
    	   return (/\d{11}$/.test(value));
        },
       message: '手机号码格式错误！'
   },
   alphanumeric: {
       validator: function(value,param){
    	  
    	   return (/^\w+$/i.test(value));
        },
       message: '只能输入字母、数字、下划线!'
   },
   lettersonly: {
       validator: function(value,param){
    	  
    	   return (/^[a-z]+$/i.test(value));
        },
       message: '只能输入字母!'
   },
   url: {
       validator: function(value,param){
    	  
    	   return (/^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value));
        },
       message: '请输入正确的URL地址!'
   },
   minlength: {
       validator: function(value,param){
    	  
    	   return (value.trim().length>=param[0]);
        },
       message: '长度不能小于{0}'
   },
   comboxValidate : {  
       validator : function(value, param,missingMessage) {  
           if($('#'+param).combobox('getValue')!='' && $('#'+param).combobox('getValue')!=null){  
               return true;  
           }  
           return false;  
       },  
       message : "{1}"  
   },
   telNum:{ //既验证手机号，又验证座机号
      validator: function(value, param){ 
          return /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\d{3})|(\d{3}\-))?(1[358]\d{9})$)/.test(value);
      },    
      message: '请输入正确的电话号码。' 
   }  
});
