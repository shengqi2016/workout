<script setup>
import { ref, getCurrentInstance } from "vue";
import axios from "axios";
const selectedDate = ref("");
const calorie = ref("");
const weight = ref(0);
const heartrate = ref(0);
const dataOfWork = ref("2019/02/01");
const duration = "0";
const username = ref("");
const email = ref("");
const model3 = ref(4.5);
const times = ref(0);
const tab = ref("bodyrecord");
const measure = ref("kg");
const sporp_type = ref("running");
const $api = getCurrentInstance().appContext.config.globalProperties.$api;
const options_measure = ["kg", "pound"];
const options_sport = [
  "Running",
  "Cycling",
  "Swimming",
  "Weightlifting",
  "Yoga",
  "Basketball",
  "Jump Rope",
  "Hiking",
];
function resetModels() {
  model3.value = 3.0;
}
</script>

<template>
  <q-page-container>
    <div class="row">
      <div class="col-12 col-md-2"></div>
      <div class="col-12 col-md-8">
        <div class="q-gutter-y-md">
          <q-card>
            <q-tabs
              v-model="tab"
              dense
              class="text-grey"
              active-color="primary"
              indicator-color="primary"
              align="justify"
              narrow-indicator
            >
              <q-tab name="bodyrecord" label="Body Record" />
              <q-tab name="sportrecord" label="WorkOut Record" />
              <q-tab name="foodintake" label="Food intake" />
            </q-tabs>

            <q-separator />

            <q-tab-panels v-model="tab" animated>
              <q-tab-panel name="bodyrecord">
                <br />
                <div class="text-h6">Body Record</div>

                <!-- row-1 -->
                <div class="row">
                  <div class="col-12 col-md-3">
                    <q-input outlined v-model="weight" label="Weight" />
                  </div>
                  <div class="col-12 col-md-2">
                    <q-select
                      outlined
                      v-model="measure"
                      :options="options_measure"
                      label="kg/pound"
                    />
                  </div>
                  <div class="col-12 col-md-1"></div>
                  <div class="col-12 col-md-5">
                    <q-input outlined v-model="heartrate" label="Heart rate" />
                  </div>
                </div>
                <!-- row-2 -->
                <br />
                <h9>Mood</h9>
                <div class="row">
                  <div class="col-12 col-md-7">
                    <q-rating
                      v-model="model3"
                      max="7"
                      size="3em"
                      color="red"
                      color-selected="red-9"
                      icon="favorite_border"
                      icon-selected="favorite"
                      icon-half="favorite"
                      no-dimming
                    />
                  </div>
                  <div class="col-12 col-md-5">
                    <q-btn
                      color="grey"
                      no-caps
                      label="Reset Moods"
                      @click="resetModels"
                    />
                  </div>
                </div>
              </q-tab-panel>

              <q-tab-panel name="sportrecord">
                <div class="text-h6">WorkOut Record</div>
                <!-- row-1 -->
                <div class="row">
                  <div class="col-12 col-md-4"></div>
                  <div class="col-12 col-md-4"><q-date v-model="dataOfWork" /></div>
                  <div class="col-12 col-md-4">Workout Data : {{ dataOfWork }}</div>
                </div>
                <br />
                <br />
                <!-- row-2 -->
                <div class="row">
                  <div class="col-12 col-md-5">
                    <q-select
                      outlined
                      v-model="sporp_type"
                      :options="options_sport"
                      label="Sport Types"
                    />
                  </div>

                  <div class="col-12 col-md-1"></div>
                  <div class="col-12 col-md-3">
                    <q-input outlined v-model="duration" label="Duration (minutes)" />
                  </div>
                  <div class="col-12 col-md-3">
                    <q-input outlined v-model="times" label="Times" />
                  </div>
                </div>
              </q-tab-panel>

              <q-tab-panel name="foodintake">
                <div class="text-h6">Food intake</div>
                Lorem ipsum dolor sit amet consectetur adipisicing elit.
              </q-tab-panel>
            </q-tab-panels>
          </q-card>
        </div>
      </div>
      <div class="col-12 col-md-2"></div>
    </div>
  </q-page-container>
</template>
