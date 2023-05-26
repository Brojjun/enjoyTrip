<template>
<div class="col-12 popup_style_0_wrap" ref="loginPopup">
  <div class="col-12 popup_con" style="max-width: 600px">
    <div class="col-12 popup_inner">
      <div class="col-12 popup_header_con">
        <div class="col-12 popup_header_top_con">
          <div class="left_box">
            <div class="title_con">로그인</div>
          </div>
          <div class="right_box">
            <div class="popup_close_con">
              <a class="popup_close_btn" href="javascript:void(0)" onclick="PopupManager.removePopLatest(true)">닫기버튼</a>
            </div>
          </div>
        </div>
      </div>
      <div class="col-12 pt50 pb30 pl30 pr30 popup_contents_con">
        <div class="col-12 mb30 form_style_0_con">
          <div class="col-12 form_style_0">
            <div class="col-0 label_box">
              <label for="userId">아이디</label>
            </div>
            <div class="col-0 input_box">
              <div class="col-12 input_style_0_con">
                <input type="text" id="userId" placeholder="아이디를 입력해주세요" />
              </div>
            </div>
          </div>
          <div class="col-12 form_style_0">
            <div class="col-0 label_box">
              <label for="userPassword">비밀번호</label>
            </div>
            <div class="col-0 input_box">
              <div class="col-12 input_style_0_con">
                <input type="password" id="userPassword" placeholder="비밀번호를 입력해주세요" />
              </div>
            </div>
          </div>
        </div>
        <div class="col-12 tc btn_style_0_con">
          <button type="button" class="btn_style_0" id="loginSubmitBtn">로그인</button>
          <button type="button" class="btn_style_0" onclick="findPw.openFindPwPopup()">비밀번호 찾기</button>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: 'LoginPopup',
};
</script>
