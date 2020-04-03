import axios from "axios";
import { GET_ERRORS, GET_PROJECT, GET_PROJECTS, DELETE_PROJECT } from "./types";

export const createProject = (project, history) => async dispatch => {
  try {
    await axios.post("/api/project", project);
    history.push("/dashboard");
    //REmove errors from state
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const getProjects = () => async dispatch => {
  try {
    const res = await axios.get("/api/project/");
    dispatch({
      type: GET_PROJECTS,
      payload: res.data
    });
  } catch (err) {
    console.log(err);
  }
};

export const getProject = (id, history) => async dispatch => {
  try {
    const res = await axios.get(`/api/project/${id}`);
    dispatch({
      type: GET_PROJECT,
      payload: res.data
    });
  } catch (err) {
    // console.log(err);
    history.push("/dashboard");
  }
};

export const deleteProject = id => async dispatch => {
  try {
    if (
      window.confirm("Are you sure? This will delete the project and all the data related to it?")
    ) {
      await axios.delete(`/api/project/${id}`);
      dispatch({
        type: DELETE_PROJECT,
        payload: id
      });
    }
  } catch (err) {
    console.log(err);
  }
};
