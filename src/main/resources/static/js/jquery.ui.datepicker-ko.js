
jQuery(function($){
	$.datepicker.regional['ko'] = {
		closeText: '닫기'
		, prevText: '이전달'
		, nextText: '다음달'
		, currentText: '오늘'
		, monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
		, monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
		, dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일']
		, dayNamesShort: ['일','월','화','수','목','금','토']
		, dayNamesMin: ['일','월','화','수','목','금','토']
		, weekHeader: 'Wk'
		, dateFormat: 'yy-mm-dd'
		, firstDay: 0
		, isRTL: false
		, showMonthAfterYear: true
		, yearSuffix: ''
		//, showOn: 'button' 											// InputBox 우측에 Icon 출력 // both / focus / button
        //, buttonImage: '../images/sub/ico_cal.png'	// 달력 Icon Path
        , buttonImageOnly: false 									// 달력 Icon 표시
        , changeMonth: true 										// 월 선택 SelectBox 생성
        , changeYear: true  										// 년 선택 SelectBox 생성
        , showButtonPanel: true 									// Today, Done을 하단에 추가
        , maxDate: '+0d' 
        , showAnim: "slide"
	};
	$.datepicker.setDefaults($.datepicker.regional['ko']);

	$.datepicker._gotoToday = function(id) {
	    var target = $(id);
	    var inst = this._getInst(target[0]);
	    if (this._get(inst, 'gotoCurrent') && inst.currentDay) {
	            inst.selectedDay = inst.currentDay;
	            inst.drawMonth = inst.selectedMonth = inst.currentMonth;
	            inst.drawYear = inst.selectedYear = inst.currentYear;
	    }
	    else {
	            var date = new Date();
	            inst.selectedDay = date.getDate();
	            inst.drawMonth = inst.selectedMonth = date.getMonth();
	            inst.drawYear = inst.selectedYear = date.getFullYear();
	            // the below two lines are new
	            this._setDateDatepicker(target, date);
	            this._selectDate(id, this._getDateDatepicker(target));
	    }
	    this._notifyChange(inst);
	    this._adjustDate(target);
	};
});