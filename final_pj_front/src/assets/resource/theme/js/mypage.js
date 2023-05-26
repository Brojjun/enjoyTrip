/*
 *	23/05/01 JM
 *  마이페이지 js 구현
 */

// const mypage = {
// 	updateBtn : null
// 	,idInput : null

// 	,init : function(userNo){
// 		this.updateBtn = document.getElementById("updateBtn");
// 		this.idInput = document.getElementById("updateId");

// 		//세팅
// 		vanillaAjax("GET", getApiUrl() + "myInfo/" + userNo, {}, mypage.settingInfo);

// 		this.registEvent();
// 	}
// 	,registEvent : function(){
// 		let _this = this;
// 		this.updateBtn.addEventListener("click", () => {
// 			let param = {id : _this.idInput.value,
// 					password : document.getElementById("updatePassword").value
// 					};
// 			vanillaAjax("PUT", getApiUrl() + "myInfo/" + document.getElementById("updateNo").value, param, mypage.completeUpdate);
// 		});
// 	}

// 	,settingInfo : function(data){
// 		let _this = mypage;
// 		document.getElementById("updateNo").value = data.no;
// 		_this.idInput.value = data.id;
// 		document.getElementById("updatePassword").value = data.password;
// 	}

// 	,completeUpdate : function(data){
// 		console.log(data);
// 	}
// }
