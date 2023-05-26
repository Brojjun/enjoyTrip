/*
 * Author : JM
 * Date : 23/03/30
 * Content : 비밀번호찾기 js 구현
 */
// let findPw = {
// 	findPwSubmitBtn : null
// 	,idInput : null
// 	,emailInput : null

// 	,init : function(){
// 		this.findPwSubmitBtn = document.getElementById('findPwSubmitBtn');
// 		this.idInput = document.querySelector('#findPwPopup #findPwId');
// 		this.emailInput = document.querySelector('#findPwPopup #findPwEmail');

// 		this.registEvent();
// 	}

// 	,registEvent : function(){
// 		let _this = this;

// 		_this.findPwSubmitBtn.addEventListener('click', () => {
// 			_this.findPwSubmit();
// 		});

// 	}

// 	,openFindPwPopup : function(){
// 		let _this = this;
// 		PopupManager.add('#findPwPopup');

// 		//값 초기화
// 		_this.idInput.value = '';
// 		_this.emailInput.value = '';
// 	}

// 	,findPwSubmit : function() {
// 		let _this = this;
// 		if(_this.idInput.value == ''){
// 			alert('아이디를 입력해주세요');
// 			return;
// 		}

// 		if(_this.emailInput.value == ''){
// 			alert('이메일을 입력해주세요');
// 			return;
// 		}

// 		let param = JSON.stringify({id : _this.idInput.value, email : _this.emailInput.value});

// 		vanillaAjax("POST", getUrl() + "/user", param, findPw.findPwAction);

// 	}

// 	,findPwAction : function(result){
// 		if(result.result){
// 			alert(result.password + "입니다");
// 		} else {
// 			alert('비밀번호 확인에 실패했습니다. 확인 후 다시 시도해주세요');
// 		}
// 	}
// }

// windowOnload(function(){
// 	findPw.init();
// })
