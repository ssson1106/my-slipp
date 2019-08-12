$(".answer-write input[type='submit']").click(addAnswer);



function addAnswer(e){
	e.preventDefault(); //디폴트 기능이 실행되지 않도록 막는기능 = return false; 와 같은듯
	
	var queryString = $(".answer-write").serialize();
	console.log('query : ' + queryString);
	
	var url = $('.answer-write').attr('action');
	console.log('url : ' + url);
	$.ajax({
		type : 'post',
		url : url,
		data : queryString,
		dataType : 'json',
		error : onError,
		success : onSuccess
	});
}

function onError(){
	alert("error");
}

String.prototype.format = function() {
	  var args = arguments;
	  return this.replace(/{(\d+)}/g, function(match, number) {
	    return typeof args[number] != 'undefined'
	        ? args[number]
	        : match
	        ;
	  });
	}

	
function onSuccess(data, status){
	console.log(data);
	var answerTemplate = $('#answerTemplate').html();
	var template = answerTemplate.format(
			//userid, 날짜, contents, questionsId, answersId  
			data.writer.userId, data.formattedCreateDate, data.contents,
			data.question.id, data.id);
	$(".answer-write").before(template);
	
	$(".form-control").val("");
}

$(document).on("click","a.link-delete-article",deleteAnswer);

function deleteAnswer(e) {
	e.preventDefault(); 
	var deleteBtn = $(this);
	var url = deleteBtn.attr("href");
	console.log("url : " + url);
	
	$.ajax({
		type : 'DELETE',
		url : url,
		dataType : 'json',
		/*error : function(){
			alert("error");
		},*/
		error:function(data){
		    alert(data.errorMessage);},
		success : function(data, status){
			alert("success");
			console.log(data);
			deleteBtn.closest("article").remove();
			
		}
	});
}