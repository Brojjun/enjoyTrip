import http from './http';
import file from './file';
const api = http;
const fileApi = file;
// notice list

//get comment amount
async function getCommentCnt(success, fail) {
  await api.get(`/notice/articleCnt`).then(success).catch(fail);
}

//get need articel
async function getNoticeList(page, success, fail) {
  await api.get(`/notice/list/${page}`).then(success).catch(fail);
}

// notice modify
async function updateNotice(no, body) {
  await fileApi.put(`/notice/update/${no}`, body);
}

// get article
async function getNotice(no, success, fail) {
  await api.get(`notice/info/${no}`).then(success).catch(fail);
}

// delete notice
async function deleteNotice(no, success, fail) {
  await api.put(`notice/delete/${no}`).then(success).catch(fail);
}

//write notice
async function writeNotice(body, success, fail) {
  await fileApi.post(`notice/write`, body).then(success).catch(fail);
}

// download
async function downLoad(item, success, fail) {
  await fileApi.get(`download/${item}`, { responseType: 'blob' }).then(success).catch(fail);
}

async function addHit(no, success, fail) {
  await api.put(`notice/hit/${no}`).then(success).catch(fail);
}

async function writeLike(body, success, fail) {
  await api.post(`notice/like`, body).then(success).catch(fail);
}

async function deleteLike(noticeNo, userNo, success, fail) {
  await api.delete(`notice/like/${noticeNo}/${userNo}`).then(success).catch(fail);
}

async function getLikeCnt(no, success, fail) {
  await api.get(`notice/like/cnt/${no}`).then(success).catch(fail);
}

async function getNoticeLike(userNo, noticeNo, success, fail) {
  await api.get(`notice/like/${userNo}/${noticeNo}`).then(success).catch(fail);
}

async function getCommentList(noticeNo, success, fail) {
  await api.get(`noticeComment/list/${noticeNo}`).then(success).catch(fail);
}

async function writeComment(param, success, fail) {
  await api.post('noticeComment/write', param).then(success).catch(fail);
}

async function deleteComment(param, success, fail) {
  await api.put('noticeComment/delete', param).then(success).catch(fail);
}

export {
  writeLike,
  deleteLike,
  getLikeCnt,
  getNoticeLike,
  addHit,
  getCommentCnt,
  getNoticeList,
  updateNotice,
  getNotice,
  deleteNotice,
  writeNotice,
  getCommentList,
  writeComment,
  deleteComment,
  downLoad,
};
