<script setup>
import { ref, getCurrentInstance } from "vue";
import axios from "axios";
const selectedDate = ref("");
const calorie = ref("");
const weight = ref("");
const result = ref([]);
const username = ref("");
const email = ref("");

const $api = getCurrentInstance().appContext.config.globalProperties.$api;

function getdata() {
  const response = $api.get("http://localhost:8080/api/users", {}).then((response) => {
    console.log(response.data);
    const users = response.data;
    result.value = [];
    users.forEach((user) => {
      result.value.push(user);
    });
  });
}

function sendnewuser() {
  let pass = true;
  username.value !== "" && email.value !== "" ? pass : (pass = !pass);
  if (pass) {
    const user = {
      username: username.value,
      email: email.value,
    };
    console.log("User data:", user);

    $api
      .post("http://localhost:8080/api/add", user)
      .then((response) => {
        console.log("Data sent successfully:", response.data);
        alert("Data sent Successfully");
      })
      .catch((error) => {
        console.error("Error sending data:", error);
        alert("Error sending data");
      });
  } else {
    alert("Please Fill the button");
  }
}
</script>

<template>
  <!-- <q-date v-model="selectedDate" mask="YYYY-MM-DD">WSR</q-date> -->
  <q-page-container>
    <div class="q-pa-md example-row-stacked-to-horizontal">
      <!-- Row 1 -->
      <div class="row">
        <div class="col-12 col-md-4"></div>
        <div class="col-12 col-md-2"></div>
        <div class="col-12 col-md-2"></div>
        <div class="col-12 col-md-2"></div>
        <div class="col-12 col-md-2"></div>
      </div>
      <br />
      <br />

      <!-- Row 2 -->
      <br />
      <br />

      <!-- Row 3 -->
      <div class="row">
        <div class="col-12 col-md-3">
          <q-input filled v-model="weight" label="Weight(kg)" />
        </div>
        <div class="col-12 col-md-3"></div>
        <div class="col-12 col-md-3">
          <q-input filled v-model="calorie" label="Calorie" />
        </div>
        <div class="col-12 col-md-3"></div>
      </div>
      <br />
      <br />

      <!-- Row 4 -->
      <div class="row">
        <div class="col-12 col-md-12"></div>
      </div>
      <br />
      <br />

      <!-- Row 5 -->
      <div class="row">
        <div class="col-12 col-md-3">
          <q-input filled v-model="username" label="UserName" />
        </div>
        <div class="col-12 col-md-3"></div>
        <div class="col-12 col-md-3">
          <q-input filled v-model="email" label="Email" />
        </div>
        <div class="col-12 col-md-3"></div>
      </div>
      <br />
      <br />

      <!-- Row  6-->
      <div class="row">
        <div class="col-12 col-md-4"></div>
        <div class="col-12 col-md-3">
          <q-btn
            @click="sendnewuser"
            style="background: red; color: white"
            label="send a user"
          />
        </div>
        <div class="col-12 col-md-5"></div>
      </div>
      <br />
      <br />

      <!-- Row 7 Result-->
      <div class="row">
        <div class="col-12 col-md-12"></div>
      </div>
    </div>
  </q-page-container>
</template>
