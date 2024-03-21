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
