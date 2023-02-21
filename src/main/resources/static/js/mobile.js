
var glbPageList = new Array();
var glbDeviceToken;

$(document).ready(function(){

	// 디바이스 토큰 호출
	try{
		CallApp.GetDeviceToken();
	}catch(e){}

	var page = sessionStorage.getItem("pageList");

	if(page != null && page != ""){

		glbPageList = page.split(",");

		if(glbPageList[glbPageList.length-1] != window.location.href){
			glbPageList.push(window.location.href);
		}

	}else{
		glbPageList.push(window.location.href);
	}

	sessionStorage.setItem("pageList", glbPageList);

	$('.btn_lnb').click(function() {
		var h = $(window).height();
		$('.wrapper').animate(  { left : -260 },  500);
		$( 'body, html' ).css("overflow",'hidden');
		//$( '.mask' ).show();
		$( '.lnb' ).show();
		$( '.lnb_close' ).show();
		$( '.btn_lnb' ).hide();
	});
	$('.mask, .lnb_close button').click(function() {
		$('.wrapper').animate(  { left : 0 },  500);
		$( 'body, html' ).css('overflow', '' );
		//$( '.mask' ).hide();
		$( '.lnb' ).hide();
		$( '.lnb_close' ).hide();
		$( '.btn_lnb' ).show();

	 });

    $('header.top .btn_back').click(function(){
    	fnBack();
    });

});

// 뒤로가기(기기 이벤트 포함)
function fnBack(){

	// 종료
	if(glbPageList.length < 2){

		try{
			CallApp.AppQuit(); // 앱 함수
		}catch(e){}

	}else{

		glbPageList.splice(glbPageList.length-1, 1); // 마지막 페이지 제거

		sessionStorage.setItem("pageList", glbPageList);

		location.href = glbPageList[glbPageList.length-1]; // 이전 페이지 이동

	}

}

// 모바일 토큰 세팅(앱에서 호출 해 줌)
function setDeviceToken(token){
	glbDeviceToken = token;
}

function fnPopupOpen(e) {
	$("#divAlarmPopup, #divAlarmMask").show();
	$("body, html").css("overflow",'hidden');
}

function fnPopupClose(){
	$("#divAlarmPopup, #divAlarmMask").hide();
	$("body, html").css('overflow', '' );
}
