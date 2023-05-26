let gnb = {
  isMobile: false,
  isSelectedMenu: false,

  windowScrollTop: 0,

  originGnb: null,
  originGnbMenu: null,
  clickGnb: null,
  clickGnbDim: null,
  clickGnbMenu: null,
  init: function () {
    this.originGnb = document.getElementById('originHeader');
    this.originGnbMenu = this.originGnb.querySelector('.gnb_con');
    this.clickGnb = document.getElementById('clickHeader');
    this.clickGnbMenu = this.clickGnb.querySelector('.gnb_con');
    this.clickGnbDim = document.getElementById('clickHeaderDim');

    if (checkMobileSize()) this.isMobile = true;

    this.windowScrollTop = window.scrollY || document.documentElement.scrollTop;

    this.findGnbMenu();

    this.registEvent();
  },

  registEvent: function () {
    let _this = this;

    window.addEventListener('resize', () => {
      _this.windowResizeEvent();
    });

    window.addEventListener('scroll', () => {
      _this.windowScrollTop = window.scrollY || document.documentElement.scrollTop;
    });

    _this.clickGnbDim.addEventListener('click', () => {
      _this.closeClickGnb();
    });

    //모바일인 경우
    /*,if (_this.isMobile) {
      _this.originGnbMenuMobileEvent();
      _this.clickGnbMenuMobileEvent();
    } else {
      //웹인 경우
      _this.originGnbMenuWebEvent();
      _this.clickGnbMenuWebEvent();
    }*/
  },

  //현재 메뉴에서 active를 해줘야 하는지 찾아주는 함수
  findGnbMenu: function () {},

  originGnbMenuMobileEvent: function () {
    let _this = this;
    let menuList = _this.originGnbMenu;
    let depth1li = menuList.querySelectorAll('> li');
    Array.prototype.forEach.call(depth1li, (el) => {
      el.addEventListener('click', function () {});
    });
  },
  originGnbMenuWebEvent: function () {
    let menuList = _this.originGnbMenu;
    let depth1li = menuList.querySelectorAll('> li');

    let activeDepth1MenuIdx = menuList.indexOf(menuList.querySelector('> li.active'));

    Array.prototype.forEach.call(depth1li, (el) => {
      el.addEventListener('click', function () {});
    });
  },

  clickGnbMenuMobileEvent: function () {},
  clickGnbMenuWebEvent: function () {},

  //현재 메뉴에서 active를 해줘야 하는지 찾아주는 함수
  lockScroll: function () {
    $html = document.querySelector('html');
    $body = document.querySelector('body');
    let initWidth = $body.outerWidth();
    let initHeight = $body.outerHeight();

    let scrollPosition = [
      self.pageXOffset || document.documentElement.scrollLeft || document.body.scrollLeft,
      self.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop,
    ];
    $html.setAttribute('data-scroll-position', scrollPosition);
    $html.setAttribute('data-previous-overflow', $html.style.overflow);
    $html.style.overflow = 'hidden';
    window.scrollTo(scrollPosition[0], scrollPosition[1]);

    let marginR = $body.outerWidth() - initWidth;
    let marginB = $body.outerHeight() - initHeight;
    $body.style.cssText = `margin-right: ${marginR},'margin-bottom': ${marginB}`;
  },
  unlockScroll: function () {
    $html = document.querySelector('html');
    $body = document.querySelector('body');
    $html.style.overflow = $html.getAttribute('data-previous-overflow');
    let scrollPosition = $html.getAttribute('data-scroll-position');
    window.scrollTo(scrollPosition[0], scrollPosition[1]);

    $body.style.cssText = `margin-right: 0, margin-bottom : 0`;
  },

  windowResizeEvent: function () {},

  openClickGnb: function () {
    let _this = this;

    if (!_this.clickGnb.classList.contains('default')) _this.clickGnb.classList.add('default');

    _this.clickGnbDim.classList.add('open');
    _this.clickGnb.classList.add('active');
  },
  closeClickGnb: function () {
    let _this = this;

    _this.clickGnb.classList.remove('active');
    _this.clickGnbDim.classList.remove('open');

    document.querySelector('body').style.overflow = '';
  },

  removeEvent: function () {},
  destroy: function () {},
};

windowOnload(function () {
  setTimeout(gnb.init(), 5000);
});
