import { getSidoList, getGugunList } from '@/api/location';

const locationStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: '선택하세요' }],
    guguns: [{ value: null, text: '선택하세요' }],
  },
  getters: {},
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: '선택하세요' }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: '선택하세요' }];
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
  },
  actions: {
    getSido: ({ commit }) => {
      getSidoList(
        ({ data }) => {
          commit('SET_SIDO_LIST', data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      getGugunList(
        sidoCode,
        ({ data }) => {
          commit('SET_GUGUN_LIST', data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default locationStore;
