<template>
  <q-page class="flex flex-center">
    <q-btn label="click me" @click="callme" />
    <button @click="touch">{{ count }}</button>
    <span>{{ msg }}</span>
    <br />
    <span>{{ msg1 }}</span>
  </q-page>
</template>

<script setup>
import { ref } from "vue";

const msg = ref("123");
const count = ref(0);
let msg1 = "abc";

function callme() {
  msg.value = "clicked";
  msg1 = "456";
}
function touch() {
  //condition ? exprIfTrue : exprIfFalse
  count.value = count.value >= 10 ? 0 : count.value + 1;
}
</script>
