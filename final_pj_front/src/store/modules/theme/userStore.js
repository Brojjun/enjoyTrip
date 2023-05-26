import JwtDecode from 'jwt-decode';
import router from '@/router';
import { login, findById, tokenRegeneration, logout } from '@/api/user';

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isValidToken: false,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          console.dir(data);
          if (data.result) {
            //성공한 경우
            let accessToken = data['accessToken'];
            let refreshToken = data['refreshToken'];
            //console.log("login success token created!!!! >> ", accessToken, refreshToken);
            commit('SET_IS_LOGIN', true);
            commit('SET_IS_LOGIN_ERROR', false);
            commit('SET_IS_VALID_TOKEN', true);
            sessionStorage.setItem('accessToken', accessToken);
            sessionStorage.setItem('refreshToken', refreshToken);
          } else {
            commit('SET_IS_LOGIN', false);
            commit('SET_IS_LOGIN_ERROR', true);
            commit('SET_IS_VALID_TOKEN', false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getUserInfo({ commit, dispatch }, token) {
      let decodeToken = JwtDecode(token);
      // console.log("2. getUserInfo() decodeToken :: ", decodeToken);
      await findById(
        decodeToken.userId,
        ({ data }) => {
          if (data.result === true) {
            commit('SET_USER_INFO', data.userInfo);
            // console.log("3. getUserInfo data >> ", data);
          } else {
            console.log('유저 정보 없음!!!!');
          }
        },
        async (error) => {
          console.log(
            'getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ',
            error.response.status
          );
          await dispatch('tokenRegeneration');
        }
      );
    },
    async tokenRegeneration({ commit, state }) {
      console.log('토큰 재발급 >> 기존 토큰 정보 : {}', sessionStorage.getItem('accessToken'));
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({ data }) => {
          if (data.result == true) {
            let accessToken = data['accessToken'];
            console.log('재발급 완료 >> 새로운 토큰 : {}', accessToken);
            sessionStorage.setItem('accessToken', accessToken);
            commit('SET_IS_VALID_TOKEN', true);
          } else {
            console.log('못 쓴다.');
            commit('SET_IS_VALID_TOKEN', false);
            commit('SET_IS_LOGIN', false);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            console.log('갱신 실패');
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              state.userInfo.no,
              ({ data }) => {
                if (data.message === 'success') {
                  console.log('리프레시 토큰 제거 성공');
                } else {
                  console.log('리프레시 토큰 제거 실패');
                }
                alert('RefreshToken 기간 만료!!! 다시 로그인해 주세요.');
                commit('SET_IS_LOGIN', false);
                commit('SET_USER_INFO', null);
                commit('SET_IS_VALID_TOKEN', false);
                router.push({ name: 'UserLogin' });
              },
              (error) => {
                console.log(error);
                commit('SET_IS_LOGIN', false);
                commit('SET_USER_INFO', null);
              }
            );
          }
        }
      );
    },
    async userLogout({ commit }, userno) {
      await logout(
        userno,
        ({ data }) => {
          if (data.result) {
            commit('SET_USER_INFO', null);
            commit('SET_IS_LOGIN', false);
            commit('SET_IS_VALID_TOKEN', false);
          } else {
            console.log('유저 정보 없음!!!!');
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default userStore;
