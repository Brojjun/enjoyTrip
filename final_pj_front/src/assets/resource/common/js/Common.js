//모바일인지 아닌지 단순 창 크기로 판단하는 함수
const checkMobileSize = () => {
  return window.innerWidth < 992;
};

export default checkMobileSize;
