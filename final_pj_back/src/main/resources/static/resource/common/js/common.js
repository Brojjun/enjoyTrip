//모바일인지 아닌지 단순 창 크기로 판단하는 함수
function checkMobileSize() {
  return window.innerWidth < 992;
}

function getUrl(){
	return window.origin + "/"; 
}

function getApiUrl(){
	return window.origin + "/api/"; 
}
