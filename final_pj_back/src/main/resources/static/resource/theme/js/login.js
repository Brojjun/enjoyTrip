/*
 * 로그인 / 로그아웃 js 구현 
 */
let login = {
	loginSubmitBtn : null
	,idInput : null
	,pwInput : null
	
	,init : function(){
		this.loginSubmitBtn = document.getElementById('loginSubmitBtn');
		this.idInput = document.querySelector('#loginPopup #userId');
		this.pwInput = document.querySelector('#loginPopup #userPassword');
		
		this.registEvent();
	}
	
	,registEvent : function(){
		let _this = this;
		
		_this.loginSubmitBtn.addEventListener('click', () => {
			_this.loginSubmit();
		});
		
	}
	
	,openLoginPopup : function(){
		let _this = this;
		PopupManager.add('#loginPopup');
		
		//값 초기화
		_this.idInput.value = '';
		_this.pwInput.value = '';
	}
	
	,loginSubmit : function() {
		let _this = this;
		if(_this.idInput.value == ''){
			alert('아이디를 입력해주세요');
			return;
		}
		
		if(_this.pwInput.value == ''){
			alert('비밀번호를 입력해주세요');
			return;
		}
		
		let param = {id : _this.idInput.value, password : _this.pwInput.value};
		
		vanillaAjax("POST", getApiUrl() + "user/login", param, login.loginAction);
		
	}
	
	,loginAction : function(result){
		if(result.result){
			window.location.reload();
		} else {
			alert('로그인에 실패했습니다. 확인 후 다시 시도해주세요');
		}
	}
}

windowOnload(function(){
	login.init();
})
