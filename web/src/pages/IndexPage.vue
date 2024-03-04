<script setup>
import { ref } from "vue";
const selectedDate = ref("");
const weight = ref("");
function getdata() {
  weight.value = selectedDate.value;
}
</script>

<template>
  <q-page-container>
    <div class="q-pa-md example-row-stacked-to-horizontal">
      <!-- Row 1 -->
      <div class="row">
        <div class="col-12 col-md-5">
          <q-date v-model="selectedDate" mask="YYYY-MM"></q-date>
        </div>
        <div class="col-12 col-md-2"></div>
        <div class="col-12 col-md-5">
          <q-input filled v-model="weight" label="Weight" />
        </div>
      </div>
      <!-- Row 2 -->
      <div class="row">
        <div class="col-12 col-md-5"></div>
        <div class="col-12 col-md-2"></div>
        <div class="col-12 col-md-5"></div>
      </div>
      <!-- Row  -->
      <div class="row">
        <div class="col-12 col-md"></div>
        <q-btn
          @click="getdata"
          style="background: #ff0080; color: white"
          label="Submit"
        />
        <div class="col-12 col-md"></div>
      </div>
    </div>
  </q-page-container>
</template>
