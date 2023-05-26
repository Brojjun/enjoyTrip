/**
 * vanilla js로 ajax 구현
 * author : JM
 * date :  23/03/28
 * update_date : 23/04/28
 * 주고받는 DATA는 JSON
 * fetch로 변환
 */

function vanillaAjax(method, url, body, callback){
	
	let config;
	//GET, DELETE 처리
	if(method == "GET" || method == "DELETE"){
		config = {
		          method: method,
		          headers: {
		            "Content-Type": "application/json",
		          },
		};
	} else { 	//POST, PUT 처리
		config = {
		          method: method,
		          headers: {
		            "Content-Type": "application/json",
		          },
		          body: JSON.stringify(body),
		};
	}
	fetch(url, config)
    .then((response) => response.json())
    .then((data) => callback(data));
}