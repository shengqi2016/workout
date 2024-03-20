<script setup>
import { ref, getCurrentInstance } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
const username = ref("");
const password = ref(null);
const $api = getCurrentInstance().appContext.config.globalProperties.$api;

function onlogin() {
  Validation();
}
function Validation() {
  const member = { username: username.value, password: password.value };
  $api
    .post("http://localhost:8080/api/login", member)
    .then((response) => {
      console.log("Login successful:", response.data);
      userId.value = response.data;
    })
    .catch((error) => {
      console.error("error", error);
    });
}
</script>

<template>
  <q-page-container>
    <div class="q-pa-md example-row-stacked-to-horizontal">
      <!-- Row 1 -->
      <br />
      <br />
      <q-form @submit="onlogin">
        <div class="row">
          <div class="col-12 col-md-4"></div>
          <div class="col-12 col-md-4 flex flex-center text-h4">LOG IN</div>
          <div class="col-12 col-md-4"></div>
        </div>
        <br />
        <br />
        <!-- Row 2 -->
        <div class="row">
          <div class="col-12 col-md-4"></div>
          <div class="col-12 col-md-4">
            <q-input
              filled
              v-model="username"
              label="Usermame*"
              lazy-rules
              :rules="[(val) => val.length > 0 || 'Please type username']"
            />
          </div>
          <div class="col-12 col-md-4"></div>
        </div>
        <!-- Row 3 -->
        <br />
        <div class="row">
          <div class="col-12 col-md-4"></div>
          <div class="col-12 col-md-4">
            <q-input
              filled
              v-model="password"
              label="Password"
              lazy-rules
              :rules="[
                (val) => (val !== null && val.length > 0) || 'Please Enter password',
              ]"
            />
          </div>
          <div class="col-12 col-md-4"></div>
        </div>
        <!-- Row 4 -->
        <div class="row">
          <div class="col-12 col-md-4"></div>
          <div class="col-12 col-md-4 flex flex-center text-h4">
            <q-btn label="login" type="submit" color="primary" />
          </div>
          <div class="col-12 col-md-4"></div>
        </div>
      </q-form>
      <br />
      <br />
      <!-- Row 2 -->
      <br />
      <br />
    </div>
  </q-page-container>
</template>
