<script setup>
import { ref, getCurrentInstance } from "vue";
import axios from "axios";
const selectedDate = ref("");
const calorie = ref("");
const weight = ref("");
const result = ref([]);
const sports = ref("");
const options = [
  {
    label: "足球",
    value: "soccer",
  },
  {
    label: "板球",
    value: "cricket",
  },
  {
    label: "曲棍球",
    value: "field_hockey",
  },
  {
    label: "网球",
    value: "tennis",
  },
  {
    label: "排球",
    value: "volleyball",
  },
  {
    label: "乒乓球",
    value: "table_tennis",
  },
  {
    label: "棒球",
    value: "baseball",
  },
  {
    label: "高尔夫",
    value: "golf",
  },
];
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
      })
      .catch((error) => {
        console.error("Error sending data:", error);
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
        <div class="col-12 col-md-2">
          <q-input filled v-model="weight" label="Weight(kg)" />
        </div>
        <div class="col-12 col-md-2"></div>
        <div class="col-12 col-md-2">
          <q-input filled v-model="calorie" label="Calorie" />
        </div>
        <div class="col-12 col-md-2"></div>
      </div>
      <br />
      <br />
      <br />
      <!-- Row 2 -->

      <br />
      <br />
      <br />
      <!-- Row 3 -->
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
      <!-- Row  -->
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
      <!-- Row Result-->
      <div class="row">
        <div class="col-12 col-md-12">
          <q-btn
            @click="getdata"
            style="background: lightblue; color: white"
            label="Get Users's List"
          />
        </div>
      </div>
    </div>
  </q-page-container>
</template>
