const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "", component: () => import("pages/IndexPage.vue") },
      { path: "user", component: () => import("pages/UsersInformation.vue") },
      { path: "record", component: () => import("pages/RecordPage.vue") },
      { path: "member", component: () => import("pages/MemberBoard.vue") },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
