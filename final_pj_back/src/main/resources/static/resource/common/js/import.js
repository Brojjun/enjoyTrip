/*
Date : 22/03/17
Author : JM 최초작성
content : html을 임포트하기 위한 함수
*/
let allElements = document.getElementsByTagName('*');
Array.prototype.forEach.call(allElements, (el) => {
  let includePath = el.dataset.includePath;
  if (includePath) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        el.outerHTML = this.responseText;
      }
    };
    xhr.open('GET', includePath, true);
    xhr.send();
  }
});
