/*
Date : 22/03/17
Author : JM 최초작성
content : window load에 이벤트 등록을 하는 함수
*/
function windowOnload(callBack) {
  window.addEventListener('load', () => {
    callBack();
  });
}
