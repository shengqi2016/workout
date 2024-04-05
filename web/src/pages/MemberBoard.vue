<script setup>
import { ref, onUnmounted, computed, onMounted } from "vue";
import { useMemberStore } from "src/stores/member_inf";
import { useRouter } from "vue-router";
const router = useRouter();
const memberStore = useMemberStore();
const username = computed(() => {
  return memberStore.currentMember?.username;
});

onMounted(() => {
  if (memberStore.status.logout == true) {
    router.push({ path: "/" });
  }
});
</script>

<template>
  <q-page-container>
    <div class="q-pa-md flex flex-center">
      <div class="row">
        <div class="col-12 col-md-4 flex flex-center text-h4">Dear, {{ username }}</div>
      </div>
    </div>
  </q-page-container>
</template>
