var CONTEXT_NAME;function getContextName(){	// TODO cookie를 안쓸 경우엔 무조건 값이 "" 이 되어 버림.	if (CONTEXT_NAME == null){		CONTEXT_NAME = getCookie("j_context_name");	}	return CONTEXT_NAME;}var global = new JGlobal(); var messages = new JMessage();function JGlobal() {	this.pageNo = 1;	this.pageVNo = 1;	this.help;	this.query;	this.orderIndex;	this.orderTheadIndex;	this.orderMode;	this.orderColumn;}function JMessage() {	this.init;	this.messages;		this.get = function(id, args) {					if (!this.init) {						this.messages = new Array();						this.messages["JSM-1001"] = "저장하시겠습니까?";						this.messages["JSM-1002"] = "아이디";						this.messages["JSM-1003"] = "삭제하시겠습니까?";						this.messages["JSM-1004"] = "{0} 입력해야 합니다.";						this.messages["JSM-1005"] = "{0}의 크기는 {1}이여야 합니다";						this.messages["JSM-1006"] = "{0}의 크기는 {1}과 {2} 사이여야 합니다";						this.messages["JSM-1007"] = "{0}에는 다음(', ., \") 특수 문자는 입력할 수 없습니다";						this.messages["JSM-1008"] = "{0}에는 숫자만을 입력해야 합니다";						this.messages["JSM-1009"] = "{0}의 숫자는 {1}이여야 합니다";						this.messages["JSM-1010"] = "{0}의 숫자는 {1} {2} 사이여야 합니다";						this.messages["JSM-1011"] = "{0} 선택해야 합니다";						this.messages["JSM-1012"] = "선택할 수 있는 {0}의 수는 {1} 입니다";						this.messages["JSM-1013"] = "선택할 수 있는 {0}의 수는 {1} {2} 사이여야 합니다";						this.messages["JSM-1014"] = "{0}의 금액은 {1}이여야 합니다";						this.messages["JSM-1015"] = "{0}의 금액는 {1} {2} 사이여야 합니다";						this.messages["JSM-1016"] = "{0} 올바르게 입력하여 주십시오";						this.messages["JSM-1017"] = "입력하신 {0} 전자우편주소로 유효하지 않습니다. 다시 입력하여 주십시오!";						this.messages["JSM-1018"] = "입력하신 {0} 날짜로 유효하지 않습니다. 다시 입력하여 주십시오!";						this.messages["JSM-1019"] = "{0}의 날짜는 {1} 이전 이어야 합니다";						this.messages["JSM-1020"] = "{0}의 날짜는 {1} 이후 이어야 합니다";						this.messages["JSM-1021"] = "선택할 수 있는 {0}의 날짜는 {1} {2} 사이여야 합니다";						this.messages["JSM-1022"] = "올바른 파일명이 아닙니다. 파일을 다시 입력해 주시기 바랍니다";						this.messages["JSM-1023"] = "{0}에 대한 파일을 첨부해야 합니다";						this.messages["JSM-1024"] = "{0}에 대한 첨부 가능한 파일의 확장자는 {1} 입니다";						this.messages["JSM-1025"] = "{0}에 대하여 확장자가 {1}인 파일은 첨부가 금지되어 있습니다";						this.messages["JSM-1026"] = "입력하신 {0} Crontab 표현식으로 유효하지 않습니다. 다시 입력하여 주십시오!";						this.messages["JSM-1027"] = "{0}의  {1} 올바른 속성 표현식이 아닙니다";						this.messages["JSM-1028"] = "{0}의 속성  {1} 필요합니다";						this.messages["JSM-1029"] = "관련 답글도 함께 삭제됩니다. 삭제하시겠습니까?";						this.messages["JSM-1030"] = "이동하시겠습니까?";						this.messages["JSM-1031"] = "수정하시겠습니까?";						this.messages["JSM-1032"] = "회원가입하시겠습니까?";						this.init = true;					}					var message = this.messages[id];					if (!message) {						return id;					}					if (args) {						if (typeof args == "object" && args.length) {							for (var i = 0; i < args.length; i++) {								var pattern = new RegExp("\\{" + i + "\\}", "g"); 								message = message.replace(pattern, args[i]);							}						} else {							message = message.replace(/\{0\}/g, args);						}					}					return message;				};	this.alert = function(id, args) {					 alert(this.get(id, args));				 }}function JAdd() {	this.params;	this.param = jcontrol_param;		this.addParam = jcontrol_addParam;	this.run = function() {					var nextPage = jcontrol_getJspName() + "Form.jsp";					var query = jcontrol_getQueryString();					if (query != "") {						nextPage += "?" + query;					}					if (this.params) {						if (query != "") {							nextPage += "&" + this.params;						} else {							nextPage += "?" + this.params;						}					}					//window.location.href = encodeURI(nextPage);					window.location.href = nextPage;				};		this.write = function(){					var nextPage = jcontrol_getJspName() + "View.jsp";					var query = jcontrol_getQueryString();					if (query != "") {						nextPage += "?" + query;					}					if (this.params) {						if (query != "") {							nextPage += "&" + this.params;						} else {							nextPage += "?" + this.params;						}					}					//window.location.href = encodeURI(nextPage);					window.location.href = nextPage;	     		};}function JAjax() {	this.params;	this.addParam = jcontrol_addParam;	this.mode = function(mode) {					return this.addParam("mode", mode);				};						this.run = function(element, url, params) {							JLoadingMessage.show(element);					if (params) {						var callback = params.onComplete;						params.onComplete = function(originalRequest) {											JLoadingMessage.hide();											if (callback) {												callback(originalRequest);											}										};						if (this.params) {							params.parameters = this.params;						}					} else if (this.params) {						params = {parameters: this.params, onComplete: JLoadingMessage.hide};					}					new Ajax.Request(url, params);									};	this.update = function(element, url, target, params) {					if(this.params || !params || params.bLodingMessage == true){					  JLoadingMessage.show(element);					}					  if (params) {						  var callback = params.onComplete;						  params.onComplete = function(originalRequest) {												if(!this.params || !params || params.bLodingMessage == true){												  JLoadingMessage.hide();												}												  if (callback) {													  callback(originalRequest);												  }											  };						  if (this.params) {							  params.parameters = this.params;						  }					  } else if (this.params) {						  params = {parameters: this.params, onComplete: function(){							if(!this.params || this.params.bLodingMessage == true){							  JLoadingMessage.hide();							}						  }  }					  }					  new Ajax.Updater(target, url, params);				  };}function JBehind() {	this.formIndex = 0;	this.form = jcontrol_form;	this.run = function(mode) {					if (window.validate && !validate(mode)) {						return;					}					var form = document.forms[this.formIndex];					form.action = form.action + jcontrol_getQuery();					jcontrol_addMode(form, mode);					form.submit();				};}function JClose() {	this.run = function() {					window.close();				};}//TODO after 함수에 기본적으로 적용될 파라미터는?//TODO firefox가 modal 창을 IE처럼 지원하지 않아 편법을 썼음. 리팩토링의 여지가 있음. 이와 관련 참고 참고 : http://www.gtalbot.org/BugzillaSection/Bug195867GDR_WindowOpen.htmlvar jcontrol_tempDatePopupMethod = null;function JDatePopup() {	this.after;	this.popup = function(event, element, after) {					//var url = getContextName() + "/ep/board/datePopup.jsp";					var url = "/ep/board/datePopup.jsp";					var xfield = element.previousSibling;					/* IE */					if (window.showModalDialog) {						//2006.12.07  팝업 사이즈 크기 수정 (250, 230 => 211, 287 => 211, 307)						//var ws = jcontrol_formatModalWindow(Event.pointerX(event) + window.screenLeft - 105,Event.pointerY(event) + window.screenTop + 10,200, 255);												var Ver = msExplorer();						var pos_x = event.pageX || (event.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft));						var pos_y = event.pageY || (event.clientY + (document.documentElement.scrollTop || document.body.scrollTop));						if(Ver =="6.0"){							var ws = jcontrol_formatModalWindow(pos_x + window.screenLeft - 105,pos_y + window.screenTop + 10,310,395); //260,295						} else {							var ws = jcontrol_formatModalWindow(pos_x + window.screenLeft - 105,pos_y+ window.screenTop + 10,300,360); //250,260						}						var dt = showModalDialog(url, window, ws);						if (dt != null) {							if (dt.year == "") {								xfield.value = "";							} else {								xfield.value = dt.year + "-" + dt.month + "-" + dt.day;							}						}						if (after) after();					} else {						var ws = jcontrol_formatModalWindow(Event.pointerX(event) + window.screenX,													Event.pointerY(event) + window.screenY,													250, 350);//200,250						url += "?formName=" + xfield.form.getAttribute("name") + "&fieldName=" + xfield.name;						var win1 = window.open(url, "date", ws);						jcontrol_tempDatePopupMethod = after;					}				};}function addEvent(obj, evType, fn){try{ if (obj.addEventListener){	obj.addEventListener(evType, fn, false);	return true; } else if (obj.attachEvent){	var r = obj.attachEvent("on"+evType, fn);	return r; } else {	return false; } }catch(e){alert("catch");}}/**function JDelete() {	this.formIndex = 0;	this.idValue = "id";	this.form = jcontrol_form;	this.id = function(id) {				  this.idValue = id;				  return this;			  };	this.run = function() {					var form = document.forms[this.formIndex];					if (new JCheck(messages.get("JSM-1002"), form[this.idValue]).validate()							&& confirm(messages.get("JSM-1003"))) {						jcontrol_addMode(form, "delete");						form.action = form.action + jcontrol_getQuery();						form.submit();					}				};}**/function JDelete(modeMethod) {	this.formIndex = 0;	this.idValue = "id"; 	this.form = jcontrol_form;	/**	this.id = function(id) {				  this.idValue = id;				  return this;			  };	**/	this.run = function() {					var form = document.forms[this.formIndex];					//if (new JCheck(messages.get("JSM-1002"), form[this.idValue]).validate()					//		&& confirm(messages.get("JSM-1003"))) {						jcontrol_addMode(form, modeMethod);						form.action = form.action + jcontrol_getQuery();						form.submit();					//}				};}function JExcel() {	this.formIndex = 0;	this.form = jcontrol_form;	this.run = function(mode) {					if (!mode) {						mode = "excel";					}					var form = document.forms[this.formIndex];					var input = jcontrol_addMode(form, mode);					form.submit();					form.removeChild(input);				};}function JHelp() {	this.targetValue;	this.target = function(target) {					  this.targetValue = target;					  return this;				  };	this.run = function(id) {					var target = "help-message";					if (this.targetValue) {						target = this.targetValue;					}					if (!$(target)) {						alert(target + "를 아이디로 하는 태그가 없습니다.");						return;					}					if (global.help == null || global.help != id) {						global.help = id;						var onComplete = function() {											$(target).style.display = "block";										}						new Ajax.Updater(target,										getContextName() + "/HelpAjax.do?id=" + id,										{asynchronous:true, onComplete:onComplete});					} else {						if ($(target).style.display == "block") {							$(target).style.display = "none";							global.help =  null;							return;						}					}				};}function JLinkWeb() {	this.nextPage;	this.params;	this.next = function(page) {					this.nextPage = page;					return this;				};		this.param = jcontrol_param;		this.addParam = jcontrol_addParam;	this.run = function(id) {					if (!this.nextPage) {						this.nextPage = jcontrol_getJspName() + "Form.jsp";					}					var part = "";					if (arguments.length == 1) {						part += "p_id=" + id;					} else {						for (var i = 0; i < arguments.length; i++) {							part += "&p_id" + (i + 1) + "=" + arguments[i];						}					}					if (this.params) {						part += "&" + this.params;					}				/*	var query = jcontrol_getQueryString();					if (query != "") {						part += "&" + query;					}*/					//window.location.href = encodeURI(this.nextPage + "?" + part.replace(/^&/, ""));					window.location.href = this.nextPage + "?" + part.replace(/^&/, "");				};}function JLink() {	this.nextPage;	this.params;	this.next = function(page) {					this.nextPage = page;					return this;				};		this.param = jcontrol_param;		this.addParam = jcontrol_addParam;	this.run = function(id) {					if (!this.nextPage) {						this.nextPage = jcontrol_getJspName() + "Form.jsp";					}					var part = "";					if (arguments.length == 1) {						part += "p_id=" + id;					} else {						for (var i = 0; i < arguments.length; i++) {							part += "&p_id" + (i + 1) + "=" + arguments[i];						}					}					if (this.params) {						part += "&" + this.params;					}					var query = jcontrol_getQueryString();					if (query != "") {						part += "&" + query;					}					//window.location.href = encodeURI(this.nextPage + "?" + part.replace(/^&/, ""));					window.location.href = this.nextPage + "?" + part.replace(/^&/, "");				};}function JList() {	this.run = function() {					var nextPage = jcontrol_getJspName() + "List.jsp";					window.location.href = nextPage + jcontrol_getQuery();									};}function JLoad() {	this.formIndex = 0;	this.form = jcontrol_form;	this.run = function() {					var query = window.location.search;					query = decodeURI(query);					query = query.replace(/^\?(&)*/, "");					query = query.split("&");					var form = document.forms[this.formIndex];					for (var i = 0; i < query.length; i++) {						var index = query[i].indexOf("=");						var name = query[i].substring(0, index);						var value = query[i].substring(index + 1);						if (name.substring(0, 2) == "q_") {							if (form[name]) {								if (form[name].length){ // 배열로 전달되면									for (var j = 0; j < form[name].length; j++) {										if (form[name][j].value == value) {											if (form[name][0].nodeName.toLowerCase() == "option") {												form[name][j].selected = true;											} else {												form[name][j].checked = true;											}										}									}								} else { // 배열이 아닌 input이면									form[name].value = value;								}							} // if (form[name])						} else {							if (name == "orderIndex") {								global.orderIndex = value;							} else if (name == "orderTheadIndex") {								global.orderTheadIndex = value;							} else if (name == "orderBy") {								global.orderColumn = value;							} else if (name == "orderMode") {								global.orderMode = value;							} else if (name == "pageNo") {								global.pageNo = value;							}						}					}					jcontrol_sortToggle();				};}var JLoadingMessage = {	show: function(){		var mdd = $('mapDumpDiv');		if(mdd != null){			mdd.show();		}	},	hide: function(){		var mdd = $('mapDumpDiv');		if(mdd != null){			mdd.hide();		}	}};//var JLoadingMessage = {//	show : function(target) {//				var id = "loading-message";//				if (!$(id)) {//					var div = document.createElement('div');//					div.id = id;//					document.body.appendChild(div);//					Element.hide(id);//					new Insertion.Bottom(id, "<img src='/ext/image/common/ajax-loader.gif'/>");//					$(id).style.position = "absolute";//				}//				var dim = Element.getDimensions(id);//				var targetDim = Element.getDimensions(target);//				var offsetTop, offsetLeft;//				offsetTop = (targetDim.height - dim.height) / 2;//				offsetLeft = (targetDim.width - dim.width) / 2;////				Position.clone(target,id,//							  {setLeft: true,//								setTop: true,//								setWidth: false,//								setHeight: false,//								offsetTop: offsetTop,//								offsetLeft: offsetLeft//							  });//				Element.show(id);//			},////	hide: function() {//			  var id = "loading-message";//			  if ($(id)) {//				  Element.hide(id);//				  jcontrol_sortToggle();//			  }//		  }//};function JNavigation() {	this.params;	this.run = function(pageNo, params) {				//연계시스템관리 상세보기 (서비스 목록)				if (window.location.pathname == "/ep/es/SystemView.jsp") {					global.pageNo  = document.forms[0].pageNo.value;					global.pageVNo = pageNo;					if (params && params.pageVNo) {						document.forms[0].pageVNo.value = pageNo;					}											var nextPage = JNavigation.nextPage ? JNavigation.nextPage + "?" : jcontrol_getJspName() + "View.jsp?";					global.query = nextPage + jcontrol_getQueryVString();				} else {								global.pageNo = pageNo;					if (params && params.pageNo) {						document.forms[0][params.pageNo].value = pageNo;					}										//우편번호 팝업 네비게이션 링크 URL 주소 - 수정 (박학진 : 2007.01.24)					if(jcontrol_getJspName() == "/popup/ZipCode" || jcontrol_getJspName() == "/popup/Id") {						jcontrol_pageUrl = "Popup.jsp?";					} else {						jcontrol_pageUrl = "List.jsp?";					}					var nextPage = JNavigation.nextPage ? JNavigation.nextPage + "?" : jcontrol_getJspName() + jcontrol_pageUrl;					global.query = nextPage + jcontrol_getQueryString();				}				if (isAjax) {					var target = "list";					var mode = "&mode=ajaxList";					if (params) {						if (params.target) {							target = params.target;						}						if (params.param) {							global.query += ("&" + params.param);						}						if (params.mode) {							mode = "&mode=" + params.mode;						}					}					JLoadingMessage.show(target);					new Ajax.Updater(target, global.query + mode, {asynchronous:true, onComplete:JLoadingMessage.hide});				} else {					if (params) {						if (params.param) {							global.query += ("&" + params.param);						}					}						//window.location.href = encodeURI(global.query);					// 한글처리					window.location.href = global.query;				}	};}/**function JSave() {	this.formIndex = 0;	this.form = jcontrol_form;	this.run = function() {					if (window.validate && !validate("SAVE")) {							return;					}					if (confirm(messages.get("JSM-1001"))) {						var form = document.forms[this.formIndex];						form.action = form.action + jcontrol_getQuery();						var mode = "create";						if (window.location.search.indexOf("p_id") > 0 || 								form.getElementsByTagName("input")[0].type == "hidden") {							mode = "update";						}						jcontrol_addMode(form, mode);						form.submit();					}				};}**/function JSave(modeMethod) {	this.formIndex = 0;	this.form = jcontrol_form;	this.run = function() {				if (window.validate && !validate("SAVE")) {			return;		}		var mode = modeMethod;									if (confirm(messages.get("JSM-1001"))) {			var form = document.forms[this.formIndex];			form.action = form.action + jcontrol_getQuery();						// XSS 취약점 해결 - by 류성호 2010.07.05			//form.title.value = form.title.value.replace(/\</gi,"&lt;");			//form.cont.value = form.cont.value.replace(/\</gi,"&lt;");										jcontrol_addMode(form, mode);			form.submit();		}	};}function JSearch() {		this.run = function(params) {				new JNavigation().run(1, params);	};				}function JSort() {	this.run = function(columnName, element, params) {					var row = element.parentNode.parentNode;					var elements = row.getElementsByTagName("th");					for (var i = 0; i < elements.length; i++) {						if (element.parentNode == elements[i]) {							global.orderIndex = i;							break;						}					}					var thead = row.parentNode;					elements = document.getElementsByTagName("thead");					for (var i = 0; i < elements.length; i++) {						if (thead == elements[i]) {							global.orderTheadIndex = i;							break;						}					}					global.orderColumn = columnName;					if (global.orderMode) {						global.orderMode = (global.orderMode == "ASC") ? "DESC" : "ASC";					} else {						global.orderMode = "DESC";					}					if (!global.pageNo) {						global.pageNo = 1;					}					new JNavigation().run(global.pageNo, params);				};}function jcontrol_addMode(form, mode) {	if (!form.mode) {		var input = document.createElement("input");		input.name = "mode";		input.type = "hidden";		input.value = mode;		form.appendChild(input); // MS IE에서는 추가된 INPUT에 바로 접근할 수 없음.		return input;	} else {		form.mode.value = mode;		return form.mode;	}}function jcontrol_addParam(name, value) {	if (this.params) {		this.params += "&";	} else {		this.params = "";								}	this.params += (name + "=" + value);	return this;}function jcontrol_form(index) {	this.formIndex = index;	return this;}function jcontrol_formatModalWindow(left, top, width, height) {	if (window.showModalDialog) {		return "status:no; dialogLeft:" + left + "px; dialogTop:" + top + 				"px; dialogWidth:" + width + "px; dialogHeight:" + height + "px";	} else {		return "modal=yes, left=" + left + "px, top=" + top + ", width=" + width + "px, height=" + height + "px";	}}function jcontrol_getJspName() {	var path = window.location.pathname;	if (path.indexOf("Popup.jsp") > 0) {		return path.replace("Popup.jsp", "");	} else if (path.indexOf("List.jsp") > 0) {		return path.replace("List.jsp", "");	} else if (path.indexOf("ReForm.jsp") > 0) {	// 2006-12-07 추가 		return path.replace("ReForm.jsp", "");			} else if (path.indexOf("Form.jsp") > 0) {		return path.replace("Form.jsp", "");	} else if (path.indexOf("Detail.jsp") > 0) {		return path.replace("Detail.jsp", "");	} else if (path.indexOf("View.jsp") > 0) { // 2006-10-26 추가 		return path.replace("View.jsp", "");	} else if (path.indexOf("Poi.jsp") > 0) {		return path.replace("Poi.jsp", "");	}		return path;}function jcontrol_getQuery() {	var result = "";	var query = window.location.search;	if (!query || query.length == 0) {		return result;	}		query = query.replace(/^\?(&)*/, "");	query = query.split("&");	for (var i = 0; i < query.length; i++) {		if (query[i].substring(0, 2) != "p_") {			if (result != "") {				result += "&";			} else {				result += "?";			}			result += query[i];		}	}	return result;}function jcontrol_getQueryString() {	var result = "";	var queryTags = document.getElementsByTagName("input");			for (var i = 0; i < queryTags.length; i++) {				var name = queryTags[i].name;		var type = queryTags[i].type;		var checked = queryTags[i].checked;				if (name && name.substring(0, 2) == "q_") {			if (!((type == "checkbox" || type == "radio") && checked == false)){										if (queryTags[i].value != null && queryTags[i].value != "") {					result += "&" + name + "=" + queryTags[i].value;				}						}  		} else {			//20070510			if (!((type == "checkbox" || type == "radio") && checked == false)){										if (queryTags[i].value != null && queryTags[i].value != "") {					result += "&" + name + "=" + queryTags[i].value;				}						}  		}	}	queryTags = document.getElementsByTagName("select");	for (var i = 0; i < queryTags.length; i++) {		var name = queryTags[i].name;		if (name && name.substring(0, 2) == "q_") {			if (queryTags[i].value != null && queryTags[i].value != "") {				result += "&" + name + "=" + queryTags[i].value;			}		}	}		if (global.pageNo != 1) {		result += "&pageNo=" + global.pageNo;	}		if (global.orderColumn) {		result += "&orderBy=" + global.orderColumn +					  "&orderMode=" + global.orderMode +						  "&orderIndex=" + global.orderIndex +						  "&orderTheadIndex=" + global.orderTheadIndex;	}	return result != "" ? result.replace(/^&/, "") : result;}//View JNavigation (연계시스템 관리)function jcontrol_getQueryVString() {	var result = "";	var queryTags = document.getElementsByTagName("input");		if (window.location.pathname == "/ep/es/SystemView.jsp") {		for (var i = 0; i < queryTags.length; i++) {						var name = queryTags[i].name;			var type = queryTags[i].type;			var checked = queryTags[i].checked;			if (name && name.substring(0, 2) == "q_") {				if (!((type == "checkbox" || type == "radio") && checked == false)){											if (queryTags[i].value != null && queryTags[i].value != "") {						result += "&" + name + "=" + queryTags[i].value;					}							}  			} else if (name && name.substring(0, 5) == "sysId") {				if (!((type == "checkbox" || type == "radio") && checked == false)){											if (queryTags[i].value != null && queryTags[i].value != "") {						result += "&" + name + "=" + queryTags[i].value;					}							}  			}		}		queryTags = document.getElementsByTagName("select");		for (var i = 0; i < queryTags.length; i++) {			var name = queryTags[i].name;			if (name && name.substring(0, 2) == "q_") {				if (queryTags[i].value != null && queryTags[i].value != "") {					result += "&" + name + "=" + queryTags[i].value;				}			}		}	}	if (global.pageVNo != 1) {		result += "&pageVNo=" + global.pageVNo;	}		if (global.pageNo != 1) {		result += "&pageNo=" + global.pageNo;	}				if (global.orderColumn) {		result += "&orderBy=" + global.orderColumn +					  "&orderMode=" + global.orderMode +						  "&orderIndex=" + global.orderIndex +						  "&orderTheadIndex=" + global.orderTheadIndex;	}	return result != "" ? result.replace(/^&/, "") : result;}function jcontrol_param() {	for (var i = 0 ; i < arguments.length; i++) {		if (i == 0) {			if (!this.params) {				this.params = "";			}		}		this.params += "&p_value" + (i + 1) + "=" + arguments[i];	}	return this;}function jcontrol_sortToggle() {	if (global.orderColumn) {		var thead = document.getElementsByTagName("thead")[global.orderTheadIndex];		if (thead) {			var elements = thead.getElementsByTagName("th");			if (global.orderMode == "DESC") {				elements[global.orderIndex].className = "desc";			} else {				elements[global.orderIndex].className = "asc";			}		}	}}/**  * <swf/> 플래쉬 컨트롤 활성호 막기. * @param s: source url * @param d: flash * @param w: source width * @param h: source height * @param t: wmode * @param f: frequency * @param l: link list * @param k: flashvars key * @param v: flashvars value */function mf(s,d,w,h,t){	return activeflashOn(s,d,w,h,t,null,null);}function activeflashOn(s,d,w,h,t,f,l){	var keys = new Array();	var values = new Array();	if(f != null && f.length > 0){keys.push("nfreq");values.push(f);}	for(var i = 0; l != null && i < l.length; i++){		var clkid = i + 1;		keys.push(clkid);values.push(encode(l[i]));	}	return flashOn(s,d,w,h,t,keys,values);}function flashOn(s,d,w,h,t,k,v){	var flashvars = "";	if(k != null && v != null && k.length > 0 && k.length == v.length){		for(var i = 0; i < k.length; i++){			if(i > 0){ flashvars += "&"; }			flashvars += k[i] + "=" + v[i];		}	}	var code = "";	code  = "<object type=\"application/x-shockwave-flash\" ";	code +=		 "classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" ";	code +=		 "codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0\" ";	code +=		 "width=\""+w+"\" height=\""+h+"\" id=\""+d+"\">";	code += "<param name=\"movie\" value=\""+s+"\" />";	code += "<param name=\"quality\" value=\"high\" />";	//code += "<param name=\"bgcolor\" value=\"#ffffff\" />";	code += "<param name=\"wmode\" value=\""+t+"\" />";	code += "<param name=\"menu\" value=\"false\" />";	code += "<param name=\"allowScriptAccess\" value=\"always\" />";	code += "<param name=\"swliveconnect\" value=\"true\" />";	if(flashvars.length > 0){		code += "<param name=\"FlashVars\" value=\""+flashvars+"\" />";	}	code += "<embed src=\""+s+"\" quality=\"high\" "	//code +=		"bgcolor=\"#ffffff\" "	code +=		"wmode=\""+t+"\" "	code +=		"menu=\"false\" width=\""+w+"\" height=\""+h+"\" "	code +=		"type=\"application/x-shockwave-flash\" "	if(flashvars.length > 0){		code +=	"FlashVars=\""+flashvars+"\" ";	}	code +=		"pluginspage=\"http://www.macromedia.com/go/getflashplayer\"> "	code += "</embed>"	code += "</object>"	return code;}// write document contentsfunction documentwrite(src){ document.write(src); }// assign code innerHTMLfunction setcode(target, code){ target.innerHTML = code; }// 0 for impression, 1 for click.function icprocess(vals, idx, type){	for(var i = 0; i < vals.length; i++){		if(i == idx){			if(type == 0){ impress(vals[idx]); }			else{ click(vals[idx]); }			break;		}	}}// impression checkfunction checkimp(vals, idx){idx--;icprocess(vals, idx, 0); }// impression check processfunction impress(addr){ var i = new Image; i.src = addr; }//// util// browser check for adfunction isAdAvailable(){	var g,a,p,m,noepd; g=navigator; a=g.userAgent;	p=g.appVersion; m=p.indexOf("MSIE");	if(a.indexOf("MSIE")==-1){ noepd=true; }	if(m!=-1 && a.indexOf("Win")!=-1){		v=parseFloat(p.substring(m+4)); if(v<5.5){ noepd=true; }	}	return !noepd;}// url encodefunction encode(str){	var result = "";	for (i==0;i<str.length;i++) {		if(str.charAt(i) == " ") result +=  "+";		else result +=  str.charAt(i);		}		return escape(result);}// set cookiefunction setCookie(name,value,expires,domain) {document.cookie=name + "=" + escape(value) + ((expires == null)? "" : (" ; expires=" + expires.toGMTString())) + "; domain=" + (domain ? domain : "ad.naver.com") + ";" ;}// get cookiefunction getCookie(name){	var arg = name + "=";	var alen = arg.length;	var clen = document.cookie.length;	var i = 0;	while(i< clen){		var j = i + alen;		if(document.cookie.substring(i,j)==arg){			var end = document.cookie.indexOf(";",j);			if(end == -1)				end = document.cookie.length;			return unescape(document.cookie.substring(j,end));		}		i=document.cookie.indexOf(" ",i)+1;		if (i==0) break;	}	return null;}//  12/13추가function pageMoving2(pageNo){	var mForm = document.forms.mForm;	mForm.method = "post";	mForm.target = "_parent"; 	mForm.pageNo.value = pageNo;	mForm.submit();}function pageMoving(pageNo){	var currentLocation = location.href;	var Flag = currentLocation.indexOf('?');	var Flag1 = currentLocation.indexOf('#');	if(Flag == -1 && Flag1 == -1){		//alert(currentLocation);	}else if(Flag == -1 && Flag1 > 0){		currentLocation = currentLocation.substring(0,currentLocation.indexOf('#'));		//alert(currentLocation);	}else if(Flag > 0 && Flag1 == -1){		currentLocation = currentLocation.substring(0,currentLocation.indexOf('?'));		//alert(currentLocation);	}//	window.location.href=currentLocation+"?pageNo="+pageNo;		var mForm = document.forms.mForm;	mForm.action = currentLocation;	mForm.method = "post";	mForm.target = "_parent"; 	mForm.pageNo.value = pageNo;	mForm.submit();}function pageMovingList(pageNo,pageUrl){	var mForm = document.mForm;	mForm.method = "post";	mForm.target = "_parent"; 	mForm.pageNo.value = pageNo;	mForm.action = pageUrl;	mForm.submit();}function pageMovingList1(pageNo,pageUrl){	var mForm = document.forms.saveFrm;	mForm.action = pageUrl;	mForm.method = "post";	mForm.target = "_parent"; 	mForm.pageNo.value = pageNo;	mForm.submit();}function movingInsPage(pageNo,pageUrl){	var mForm = document.forms.mForm;	mForm.action = pageUrl;	mForm.method = "post";	mForm.target = "_parent"; 	mForm.pageNo.value = pageNo;	mForm.submit();}	function JSaveFree(modeMethod) {	this.run = function() {				if (window.validate && !validate(mode)) {			return;		}		var mode = modeMethod;									if (confirm(messages.get("JSM-1001"))) {			var form = document.saveFrm;			form.action = form.action + jcontrol_getQuery();						jcontrol_addMode(form, mode);			form.submit();		}	};}function JBehindFree() {	this.run = function(mode) {					if (window.validate && !validate(mode)) {						return;					}					var form = document.saveFrm;					form.action = form.action + jcontrol_getQuery();					jcontrol_addMode(form, mode);					form.submit();				};}function pageCommetMoving(pageNo){	var currentLocation = jQuery("#replyContView", parent.document).attr('src');	currentLocation = currentLocation.substring(0,currentLocation.indexOf('pageNo=')+7)+pageNo;	jQuery("#replyContView", parent.document).attr('src',currentLocation);}