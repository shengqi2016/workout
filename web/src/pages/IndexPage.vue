<script setup>
import { ref, getCurrentInstance } from "vue";
import axios from "axios";
const selectedDate = ref("");
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
      <q-tabs v-model="tab" no-caps class="bg-orange text-white shadow-2">
        <q-tab name="mails" label="Mails" />
        <q-tab name="alarms" label="Alarms" />
        <q-tab name="movies" label="Movies" />
      </q-tabs>
      <br />
      <br />
      <br />
      <!-- Row 3 -->
      <div class="row">
        <div class="col-12 col-md-5">
          <q-input filled v-model="calorie" label="Calorie" />
        </div>
        <div class="col-12 col-md-2"></div>
        <div class="col-12 col-md-5">
          <q-select filled v-model="sports" :options="options" label="Sports Type" />
        </div>
      </div>
      <!-- Row  -->
      <div class="row">
        <div class="col-12 col-md-5"></div>
        <div class="col-12 col-md-2">
          <q-btn
            @click="getdata"
            style="background: lightblue; color: white"
            label="Get Users's List"
          />
        </div>
        <div class="col-12 col-md-5"></div>
      </div>
      <br />
      <!-- Row Result-->
      <div class="row">
        <div class="col-12 col-md-12">
          <h5>
            {{ result }}
          </h5>
        </div>
      </div>
    </div>
  </q-page-container>
</template>
