<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--공통 헤더-->
<header>
  <div class="col-12 col-center mw-1230 pl30 pr30 header_con" id="originHeader">
    <div class="col-0 left_box">
      <a href="${root}/" class="col-0 logo_con">
        <img src="${resourceThemeImg}/logo.png" alt="로고" />
      </a>
      <ul class="col-0 mt16 my_page_con">
      	<c:choose>
      		<c:when test="${userInfo != null}">
      			<li>
		          <a href="${root}/mypage">마이페이지</a>
		        </li>
		        <li>
		          <a href="javascript:void(0)" class="logoutBtn">로그아웃</a>
		        </li>
      		</c:when>
      		<c:otherwise>
      			<li>
		          <a href="javascript:void(0)" onclick="login.openLoginPopup()">로그인</a>
		        </li>
		        <li>
		          <a href="javascript:void(0)" onclick="signUp.openSignUpPopup()">회원가입</a>
		        </li>
      		</c:otherwise>
      	</c:choose>
      </ul>
    </div>
    <div class="col-0 right_box">
      <nav class="col-12">
        <ul class="col-12 gnb_con">
          <li>
            <a href="${root}/attraction/localList">여행지 검색</a>
          </li>
          <li>
            <a href="${root}/notice/list?pageNo=1">공지사항</a>
          </li>
        </ul>
      </nav>
      <div class="col-0 hamburger_con" onclick="gnb.openClickGnb()">
        <span></span>
      </div>
    </div>
  </div>
  <div class="col-12 click_header_wrap" id="clickHeader">
    <div class="col-12 col-center mw-930 pl30 pr30 click_header_con">
      <div class="col-12 mb30 top_con">
        <a href="${root}/" class="col-0 logo_con">
          <img src="${resourceThemeImg}/logo-white.png" alt="로고" />
        </a>
        <div class="col-0">
          <ul class="col-0 my_page_con mr20 mt7">
            <c:choose>
      		<c:when test="${userInfo != null}">
      			<li>
		          <a href="${root}/mypage">마이페이지</a>
		        </li>
		        <li>
		          <a href="javascript:void(0)" class="logoutBtn">로그아웃</a>
		        </li>
      		</c:when>
      		<c:otherwise>
      			<li>
		          <a href="javascript:void(0)" onclick="login.openLoginPopup()">로그인</a>
		        </li>
		        <li>
		          <a href="javascript:void(0)" onclick="signUp.openSignUpPopup()">회원가입</a>
		        </li>
      		</c:otherwise>
      	</c:choose>
          </ul>
          <a href="javascript:void(0)" class="col-0 popup_close_btn" onclick="gnb.closeClickGnb()">닫기 버튼</a>
        </div>
      </div>
      <div class="col-12 bottom_con">
        <nav class="col-12 gnb_con">
          <ul class="col-12">
            <li>
              <a href="${root}/attraction/localList">여행지 검색</a>
            </li>
            <li>
	            <a href="${root}/notice/list?pageNo=1">공지사항</a>
	          </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
  <div class="col-12 click_header_dim" id="clickHeaderDim"></div>
</header>

<div class="col-12 popup_style_0_wrap" id="loginPopup">
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

<div class="col-12 popup_style_0_wrap" id="signUpPopup">
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

<div class="col-12 popup_style_0_wrap" id="findPwPopup">
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
