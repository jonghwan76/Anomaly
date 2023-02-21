$(document).ready(function(){
	resizeContent();	//컨텐츠 리사이징-2
});

$(window).resize(function() {
	resizeContent();
});

$(document).ready(function(){
	setTime();
});

function resizeContent(){
	if($(window).width() > 1151){
		$('#sub').css("width", "100%");
		$('#container').css("width", "");
		$('#quickMenu').show();
	}
	else{
		$('#sub').css("width", "1150");
		$('#container').css("width", "1140");
		$('#quickMenu').hide();
	}

	var tabW = $('#main .tabList').width();
	$('#main .tabcontent').css("width", tabW);
}


function setTime(){
	var d = new Date();
	d.YYYYMMDDHHMMSS()
	setInterval(function(){
		if($("#dot").text() == ":") $("#dot").html(" ")
		else $('#dot').text(':')
		d.YYYYMMDDHHMMSS()
	},1000)
}


Date.prototype.YYYYMMDDHHMMSS = function () {
	var today = new Date();
	var yyyy = today.getFullYear().toString();
	var MM = pad(today.getMonth() + 1,2);
	var dd = pad(today.getDate(), 2);
	var mm = pad(today.getMinutes(), 2)
	var ss = pad(today.getSeconds(), 2)
	var hour = today.getHours();
	// if (hour > 12) hour = hour - 12;
	// if(hour==0) hour=12;
	var hh = pad(hour, 2);
	$('#yyyymmdd').html(yyyy+'.'+MM+'.'+dd);
	$('#hh').html(hh);
	$('#mi').html(mm);
	$('#ss').html(ss);
};

function pad(number, length) {
	var str = '' + number;
	while (str.length < length) {
		str = '0' + str;
	}
	return str;
}

function setCookie(cookieName, value, exdays){
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
	document.cookie = cookieName + "=" + cookieValue;
}

function deleteCookie(cookieName){
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate() - 1);
	document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}

function getCookie(cookieName) {
	cookieName = cookieName + '=';
	var cookieData = document.cookie;
	var start = cookieData.indexOf(cookieName);
	var cookieValue = '';
	if(start != -1){
		start += cookieName.length;
		var end = cookieData.indexOf(';', start);
		if(end == -1)end = cookieData.length;
		cookieValue = cookieData.substring(start, end);
	}
	return unescape(cookieValue);
}

function fncLogout() {
	$.ajax({
		url: '/user/logoutAction'
		, type: 'post'
		, dataType: 'json'
		, async : true
		, data:{}
		, success: function (msg) {
			location.href = "/";
		}
		, error: function (request, status, error) {    //오류일경우 디폴트 값 표시
			location.href = "/";
		}
	});
}

/**
 * 타이머 함수 실행
 * @param seconds
 * @param callback
 * @returns {number}
 */
function startInterval(seconds, callback)
{
	callback();
	return setInterval(callback, seconds * 1000);
}

/* 현재일로부터 day만큼 증가한 날짜를 리턴
 * @author :
 * @return : value or ''
 */
function getCalDate(day){

	var caledmonth, caledday, caledYear, returnDay;
	var loadDt = new Date();
	var v = new Date(Date.parse(loadDt) + day*1000*60*60*24);

	caledYear = v.getFullYear();

	if( v.getMonth() < 9 ){
		caledmonth = '0'+(v.getMonth()+1);
	}else {
		caledmonth = v.getMonth()+1;
	}

	if( v.getDate() <= 9 ){
		caledday = '0'+v.getDate();
	}else{
		caledday = v.getDate();
	}

	returnDay = caledYear+"-"+caledmonth+"-"+caledday;

	return returnDay;

}

function fncShowLoading() {
	var width = 0;
	var height = 0;
	var left = 0;
	var top = 0;

	width = 50;
	height = 50;

	top = ( $(window).height() - height ) / 2 + $(window).scrollTop();
	left = ( $(window).width() - width ) / 2 + $(window).scrollLeft();

	if($("#div_ajax_load_image").length != 0) {
		$("#div_ajax_load_image").css({
			"top": top+"px",
			"left": left+"px"
		});
		$("#div_ajax_load_image").show();
	}
	else {
		$('body').append('<div id="div_ajax_load_image" style="position:absolute; top:' + top + 'px; left:' + left + 'px; width:' + width + 'px; height:' + height + 'px; z-index:9999; filter:alpha(opacity=50); opacity:alpha*0.5; margin:auto; padding:0; "><img src="/images/img.svg" style="width:50px; height:50px;"></div>');
	}
}

/**
 * 금일날짜 리턴
 * @returns {string}
 */
function getCurrDate() {
	var d = new Date();

	var s =
		leadingZeros(d.getFullYear(), 4) + '-' +
		leadingZeros(d.getMonth() + 1, 2) + '-' +
		leadingZeros(d.getDate(), 2);
	return s;
}

function leadingZeros(n, digits) {
	var zero = '';
	n = n.toString();

	if (n.length < digits) {
		for (i = 0; i < digits - n.length; i++)
			zero += '0';
	}
	return zero + n;
}

function fncHideLoading() {
	$("#div_ajax_load_image").hide();
}
