PopupManager = {
  closePopCallback: {},
  zIndex: 9999,
  isLock: false,
  isRowFullSize: false,
  isColumnFullSize: false,
  isMobile: false,
  popGarbegeList: {},
  popList: [],

  popBgClickEvent: function (el) {
    let _this = this;

    //페이지를 막는 팝업일 때
    let pageAccessBlock = false;
    if (el.getAttribute('data-popup-page-access-block')) pageAccessBlock = true;

    if (!_this.isMobile) {
      el.addEventListener('dragend', (e) => {
        e.stopPropagation();
      });
      if (!pageAccessBlock) {
        el.addEventListener('click', () => {
          _this.removePopLatest(true);
        });
      }

      el.querySelector('.popup_inner').addEventListener('click', (e) => {
        e.stopPropagation();
      });
    }
  },

  // isSaveEvent: 이벤트를 남겨야할 경우
  removePopLatest: function (isSaveEvent) {
    if (this.popList.length > 0) this.removePop(this.popList[this.popList.length - 1], isSaveEvent);

    if (this.popList.length == 0) {
      this.unlockScroll();
    }
  },

  getPopLatest: function () {
    if (this.popList.length > 0) return this.popList[this.popList.length - 1];
    else return null;
  },

  checkContentsFullSize: function (el) {
    var _this = this;
    var contentsCon = el.querySelector('.popup_con');
    var contents = contentsCon.querySelector('.popup_inner');

    //실제 컨텐츠가 넘치는지 체크 (가로는 display : table-cell로 인해서 max-width를 무시하기 때문에 필요, 세로는 넘쳤을 때 header 부분을 fixed로 해야하기 때문에 필요)
    // if ($(contentsCon).css('maxWidth').replace('px', '') <= $(window).width()) {
    //   // 가로로 꽉차지 않았을 때
    //   _this.isRowFullSize = false;
    // } else {
    //   // 가로로 꽉찼을 때
    //   _this.isRowFullSize = true;
    // }

    // if ($(el).innerHeight() >= $(contentsCon).innerHeight() + 100) {
    //   // 세로로 넘치지 않았을 때
    //   _this.isColumnFullSize = false;
    // } else {
    //   // 세로로 넘쳤을 때
    //   _this.isColumnFullSize = true;
    // }
  },

  positioning: function (el) {
    var _this = this;
    var contentsCon = el.querySelector('.popup_con');
    var contents = contentsCon.querySelector('.popup_inner');

    // if (_this.isRowFullSize) {
    //   //가로로 꽉찬 경우
    //   contents.css('maxWidth', $(window).width());
    // } else {
    //   //가로로 꽉차지 않은 경우
    //   contents.css('maxWidth', '');
    // }

    /*        if ( _this.isColumnFullSize && !(_this.isMobile) ) {

            _this.onScrollEvent( el )

        } else {
            _this.offScrollEvent( el )

        }*/
  },

  repositioning: function (el) {
    var _this = this;

    _this.positioning(el, false);

    window.addEventListener('resize', function () {
      _this.isMobile = checkMobileSize();
      _this.checkContentsFullSize(el);
      _this.positioning(el, false);
    });
  },

  // 팝업을 추가하기 위해 무조건 이 function 을 타야함.
  add: function (el, closeCallbackFn) {
    var _this = this;

    el = document.querySelector(el);

    if (Object.prototype.toString.call(el) == '[object String]') {
      // selecter
      var elSelectResult = $(el);
      if (elSelectResult.length == 0) {
        el = this.popGarbegeList[el];
      } else {
        this.popGarbegeList[el] = $(el)[0];
        el = this.popGarbegeList[el];
      }
    } else if (el.length && el.context) {
      // jquery element
      this.popGarbegeList[el.selector] = el[0];
      el = this.popGarbegeList[el.selector];
    } else {
      // jquery element but no element
      if (el.selector && this.popGarbegeList[el.selector]) {
        el = this.popGarbegeList[el.selector];
      } else {
        this.popGarbegeList[el] = el;
      }
    }

    this.popList.push(el);
    this.closePopCallback[el] = closeCallbackFn;

    if (el.parentNode == null) {
      el.style.display = 'none';
      document.appendChild(el);
    }

    _this.checkContentsFullSize(el);

    _this.isMobile = checkMobileSize() ? true : false;

    this.popBgClickEvent(el);

    el.style.cssText = `display: block; z-index: ${
      this.zIndex + this.popList.length * 3 + 1
    }; position: fixed; top: 0; left: 0;`;

    document.querySelector('body').appendChild(el);

    _this.repositioning(el, false);

    _this.lockScroll();

    return el;
  },

  // isSaveEvent: 이벤트를 남겨야할 경우
  removePop: function (el, isSaveEvent) {
    if (this.closePopCallback[el]) {
      this.closePopCallback[el]({ target: el });
      delete this.closePopCallback[el];
    }

    if (Object.prototype.toString.call(el) == '[object String]') {
      el = $(el)[0];
    } else if (el.length && el.context) {
      el = el[0];
    }

    this.popList.splice(this.popList.indexOf(el), 1);

    /*this.offScrollEvent( el );*/

    if (el.parentNode) {
      if (!isSaveEvent) {
        el.remove();
      } else {
        el.style.display = 'none';
      }
    }

    if (this.popList.length == 0) {
      this.unlockScroll();
    }

    return el;
  },

  //열려있는 Popup을 모두 제거
  removePopAll: function (isSaveEvent) {
    var _this = this;

    if (this.popList.length > 0) {
      for (var i = this.popList.length - 1; i > -1; i--) {
        this.removePop(this.popList[i], isSaveEvent);
      }
    }

    if (this.popList.length == 0) {
      this.unlockScroll();
    }
  },

  lockScroll: function () {
    if (this.isLock) return;

    $html = document.querySelector('html');
    $body = document.querySelector('body');
    var initWidth = $body.clientWidth;
    var initHeight = $body.clientHeight;

    var scrollPosition = [
      self.pageXOffset || document.documentElement.scrollLeft || document.body.scrollLeft,
      self.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop,
    ];
    $html.setAttribute('data-scroll-position', scrollPosition);
    $html.setAttribute('data-previous-overflow', $html.style.overflow);
    $html.style.overflow = 'hidden';
    window.scrollTo(scrollPosition[0], scrollPosition[1]);

    var marginR = $body.clientWidth - initWidth;
    var marginB = $body.clientHeight - initHeight;
    $body.style.cssText = `margin-right: ${marginR},'margin-bottom': ${marginB}`;

    $body.style.overflow = 'hidden';

    this.isLock = true;
  },

  unlockScroll: function () {
    if (!this.isLock) return;
    $html = document.querySelector('html');
    $body = document.querySelector('body');
    $html.style.overflow = $html.getAttribute('data-previous-overflow');
    let scrollPosition = $html.getAttribute('data-scroll-position');
    window.scrollTo(scrollPosition[0], scrollPosition[1]);

    $body.style.cssText = `margin-right: 0, margin-bottom : 0`;

    $body.style.overflow = '';
    this.isLock = false;
  },
};
