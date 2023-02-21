
//Go Back
function goBack() {
    window.history.back();
}

$(function(){
    // 전체페이지 function으로 처리
    pageAllScript();
});

function pageAllScript(){
    //nav
    $(".nav-link").on('click',function(){
        $(".nav .nav-link").removeClass("on");
    });
    $('.subnav-content').mouseover(function(){
        $(this).siblings('.nav-link').addClass("active");
    });
    $('.subnav-content').mouseleave(function(){
        $(this).siblings('.nav-link').removeClass("active");
    });

    //title
    $(".nav-link").on('click',function(){
        var title = $(this).data('title');
        $('.title').html(title);
    });


    // 현재 시간
    $(function () {
        if($('.section.today').length) setTime()
    });

    function setTime(){
        var d = new Date();
        //console.log(d.YYYYMMDDHHMMSS());
        d.YYYYMMDDHHMMSS();
        setInterval(function(){
            if($('.section.today .hhmm .dot').html().length) $('.section.today .hhmm .dot').html('');
            else $('.section.today .hhmm .dot').html(':');
            d.YYYYMMDDHHMMSS()
        },1000)
    }

    Date.prototype.YYYYMMDDHHMMSS = function () {
        var today = new Date();
        var yyyy = today.getFullYear().toString();
        var MM = pad(today.getMonth() + 1,2);
        var dd = pad(today.getDate(), 2);
        var mm = pad(today.getMinutes(), 2);
        var ss = pad(today.getSeconds(), 2);
        var hour = today.getHours();
        if (hour > 12) hour = hour - 12;
        if(hour==0) hour=12;
        var hh = pad(hour, 2);
        $('.section.today .yymmddweek .yymmdd').html(yyyy+'.'+MM+'.'+dd);
        $('.section.today .yymmddweek .week').html(["(일)","(월)","(화)","(수)","(목)","(금)","(토)"][(new Date()).getDay()]);
        $('.section.today .hhmm .hh').html(hh);
        $('.section.today .hhmm .mm').html(mm);
        $('.section.today .hhmm .ss').html(ss)
    };

    function pad(number, length) {
        var str = '' + number;
        while (str.length < length) {
            str = '0' + str;
        }
        return str;
    }

    //그래프 저장 레이어 팝업
    $(".save-graph").on("click", function(){
        if($(this).hasClass('active')){
            $(this).removeClass('active');
        }else{
            $(this).addClass('active');
        }
        $(".modal").addClass('open');

        $("#new-name").focus();
    });

    $('.modal .cancel').on('click',function(){
        $(this).closest('.modal').removeClass('open');
        $(".save-graph").removeClass('active');
    });

    //그래프모드/텍스트모드 버튼
    $('.btn.graph-mode').on('click',function(){
        $(this).css('display','none');
        $(".btn.text-mode").css('display','block');
    });
    $('.btn.text-mode').on('click',function(){
        $(this).css('display','none');
        $(".btn.graph-mode").css('display','block');
    });


    //Tab Content
    $(".tab-content").hide();
    $(".tab-content:first").show();
    var prevTab = 'tab1';
    $("ul.tabs li").click(function () {
        $("ul.tabs li").removeClass("active");
        $(this).addClass("active");
        $(".tab_content").hide();
        var activeTab = $(this).attr("rel");

        $("#" + prevTab).fadeOut();
        $("#" + activeTab).fadeIn();
        prevTab = activeTab;
    });

    //aside
    $(".aside .open-side").on("click", function(){
        $(this).toggleClass("close-side");
        $(this).parent().toggleClass("active");
    });

    $('input[type="checkbox"][name="check"]').click(function(){
        if ($(this).prop('checked')) {
            $('input[type="checkbox"][name="check"]').prop('checked', false);
            $(this).prop('checked', true);
        }
    });

    // 달력
    if ($('.calendarForm .datepicker').length != 0) {
        $('.calendarForm .datepicker').datepicker({
            firstDay : 0, //주의 시작일을 일요일로 하려면 0, 월요일은 1
            showOtherMonths: true, //이전날 다음달의 날자 미리보기
            //showMonthAfterYear: true, //년도와 월의 순서를 변경
            dayNamesMin: ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'], // 요일의 한글 형식.
            monthNames: ["Jan. ", "Feb. ", "Mar. ", "Apr. ", "May. ", "Jun. ", "Jul. ", "Aug. ", "Sep. ", "Oct. ", "Nov. ", "Dec. " ],
            dateFormat: "yy-mm-dd", //년, 월, 일 순서
        });
    }

    //All Check
    $('.check-items input[type=checkbox]').on('click',function(){
        var group = $(this).parents('.check-items');
        var input = group.find('input');
        var inputTotal = input.length;
        var inputCheckTotal = group.find('input:checked').length;
        if(inputTotal == inputCheckTotal){
            $(this).parents('.check-group').find('.all').prop('checked',true);
        }else if(inputCheckTotal==0){
            $(this).parents('.check-group').find('.all').prop('checked',false);
        }
        if(this.checked){
            $("label[for="+$(this).attr('id')+"]").addClass('active');
        }else{
            $("label[for="+$(this).attr('id')+"]").removeClass('active');
        }
    });
    $('.all').on('click',function(){
        if(this.checked){
            $(this).parents('.check-group').find('.check-items input').each(function(){
                this.checked = true;
                $("label[for="+$(this).attr('id')+"]").addClass('active');
            });
        }else{
            $(this).parents('.check-group').find('.check-items input').each(function(){
                this.checked = false;
                $("label[for="+$(this).attr('id')+"]").removeClass('active');
            });
        }
    });

    //aside search Accordion
    $('body').on("click", ".down-open", function(){
        if ($(this).hasClass('close')) {
            $(this).removeClass('close');
        }
        else {
            $(this).addClass('close');
        }
        $(this).parent().parent().children('.check-items').slideToggle(250);
    });

    //input file
    $('.file-load').change(function(){
        var label = $(this).parent().find('span');
        if(typeof(this.files) !='undefined'){ // fucking IE
            if(this.files.length == 0){
                label.removeClass('withFile').text(label.data('default'));
            }
            else{
                var file = this.files[0];
                var name = file.name;
                var size = (file.size / 1048576).toFixed(3); //size in mb
                label.addClass('withFile').text(name + ' (' + size + 'mb)');
            }
        }
        else{
            var name = this.value.split("\\");
            label.addClass('withFile').text(name[name.length-1]);
        }
        return false;
    });

    //Rule Editor
    $(".set-area .remove").click(function(){
        $(this).parent(".set-area").fadeOut(1000, function(){
            $(this).remove();
        });
    });
    $(".form-inline .btn-remove").click(function(){
        $(this).parent(".form-inline").fadeOut(1000, function(){
            $(this).css("display","none");
        });
    });
    $(".add-set").click(function(){
        $(".set-3").fadeIn(1000, function(){
            $(this).css("display","block");
        });
    });
    $(".btn-add").click(function(){
        $(this).parent(".form-inline").next(".form-2").css("display","flex");
    });

    //rule-accordian
    $("#accordian .r1 li > a").click(function() {
        if ($(this).parent("li").hasClass('active')) {
            $(this).parent("li").removeClass('active');
        }
        else {
            $(this).parent("li").addClass('active');
        }
        $(this).parent().children('.r2').slideToggle(250);
    });
    $(".has-sub").click(function() {
        if ($(this).hasClass('active')) {
            $(this).removeClass('active');
        }
        else {
            $(this).addClass('active');
        }
        $(this).parent("li").children('.r3').slideToggle(250);
    });

    //rule-accordian 선택항목 반전 2depth
    $('#accordian .r2 li > a').on('click',function(){
        $(this).siblings().removeClass('active');
        $(this).toggleClass('active');
    });

    //rule-accordian 선택항목 반전 3depth
    $('#accordian .r3 li > a').on('click',function(){
        $(this).siblings().removeClass('active');
        $(this).toggleClass('active');
    });

    //룰 이름 수정 레이어 팝업
    $("#btnModify").on("click", function(){
        if($(this).hasClass('active')){
            $(this).removeClass('active');
        }else{
            $(this).addClass('active');
        }
        $(".rule-modify").addClass('open');
    });

    //룰 이름 추가 레이어 팝업
    $("#btnAdd").on("click", function(){
        if($(this).hasClass('active')){
            $(this).removeClass('active');
        }else{
            $(this).addClass('active');
        }
        $(".rule-add").addClass('open');
    });

}//function

//aside 편성 검색
function filterFunction() {
    var input, filter, ul, li, label, txtValue;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    ul = document.getElementById("searchList");
    li = ul.getElementsByTagName("li");
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("label")[0];
        txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}

//aside 편성 검색 - Analysis > Model
function filterFunctionModel() {
    var input, filter, ul, li, label, txtValue;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    ul = document.getElementById("searchList");
    li = ul.getElementsByTagName("li");
    for (i = 0; i < li.length; i++) {
        a = li[i];
        //a = li[i].getElementsByTagName("label")[0];
        // console.log("dfdf:" + a.textContent);
        txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}

//Filter Table
$(function(){
    $('.form-control').keyup(function(){
        var val = $(this).val().toLowerCase();

        $('td').closest('tr').hide();
        $('td').closest('tr').each(function(){

            var text = $(this).text().toLowerCase();
            if(text.indexOf(val) !== -1){
                $(this).show();
            }
        });
    });
});




