<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated style="background-color: dimgrey">
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="toggleLeftDrawer"
        />

        <q-toolbar-title> Work Out Record </q-toolbar-title>

        <div style="margin-right: 10%">
          Current: Time: {{ time + "      " }}
        </div>

        <q-btn icon="dark_mode" @click="darkClicked" />
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered>
      <q-list style="margin-top: 150px">
        <q-item-label header> Super Links </q-item-label>

        <EssentialLink
          v-for="link in essentialLinks"
          :key="link.title"
          v-bind="link"
        />
      </q-list>
      <q-img
        class="absolute-top"
        src="https://cdn.quasar.dev/img/material.png"
        style="height: 150px"
      >
        <div class="absolute-bottom bg-transparent">
          <q-avatar size="56px" class="q-mb-sm">
            <img src="https://cdn.quasar.dev/img/boy-avatar.png" />
          </q-avatar>
          <div class="text-weight-bold" id="name">Qi Sheng</div>
          <div id="accountName">@JeffQi</div>
        </div>
      </q-img>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import { defineComponent, ref, onUnmounted } from "vue";
import EssentialLink from "components/EssentialLink.vue";
setInterval(() => {}, 1000);
const time = ref(new Date().toLocaleString());
const intervalId = setInterval(() => {
  time.value = new Date().toLocaleString();
}, 1000);

const dark = ref(false);
function darkClicked() {
  dark.value = true
    ? (document.body.style.backgroundColor = "white")
    : (document.body.style.backgroundColor = "black");
  console.log("darkClicked()");
  dark.value = !dark.value;
}

const linksList = [
  {
    title: "Google",
    caption: "Search Engine",
    icon: "tungsten",
    link: "https://www.google.com",
  },
  {
    title: "Information",
    caption: "Personal Inforamation",
    icon: "account_circle",
    link: "https://www.google.com",
  },
];

onUnmounted(() => {
  clearInterval(intervalId);
});
export default defineComponent({
  name: "MainLayout",

  components: {
    EssentialLink,
  },

  setup() {
    const leftDrawerOpen = ref(false);

    return {
      essentialLinks: linksList,
      time,
      leftDrawerOpen,
      darkClicked,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value;
      },
    };
  },
});
</script>
