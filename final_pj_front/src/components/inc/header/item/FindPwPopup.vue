<template>
<div class="col-12 popup_style_0_wrap" ref="findPwPopup">
  <div class="col-12 popup_con" style="max-width: 900px">
    <div class="col-12 popup_inner">
      <div class="col-12 popup_header_con">
        <div class="col-12 popup_header_top_con">
          <div class="left_box">
            <div class="title_con">비밀번호 찾기</div>
          </div>
          <div class="right_box">
            <div class="popup_close_con">
              <a
                class="popup_close_btn"
                href="javascript:void(0)"
                onclick="PopupManager.removePopLatest(true)">닫기버튼</a>
            </div>
          </div>
        </div>
      </div>
      <div class="col-12 pt50 pb30 pl30 pr30 popup_contents_con">
        <div class="col-12 mb30 form_style_0_con">
          <div class="col-12 form_style_0">
            <div class="col-0 label_box">
              <label for="findPwId">아이디</label>
            </div>
            <div class="col-0 input_box">
              <div class="col-12 input_style_0_con">
                <input type="text" id="findPwId" placeholder="아이디를 입력해주세요" />
              </div>
            </div>
          </div>
          <div class="col-12 form_style_0">
            <div class="col-0 label_box">
              <label for="findPwEmail">이메일</label>
            </div>
            <div class="col-0 input_box">
              <div class="col-12 input_style_0_con">
                <input type="email" id="findPwEmail" placeholder="이메일을 입력해주세요" />
              </div>
            </div>
          </div>
        </div>
        <div class="col-12 tc btn_style_0_con">
          <button type="button" class="btn_style_0" id="findPwSubmitBtn">비밀번호 확인</button>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: 'FindPwPopup',
};
</script>
