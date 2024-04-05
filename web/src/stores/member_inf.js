import { defineStore } from "pinia";

export const useMemberStore = defineStore("member", {
  state: () => ({
    currentMember: { id: 0, username: 'username'},
    status:{logout:true},
  }),
  actions: {
    setMember(member) {
      this.currentMember = member;
    },
    resetMember() {
      this.currentMember = { id: 0, username: 'username'};
      this.status={logout:true};
    },
  },
  getters: {
    isMemberLoggedIn: (state) => state.currentMember !== null,
  },
});
