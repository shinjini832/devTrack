import axios from "axios";

const API = axios.create({
  baseURL: "https://devtrack-aw6q.onrender.com/api",
});

export default API;