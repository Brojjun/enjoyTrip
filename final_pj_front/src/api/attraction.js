import http from './http';

const api = http;

async function getList(param, success, fail) {
  await api.get(`/attraction/list`, { params: param }).then(success).catch(fail);
}

async function updateHits(attractionNo, success, fail) {
  await api.put(`/attraction/updateHits/${attractionNo}`).then(success).catch(fail);
}

async function getView(attractionNo, success, fail) {
  await api.get(`/attraction/view/${attractionNo}`).then(success).catch(fail);
}

async function getFavorites(userNo, success, fail) {
  await api.get(`/attraction/favorite/${userNo}`).then(success).catch(fail);
}

async function updateFavorite(params, success, fail) {
  await api.post(`/attraction/favorite`, JSON.stringify(params)).then(success).catch(fail);
}

export { getList, updateHits, getView, getFavorites, updateFavorite };
