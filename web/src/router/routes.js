const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "", component: () => import("pages/IndexPage.vue") },
      { path: "user", component: () => import("pages/UsersInformation.vue") },
      { path: "record", component: () => import("pages/RecordPage.vue") },
      {
        path: "main/:id",
        component: () => import("layouts/MainLayout.vue"),
        props: true,
      },
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
