import http from './http';

const api = http;

async function getSidoList(success, fail) {
  await api.get(`/location/sido`).then(success).catch(fail);
}

function getGugunList(sidoCode, success, fail) {
  api.get(`/location/gugun/${sidoCode}`).then(success).catch(fail);
}

export { getSidoList, getGugunList };
