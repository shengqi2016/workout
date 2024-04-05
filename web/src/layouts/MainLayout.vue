<template>
  <q-layout view="lHh Lpr lFf">
    <q-header id="q_header">
      <q-toolbar>
        <q-btn flat dense round icon="menu" aria-label="Menu" @click="toggleLeftDrawer" />
        <q-toolbar-title @click="goToHomePage" style="user-select: none">
          Work Out Record
        </q-toolbar-title>
        <div>{{ time }}</div>
        <q-btn label="Log Out" @click="cookie_remove" v-if="login" />
        <q-btn icon="dark_mode" @click="darkClicked" />
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered>
      <q-list v-if="login" style="margin-top: 150px">
        <q-item-label header> Super Links</q-item-label>
        <div v-for="link in essentialLinks" :key="link.title">
          <EssentialLink v-bind="link" />
        </div>
      </q-list>
      <q-drawer v-model="leftDrawerOpen" show-if-above bordered>
        <q-list style="margin-top: 150px">
          <q-item-label header>Super Links</q-item-label>
          <EssentialLink v-for="link in filteredLinks" :key="link.title" v-bind="link" />
        </q-list>
      </q-drawer>
      <q-img
        class="absolute-top"
        src="https://cdn.quasar.dev/img/material.png"
        style="height: 150px"
      >
        <div class="absolute-bottom bg-transparent">
          <q-avatar size="56px" class="q-mb-sm">
            <img src="https://cdn.quasar.dev/img/boy-avatar.png" />
          </q-avatar>
          <div class="text-weight-bold" id="name">{{ username }}</div>
          <div id="accountName">@JeffQi</div>
        </div>
      </q-img>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup>
import { ref, onUnmounted, computed } from "vue";
import { Dark, useQuasar } from "quasar";
import { useMemberStore } from "src/stores/member_inf";
import EssentialLink from "components/EssentialLink.vue";
const memberStore = useMemberStore();
const time = ref(new Date().toLocaleString());
import { useRouter } from "vue-router";
const router = useRouter();
const $q = useQuasar();
const login = computed(() => {
  return !memberStore.status.logout;
});
const filteredLinks = computed(() => {
  if (login.value) {
    return essentialLinks;
  } else {
    return essentialLinks.filter((link) => link.title === "Google");
  }
});
const username = computed(() => {
  if (memberStore.currentMember) {
    return memberStore.currentMember.username;
  }
  return "username";
});
function goToHomePage() {
  router.push({ path: "/" });
}
const intervalId = setInterval(() => {
  time.value = new Date().toLocaleString();
}, 1000);

function cookie_remove() {
  $q.cookies.remove("username");
  $q.cookies.remove("password");
  $q.notify({
    icon: "done",
    position: "center",
    color: "positive",
    message: "Dear \t" + username.value + ", LOG OUT Successfully!!!",
  });
  memberStore.resetMember();
  router.push({ path: "/" });
}
Dark.set(false);
function darkClicked() {
  Dark.toggle();
}

const essentialLinks = [
  {
    title: "Google",
    caption: "Search Engine",
    icon: "tungsten",
    link: "https://www.google.com",
  },
  {
    title: "Information",
    caption: "Personal Information",
    icon: "account_circle",
    link: "http://localhost:9000/api/#/user",
  },
  {
    title: "Record",
    caption: "Record workout and meal",
    icon: "fitness_center",
    link: "http://localhost:9000/#/record",
  },
];

const leftDrawerOpen = ref(false);
const toggleLeftDrawer = () => {
  leftDrawerOpen.value = !leftDrawerOpen.value;
};

onUnmounted(() => {
  clearInterval(intervalId);
});
</script>
