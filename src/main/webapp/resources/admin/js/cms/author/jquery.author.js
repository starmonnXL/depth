$(function(){
	var $validate_authors = $(".data-author");
	if($validate_authors && hrightsCodes){
		$.each($validate_authors,function(){
			var code = $(this).attr("data-code");
			if($.inArray(code,hrightsCodes)<0){
				$(this).remove();
			}
		})
	}
});