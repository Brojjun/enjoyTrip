import Vue from 'vue';
import VueRouter from 'vue-router';
import AppMain from '@/views/AppMain';
import AppAttraction from '@/views/AppAttraction';
import AppNotice from '@/views/AppNotice';
import AppRoute from '@/views/AppRoute';

import store from '@/store';

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters['userStore/checkUserInfo'];
  const checkToken = store.getters['userStore/checkToken'];
  let token = sessionStorage.getItem('accessToken');
  console.log('로그인 처리 전', checkUserInfo, token);

  //토큰 유효성 체크
  if (checkUserInfo != null && token) {
    await store.dispatch('userStore/getUserInfo', token);
  }
  console.log(checkToken);
  //로그인이 안되어 있을 때
  if (!checkToken || checkUserInfo === null) {
    alert('로그인이 필요한 페이지입니다..');
    // next({ name: "login" });
    router.push({ name: 'UserLogin' });
  } else {
    //로그인이 되어있을 때
    next();
  }
};

const onlyAuthNotUser = async (to, from, next) => {
  const checkUserInfo = store.getters['userStore/checkUserInfo'];
  const checkToken = store.getters['userStore/checkToken'];
  let token = sessionStorage.getItem('accessToken');

  //토큰 유효성 체크
  if (checkUserInfo != null && token) {
    await store.dispatch('userStore/getUserInfo', token);
  }
  //로그인이 안되어 있을 때
  if (!checkToken || checkUserInfo === null) {
    next();
  } else {
    //로그인이 되어있을 때
    alert('로그인시 사용 불가능한 페이지입니다.');
    router.push({ name: 'home' });
  }
};

const routes = [
  {
    path: '/',
    name: 'home',
    component: AppMain,
  },
  {
    path: '/user',
    name: 'user',
    component: () => import('@/views/AppUser'),
    children: [
      {
        path: 'login',
        name: 'UserLogin',
        beforeEnter: onlyAuthNotUser,
        component: () => import(/* webpackChunkName: "about" */ '../components/user/UserLogin.vue'),
      },
      {
        path: 'signUp',
        name: 'UserSignUp',
        beforeEnter: onlyAuthNotUser,
        component: () =>
          import(/* webpackChunkName: "about" */ '../components/user/UserSignUp.vue'),
      },
      {
        path: 'logout',
        name: 'UserLogout',
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "about" */ '../components/user/UserLogout.vue'),
      },
      {
        path: 'findId',
        name: 'UserFindId',
        beforeEnter: onlyAuthNotUser,
        component: () =>
          import(/* webpackChunkName: "about" */ '../components/user/UserFindId.vue'),
      },
      {
        path: 'findPw',
        name: 'UserFindPw',
        beforeEnter: onlyAuthNotUser,
        component: () =>
          import(/* webpackChunkName: "about" */ '../components/user/UserFindPw.vue'),
      },
      {
        path: 'myPage',
        name: 'UserMyPage',
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "about" */ '../components/user/mypage/UserMyPage.vue'),
      },
    ],
  },
  {
    path: '/attraction',
    name: 'Attraction',
    redirect: '/attraction/list',
    component: AppAttraction,
    children: [
      {
        path: 'list',
        name: 'AttractionList',
        component: () =>
          import(
            /* webpackChunkName: "about" */ '../components/attraction/list/AttractionList.vue'
          ),
      },
      {
        path: 'view/:no',
        name: 'AttractionView',
        component: () =>
          import(
            /* webpackChunkName: "about" */ '../components/attraction/view/AttractionView.vue'
          ),
      },
    ],
  },
  {
    path: '/route',
    name: 'Route',
    redirect: '/route/list',
    component: AppRoute,
    children: [
      {
        path: 'list',
        name: 'RouteList',
        component: () =>
          import(/* webpackChunkName: "about" */ '../components/route/list/RouteList.vue'),
      },
      {
        path: 'view/:no',
        name: 'RouteView',
        component: () =>
          import(/* webpackChunkName: "about" */ '../components/route/view/RouteView.vue'),
      },
      {
        path: 'write',
        name: 'RouteWrite',
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "about" */ '../components/route/write/RouteWrite.vue'),
      },
      {
        path: 'update',
        name: 'RouteUpdate',
        component: () =>
          import(/* webpackChunkName: "about" */ '../components/route/update/RouteUpdate.vue'),
      },
    ],
  },
  {
    path: '/notice',
    name: 'notice',
    component: AppNotice,
    redirect: '/notice/list',
    children: [
      {
        path: 'list',
        name: 'noticeList',
        component: () =>
          import(/* webpackChunkName: "notice" */ '../components/notice/list/NoticeList.vue'),
      },
      {
        path: 'write',
        name: 'noticeWrite',
        component: () =>
          import(/* webpackChunkName: "notice" */ '../components/notice/NoticeWrite.vue'),
      },
      {
        path: 'modify/:no',
        name: 'noticeModify',
        component: () =>
          import(/* webpackChunkName: "notice" */ '../components/notice/NoticeModify.vue'),
      },
      {
        path: 'view/:no',
        name: 'noticeView',
        component: () =>
          import(/* webpackChunkName: "notice" */ '../components/notice/NoticeView.vue'),
        children: [
          {
            path: 'comment',
            name: 'comment',
            component: () => import('../components/notice/comment/NoticeComment.vue'),
          },
        ],
      },
    ],
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
