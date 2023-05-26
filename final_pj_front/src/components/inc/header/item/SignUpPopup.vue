<template>
  <div class="col-12 popup_style_0_wrap" ref="signUpPopup">
  <div class="col-12 popup_con" style="max-width: 900px">
    <div class="col-12 popup_inner">
      <div class="col-12 popup_header_con">
        <div class="col-12 popup_header_top_con">
          <div class="left_box">
            <div class="title_con">회원가입</div>
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
              <label for="signUpId">아이디</label>
            </div>
            <div class="col-0 input_box has_btn">
              <div class="col-12 input_style_0_con">
                <input type="text" id="signUpId" placeholder="아이디를 입력해주세요" />
              </div>
              <div class="col-0 btn_style_0_con">
                	<a href="javascript:void(0)" class="btn_style_0 type_2" id="checkDuplicateId">중복확인</a>
                </div>
              <div class="col-12 warning_text"></div>
            </div>
          </div>
          <div class="col-12 form_style_0">
            <div class="col-0 label_box">
              <label for="signUpName">이름</label>
            </div>
            <div class="col-0 input_box">
              <div class="col-12 input_style_0_con">
                <input type="text" id="signUpName" placeholder="이름을 입력해주세요" />
              </div>
            </div>
          </div>
          <div class="col-12 form_style_0">
            <div class="col-0 label_box">
              <label for="signUpPassword">비밀번호</label>
            </div>
            <div class="col-0 input_box">
              <div class="col-12 input_style_0_con">
                <input type="password" id="signUpPassword" placeholder="비밀번호를 입력해주세요" />
              </div>
            </div>
          </div>
          <div class="col-12 form_style_0">
            <div class="col-0 label_box">
              <label for="signUpEmail">이메일</label>
            </div>
            <div class="col-0 input_box type_email">
            	<div class="col-12 input_style_0_con">
                	<input type="text" id="signUpEmail0" placeholder="이메일을 입력해주세요" />
              	</div>
              	<div class="col-0 ml7 mr7 mt7 addr_text">@</div>
              	<div class="col-12 input_style_0_con">
                	<input type="text" id="signUpEmail1" placeholder="도메인을 입력해주세요" />
              	</div>
              	<div class="col-12 select_style_0_con">
              		<select class="col-12 select_style_0" name="signUpEmail2">
              			<option value="naver.com">naver.com</option>
              			<option value="gmail.com">gmail.com</option>
              			<option value="daum.net">daum.net</option>
              			<option value="kakao.com">kakao.com</option>
              			<option value="" selected>직접 입력</option>
              		</select>
              	</div>
            </div>
          </div>
          <div class="col-12 form_style_0">
            <div class="col-0 label_box">
              <span>관심 지역</span>
            </div>
            <div class="col-0 input_box">
              <div class="col-12 input_style_0_con">
                
              </div>
            </div>
          </div>
          <div class="col-12 form_style_0">
            <div class="col-0 label_box">
              <span>생년월일</span>
            </div>
            <div class="col-0 input_box type_birth">
              <div class="col-12 select_style_0_con">
              		<select class="col-12 select_style_0" name="signUpBirthYear">
              			<option value="1991">1991</option>
              			<option value="1992">1992</option>
              			<option value="1993">1993</option>
              			<option value="1994">1994</option>
              			<option value="1995">1995</option>
              			<option value="1996">1996</option>
              			<option value="1997">1997</option>
              			<option value="1998">1998</option>
              			<option value="1999">1999</option>
              			<option value="2000">2000</option>
              			<option value="2001">2001</option>
              			<option value="2002">2002</option>
              		</select>
              	</div>
              	<div class="col-12 select_style_0_con">
              		<select class="col-12 select_style_0" name="signUpBirthMonth">
              			<option value="01">01</option>
              			<option value="02">02</option>
              			<option value="03">03</option>
              			<option value="04">04</option>
              			<option value="05">05</option>
              			<option value="06">06</option>
              			<option value="07">07</option>
              			<option value="08">08</option>
              			<option value="09">09</option>
              			<option value="10">10</option>
              			<option value="11">11</option>
              			<option value="12">12</option>
              		</select>
              	</div>
              	<div class="col-12 select_style_0_con">
              		<select class="col-12 select_style_0" name="signUpBirthDate">
              			<option value="01">01</option>
              			<option value="02">02</option>
              			<option value="03">03</option>
              			<option value="04">04</option>
              			<option value="05">05</option>
              			<option value="06">06</option>
              			<option value="07">07</option>
              			<option value="08">08</option>
              			<option value="09">09</option>
              			<option value="10">10</option>
              			<option value="11">11</option>
              			<option value="12">12</option>
              			<option value="13">13</option>
              			<option value="14">14</option>
              			<option value="15">15</option>
              			<option value="16">16</option>
              			<option value="17">17</option>
              			<option value="18">18</option>
              			<option value="19">19</option>
              			<option value="20">20</option>
              			<option value="21">21</option>
              			<option value="22">22</option>
              			<option value="23">23</option>
              			<option value="24">24</option>
              			<option value="25">25</option>
              			<option value="26">26</option>
              			<option value="27">27</option>
              			<option value="28">28</option>
              			<option value="29">29</option>
              			<option value="30">30</option>
              			<option value="31">31</option>
              		</select>
              	</div>
            </div>
          </div>
        </div>
        <div class="col-12 tc btn_style_0_con">
          <button type="button" class="btn_style_0" id="signUpSubmitBtn">회원가입</button>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: 'SignUpPopup',
};
</script>
