import { defineStore } from "pinia";

export const useMemberStore = defineStore("member", {
  state: () => ({
    currentMember: null,
  }),
  actions: {
    setMember(member) {
      this.currentMember = member;
    },
    resetMember() {
      this.currentMember = null;
    },
  },
  getters: {
    isMemberLoggedIn: (state) => state.currentMember !== null,
  },
});
