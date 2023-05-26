import http from './http';

const api = http;

async function getList(param, success, fail) {
  await api.get(`/route/list`, { params: param }).then(success).catch(fail);
}

async function updateHits(routeNo, success, fail) {
  await api.put(`/route/updateHits/${routeNo}`).then(success).catch(fail);
}

async function getView(routeNo, success, fail) {
  await api.get(`/route/view/${routeNo}`).then(success).catch(fail);
}

async function getFavorites(userNo, success, fail) {
  await api.get(`/route/favorite/${userNo}`).then(success).catch(fail);
}

async function updateFavorite(params, success, fail) {
  await api.post(`/route/favorite`, JSON.stringify(params)).then(success).catch(fail);
}

async function insert(params, success, fail) {
  await api.post(`/route/insert`, JSON.stringify(params)).then(success).catch(fail);
}

export { getList, updateHits, getView, getFavorites, updateFavorite, insert };
