<script setup>
import { useQuasar } from "quasar";
import { route } from "quasar/wrappers";
import {
  getInformation,
  validation_username,
  signUp_account,
  add_profile,
} from "src/api/member";
import { useMemberStore } from "src/stores/member_inf";
import { getCurrentInstance, ref } from "vue";
const memberStore = useMemberStore();
import { useRouter } from "vue-router";
const router = useRouter();
const $q = useQuasar();
const username = ref("");
const username_signup = ref(null);
const password_signup = ref("");
const password = ref("");
const layout = ref(false);
const weight = ref(0);
const height = ref(0);
const email = ref("");
const age = ref(2);
const gender = ref("");
const weightUnit = ref("");
const dateOfSignUp = ref("");
async function onsignup() {
  console.log(username_signup.value);
  let result;
  let id;

  try {
    // 验证用户名是否可用
    result = await validation_username(
      username_signup.value,
      $api,
      "http://localhost:8080/api/usernamecheck"
    );

    if (result) {
      console.log("usernamecheck succeed");
      console.log("1");

      const member = {
        username: username_signup.value,
        password: password_signup.value,
        admin: 0,
      };

      console.log(member);

      // 注册用户并获取返回的数据
      const responseData = await signUp_account(
        member,
        $api,
        "http://localhost:8080/api/signupaccount"
      );

      console.log("Received data from signup_account:", responseData);
      id = parseInt(responseData);

      let date = new Date().toLocaleString();

      const profile = {
        id: id,
        age: age.value,
        email: email.value,
        gender: gender.value,
        date: date,
        weight: weight.value,
        height: height.value,
      };

      console.log(profile);

      // 添加用户资料
      const addProfileResponse = await add_profile(
        profile,
        $api,
        "http://localhost:8080/api/addProfile"
      );

      console.log("Added profile:", addProfileResponse);
      signup_reset;
      layout.value = false;
    } else {
      alert("username exists");
    }
  } catch (error) {
    console.error("Error in onsignup:", error);
  }
}

const $api = getCurrentInstance().appContext.config.globalProperties.$api;
function gosignup() {
  console.log("gosignup");
  router.push({ path: "/signup" });
}
function onlogin() {
  Validation();
}
function Validation() {
  const member = { username: username.value, password: password.value };
  $api
    .post("http://localhost:8080/api/login", member)
    .then((response) => {
      if (response.data !== 0) {
        console.log("Login successful:", response.data);
        console.log("id:" + response.data);
        memberStore.setMember({ id: response.data, username: username.value });
        console.log("Current member: ", memberStore.currentMember.username);
        $q.notify({
          icon: "done",
          position: "center",
          color: "positive",
          message: "Dear \t" + username.value + ", Login Successfully!!!",
        });
        username.value = null;
        password.value = null;
      } else {
        $q.notify({
          color: "negative",
          position: "center",
          message: "Username or Password is incorrect",
        });
      }
    })
    .catch((error) => {
      console.error("error", error);
    });

  //test
  let result;
  result = getInformation(1, $api, "http://localhost:8080/api/inf");
}

function signup_reset() {
  username.value = null;
  password_signup.value = null;
  email.value = null;
  gender.value = null;
  weight.value = null;
  height.value = null;
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
              :rules="[
                (val) => (val !== null && val.length > 0) || 'Please type username',
              ]"
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
        <br />
      </q-form>
      <!-- Row 5 -->
      <div class="row">
        <div class="col-12 col-md-4"></div>
        <div class="col-12 col-md-4 flex flex-center text-h4">
          <q-btn
            label="signup"
            type="button"
            color="primary"
            size="lg"
            @click="layout = true"
          />
        </div>
        <div class="col-12 col-md-4"></div>
      </div>
    </div>
  </q-page-container>

  <q-dialog v-model="layout">
    <q-layout view="Lhh lpR fff" container class="bg-white text-dark">
      <q-header class="bg-primary">
        <q-toolbar>
          <q-toolbar-title>Sign up</q-toolbar-title>
          <q-btn flat v-close-popup round dense icon="close" />
        </q-toolbar>
      </q-header>

      <q-page-container>
        <div class="q-pa-md flex flex-center">
          <q-form class="col-md-12 q-gutter-md" @submit="onsignup">
            <div class="text-h4 text-center">SIGN UP</div>
            <div class="row q-gutter-md">
              <q-input
                class="col-12 col-md-8"
                filled
                v-model="username_signup"
                label="Username*"
                hint="Please enter username"
                lazy-rules
                :rules="[(val) => (val && val.length > 0) || 'Please type username']"
              />
              <q-input
                class="col-12 col-md-8"
                filled
                v-model="password_signup"
                label="Password*"
                hint="Please enter at least 8 characters"
                type="password"
                lazy-rules
                :rules="[(val) => (val && val.length > 7) || 'At least 8 characters']"
              />
            </div>
            <div class="row q-gutter-md">
              <q-input
                class="col-12 col-md-8"
                filled
                v-model="email"
                label="Email*"
                hint="Please enter your email"
                lazy-rules
                :rules="[(val) => (val && val.includes('@')) || 'Email must include @']"
              />
              <q-select
                class="col-12 col-md-8"
                filled
                v-model="gender"
                label="Gender*"
                :options="['Female', 'Male']"
              />
            </div>
            <div class="row q-gutter-md">
              <q-input
                class="col-12 col-md-8"
                filled
                v-model="weight"
                label="Weight*"
                hint="Enter your weight"
                type="number"
                lazy-rules
                :rules="[(val) => val > 0 || 'Weight must be greater than 0']"
              />
              <q-select
                class="col-12 col-md-8"
                filled
                v-model="weightUnit"
                label="Unit*"
                :options="['kg', 'pound']"
              />
            </div>
            <div class="row q-gutter-md">
              <q-input
                class="col-12 col-md-8"
                filled
                v-model="height"
                label="Height(CM)*"
                hint="Enter your height"
                type="number"
                lazy-rules
                :rules="[(val) => val > 0 || 'Height must be greater than 0']"
              />
              <q-input
                class="col-12 col-md-3"
                filled
                v-model="age"
                label="Age*"
                hint="Enter your age"
                type="number"
                lazy-rules
                :rules="[(val) => val > 0 || 'Age must be greater than 0']"
              />

              <div class="col-12 col-md-6"><!-- 空列用于对齐 --></div>
            </div>
            <div class="row justify-center">
              <q-btn
                class="col-12 col-md-6"
                label="Signup"
                type="submit"
                color="primary"
              />
            </div>
          </q-form>
        </div>
      </q-page-container>
    </q-layout>
  </q-dialog>
</template>
