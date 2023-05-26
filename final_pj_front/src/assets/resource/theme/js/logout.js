/*
 * 23/05/01 JM
 * 로그아웃 js 구현
 */
// const logout = {
// 	logoutBtns : null

// 	,init : function(){
// 		this.logoutBtns = document.querySelectorAll('.logoutBtn');
// 		this.registEvent();
// 	}

// 	,registEvent : function(){
// 		this.logoutBtns.forEach(btn => btn.addEventListener('click', () => {
// 			vanillaAjax("GET", getApiUrl() + "user/logout", {}, logout.logoutAction);
// 		}));
// 	}

// 	,logoutAction : function(data){
// 		if(data) window.location.reload();
// 		else alert('로그아웃에 실패했습니다. 잠시 후 다시 시도해주세요');
// 	}
// }

// windowOnload(function(){
// 	logout.init();
// })
