<script setup>
import { ref, getCurrentInstance } from "vue";
import axios from "axios";
import { useQuasar } from "quasar";

const $q = useQuasar();
let pass = true;
const weight = ref(null);
const heartrate = ref(null);
const dataOfWork = ref("2019/02/01");
const duration = ref(null);
const calorioesburned = ref(0);
const model3 = ref(4.5);
const times = ref(null);
const tab = ref("bodyrecord");
const measure = ref("kg");
const sport_type = ref("running");
const $api = getCurrentInstance().appContext.config.globalProperties.$api;
const options_measure = ["kg", "pound"];
const errorpart = ref("");
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
const record = ref([]);
const Intensity_Level = ref("Low");
const Intensity_Level_options = ["Low", "Moderate", "High", "Very High"];

function resetModels() {
  model3.value = 3.0;
}

function validation() {
  pass = true;
  if (weight.value !== null && weight.value > 0) {
    console.log("weight correct");
  } else {
    pass = false;
    errorpart.value += "Weight Parts| \n";
  }
  if (heartrate.value !== null && heartrate.value > 0) {
    console.log("Heartrate correct");
  } else {
    pass = false;
    errorpart.value += "Heartrate Parts| \n";
  }

  if (duration.value !== null && duration.value > 0) {
    console.log("Duration correct");
  } else {
    pass = false;
    errorpart.value += "Duration Parts| \n";
  }

  if (times.value !== null && times.value > 0) {
    console.log("Times correct");
  } else {
    pass = false;
    errorpart.value += "Times Parts| \n";
  }

  if (pass) {
    $q.notify({
      icon: "done",
      position: "center",
      color: "positive",
      message: "Submitted",
    });
  } else {
    $q.notify({
      color: "negative",
      position: "center",
      message: "Errors:\n" + errorpart.value + "has Problem",
      actions: [
        {
          icon: "reset",
          color: "yellow",
          label: "reset",
          handler: () => {
            onReset();
          },
        },
      ],
    });
  }
}

function caloriesculcate() {
  console.log("calculate");
  switch (sport_type.value) {
    case "Running":
      calorioesburned.value = Number(duration.value) * Number(times.value) * 8;
      break;
    case "Swimming":
      calorioesburned.value = Number(duration.value) * Number(times.value) * 10;
      break;
    case "Weightlifting":
      calorioesburned.value = Number(duration.value) * Number(times.value) * 5;
      break;
    case "Yoga":
      calorioesburned.value = Number(duration.value) * Number(times.value) * 3;
      break;
    case "Basketball":
      calorioesburned.value = Number(duration.value) * Number(times.value) * 10;
      break;
    case "Hiking":
      calorioesburned.value = Number(duration.value) * Number(times.value) * 7;
      break;
    case "Jump Rope":
      calorioesburned.value = Number(duration.value) * Number(times.value) * 12;
      break;
    case "Cycling":
      calorioesburned.value = Number(duration.value) * Number(times.value) * 8;
      break;
  }
  calorioesburned.value = Number(duration.value) * Number(times.value) * 8;
  console.log(calorioesburned.value);
}

function onSubmit() {
  validation();
  caloriesculcate();
  record.value = {
    weight: weight.value,
    duration: duration.value,
    times: times.value,
    mood: model3.value,
    calorioesburned: calorioesburned,
  };
  console.log(record.value);
  if (pass) {
    $api
      .post("http://localhost:8080/api/record", record)
      .then((response) => {
        console.log("Data sent successfully:", response.data);
        alert("Data sent Successfully");
      })
      .catch((error) => {
        console.error("Error sending data:", error);
        alert("Error sending data");
      });
  } else {
    console.log("have't send!");
  }
}
function onReset() {
  times.value = null;
  weight.value = null;
  duration.value = null;
  heartrate.value = null;
  measure.value = "kg";
  model3.value = 4.5;
}
</script>

<template>
  <q-page-container>
    <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
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
              </q-tabs>

              <q-separator />

              <q-tab-panels v-model="tab" animated>
                <q-tab-panel name="bodyrecord">
                  <br />
                  <div class="text-h6">Body Record</div>

                  <!-- row-1 -->
                  <div class="row">
                    <div class="col-12 col-md-3">
                      <q-input
                        outlined
                        v-model="weight"
                        label="Weight"
                        type="number"
                        :rules="[
                          (val) => val !== null || 'Please fill your weight',
                          (val) => val > 0 || 'Please type valid number',
                        ]"
                      />
                    </div>
                    <div class="col-12 col-md-2">
                      <q-select
                        outlined
                        v-model="measure"
                        :options="options_measure"
                        label="Intensity Level"
                      />
                    </div>
                    <div class="col-12 col-md-1"></div>
                    <div class="col-12 col-md-5">
                      <q-input
                        outlined
                        v-model="heartrate"
                        label="Heart rate"
                        type="number"
                        :rules="[
                          (val) => val !== null || 'please fill your heartrate ',
                          (val) => val > 0 || 'Please enter valid number',
                        ]"
                      />
                    </div>
                  </div>
                  <!-- row-2 -->
                  <br />
                  <div class="text-h6">Mood</div>
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
                        v-model="sport_type"
                        :options="options_sport"
                        label="Sport Types"
                      />
                    </div>

                    <div class="col-12 col-md-1"></div>
                    <div class="col-12 col-md-3">
                      <q-input
                        outlined
                        v-model="duration"
                        label="Duration (minutes)"
                        type="number"
                        :rules="[
                          (val) => val !== null || 'Please fill your weight',
                          (val) => val > 0 || 'Please type valid number',
                        ]"
                      />
                    </div>
                    <div class="col-12 col-md-3">
                      <q-input
                        outlined
                        v-model.number="times"
                        label="Times"
                        type="number"
                        :rules="[
                          (val) => val !== null || 'please fill your Times ',
                          (val) => val > 0 || 'Please enter valid number',
                        ]"
                      />
                    </div>
                  </div>
                  <br />

                  <!-- row-3 -->
                  <div class="row">
                    <div class="col-12 col-md-5">
                      <q-select
                        outlined
                        v-model="Intensity_Level"
                        :options="Intensity_Level_options"
                        label="Intensity Level"
                      />
                    </div>
                    <div class="col-12 col-md-1"></div>
                    <div class="col-12 col-md-3"></div>
                    <div class="col-12 col-md-3"></div>
                  </div>
                  <br />
                </q-tab-panel>
              </q-tab-panels>
              <q-separator />
              <br />
              <br />
              <div class="row">
                <div class="col-12 col-md-5"></div>
                <div class="col-12 col-md-3">
                  <q-btn push color="primary" label="Sumbit" type="submit" />
                </div>
                <div class="col-12 col-md-4">
                  <q-btn push color="primary" label="Reset" type="reset" />
                </div>
              </div>
              <br />
            </q-card>
          </div>
        </div>
      </div>
    </q-form>
  </q-page-container>
</template>
