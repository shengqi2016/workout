// Quick search profile
export function getInformation(id, $api, url) {
  return $api
    .post(url, { id })
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      console.error("error", error);
      return null;
    });
}
export function validation_username(username, $api, url) {
  return $api
    .post(url, { username })
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      console.error("error", error);
      return null;
    });
}
export function signUp_account(member, $api, url) {
  console.log("2");
  return $api
    .post(url, member)
    .then((response) => {
      console.log("signup_account:" + response.data);
      return response.data;
    })
    .catch((error) => {
      console.error("error", error);
      return null;
    });
}

export function add_profile(profile, $api, url) {
  console.log("3");
  return $api
    .post(url, profile)
    .then((response) => {
      console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      console.error("error", error);
      return null;
    });
}
