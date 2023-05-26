import http from './http';

const api = http;

async function checkId(userId, success, fail) {
  await api.get(`/user/checkDuplicateId/${userId}`).then(success).catch(fail);
}

async function signUp(user, success, fail) {
  await api.post(`/user/signUp`, JSON.stringify(user)).then(success).catch(fail);
}

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userId, success, fail) {
  api.defaults.headers['Authorization'] = 'TOKEN ' + sessionStorage.getItem('accessToken');
  await api.get(`/user/info/${userId}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers['Authorization'] = 'TOKEN ' + sessionStorage.getItem('refreshToken'); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userNo, success, fail) {
  await api.get(`/user/logout/${userNo}`).then(success).catch(fail);
}

async function findId(user, success, fail) {
  await api.post(`/user/findId`, JSON.stringify(user)).then(success).catch(fail);
}

async function findPw(user, success, fail) {
  await api.post('/user/findPw', JSON.stringify(user)).then(success).catch(fail);
}

async function update(user, success, fail) {
  await api.put('/user/update', user).then(success).catch(fail);
}

export { checkId, signUp, login, findById, tokenRegeneration, logout, findId, findPw, update };
