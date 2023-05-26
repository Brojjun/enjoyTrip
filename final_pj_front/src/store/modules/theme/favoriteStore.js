import { getFavorites, updateFavorite } from '@/api/attraction';

const favoriteStore = {
  namespaced: true,
  state: {
    favorites: [],
  },
  getters: {
    getFavorites(state) {
      return state.favorites;
    },
  },
  mutations: {
    SET_FAVORITES(state, data) {
      state.favorites = data;
    },
    ADD_FAVORITES(state, attractionId) {
      state.favorites.push(attractionId);
    },
    SUB_FAVORITES(state, attractionId) {
      state.favorites.splice(state.favorites.indexOf(attractionId), 1);
    },
  },
  actions: {
    async userGetFavorites({ commit }, userNo) {
      await getFavorites(
        userNo,
        ({ data }) => {
          if (data != null && data != '' && data != []) {
            commit('SET_FAVORITES', data);
          }
        },
        (error) => {
          console.log(error);
          alert('에러로 실패했습니다. 관리자에 문의해주세요.');
        }
      );
    },
    async userUpdateFavorite({ commit }, param) {
      await updateFavorite(
        param,
        ({ data }) => {
          if (data) {
            if (param.flag == 0) {
              //추가인 경우
              commit('ADD_FAVORITES', Number(param.attractionId));
            } else {
              commit('SUB_FAVORITES', Number(param.attractionId));
            }
          }
        },
        (error) => {
          console.log(error);
          alert('에러로 실패했습니다. 관리자에 문의해주세요.');
        }
      );
    },
  },
};

export default favoriteStore;
