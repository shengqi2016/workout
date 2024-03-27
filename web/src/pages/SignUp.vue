<script setup></script>
<template>
  <q-page-container>
    <div class="q-pa-md flex flex-center">
      <q-form class="col-md-8 q-gutter-md" @submit="onsignup">
        <div class="text-h4 text-center">Sig</div>
        <div class="row q-gutter-md">
          <q-input
            class="col-12 col-md-8"
            filled
            v-model="username"
            label="Username*"
            hint="Please enter username"
            lazy-rules
            :rules="[(val) => (val && val.length > 0) || 'Please type username']"
          />
          <q-input
            class="col-12 col-md-8"
            filled
            v-model="password"
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
            label="Height*"
            hint="Enter your height"
            type="number"
            lazy-rules
            :rules="[(val) => val > 0 || 'Height must be greater than 0']"
          />
          <div class="col-12 col-md-6"><!-- 空列用于对齐 --></div>
        </div>
        <div class="row justify-center">
          <q-btn class="col-12 col-md-6" label="Signup" type="submit" color="primary" />
        </div>
      </q-form>
    </div>
  </q-page-container>
</template>
