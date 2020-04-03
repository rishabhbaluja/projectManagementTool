import React from "react";
import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../node_modules/jquery/dist/jquery.min";
import "../node_modules/bootstrap/dist/js/bootstrap.min";
import "../node_modules/font-awesome/css/font-awesome.min.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./components/Project/UpdateProject";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <Header />
        <Switch>
          <Route exact path="/dashboard" component={Dashboard}></Route>
          <Route exact path="/addProject" component={AddProject}></Route>
          <Route exact path="/updateProject/:id" component={UpdateProject}></Route>
        </Switch>
      </Router>
    </Provider>
  );
}

export default App;
