<script setup>
import { ref, getCurrentInstance } from "vue";
import axios from "axios";
const usersInforamtion = ref([]);

const lengthOfUsers = ref(0);

const $api = getCurrentInstance().appContext.config.globalProperties.$api;

const response = $api.get("http://localhost:8080/api/users", {}).then((response) => {
  console.log(response.data);
  usersInforamtion.value = response.data;
  lengthOfUsers.value = usersInforamtion.value.length;
});
const columns = [
  {
    name: "id",
    required: true,
    label: "ID",
    align: "left",
    field: (row) => row.id,
    format: (val) => `${val}`,
    sortable: true,
  },
  {
    name: "username",
    align: "center",
    label: "USERNAME",
    field: "username",
    sortable: true,
  },
  { name: "email", label: "EMAIL", field: "email", sortable: true },
];
</script>

<template>
  <q-page-container>
    <div class="q-pa-md example-row-stacked-to-horizontal">
      <!-- Row 1 -->
      <div class="row">
        <div class="col-12 col-md-4">
          <!-- <q-card>{{ usersInforamtion }}</q-card> -->
        </div>
        <div class="col-12 col-md-4">
          <!-- <q-btn
            @click="getdata"
            style="background: pink; color: white"
            label="Get Users's List"
          /> -->
        </div>
        <div class="col-12 col-md-4"></div>
      </div>
      <br />
      <!-- Row 2 -->
      <div class="row">
        <div class="col-12 col-md-2"></div>
        <div class="col-12 col-md-8">
          <div class="q-pa-md">
            <q-table
              class="my-sticky-dynamic"
              flat
              bordered
              title="User Information"
              :rows="usersInforamtion"
              :columns="columns"
              :loading="loading"
              row-key="id"
              virtual-scroll
              :virtual-scroll-item-size="48"
              :virtual-scroll-sticky-size-start="48"
              :pagination="pagination"
              :rows-per-page-options="[0]"
              @virtual-scroll="onScroll"
            />
          </div>
          <div class="col-12 col-md-2"></div>
        </div>
      </div>
    </div>
  </q-page-container>
</template>
