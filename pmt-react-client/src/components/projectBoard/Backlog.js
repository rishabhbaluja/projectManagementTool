import React, { Component } from "react";
import ProjectTask from "./projectTasks/ProjectTask";

class Backlog extends Component {
  render() {
    const { projectTasks } = this.props;

    const tasks = projectTasks.map((projectTask) => (
      <ProjectTask id={projectTask.id} projectTask={projectTask} />
    ));

    let toDo = [];
    let inProgress = [];
    let done = [];

    for (let i = 0; i < tasks.length; i++) {
      if (tasks[i].props.projectTask.status === "TO_DO") {
        toDo.push(tasks[i]);
      } else if (tasks[i].props.projectTask.status === "IN_PROGRESS") {
        inProgress.push(tasks[i]);
      } else if (tasks[i].props.projectTask.status === "DONE") {
        done.push(tasks[i]);
      }
    }

    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>

            {toDo}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {inProgress}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {done}
          </div>
        </div>
      </div>
    );
  }
}
export default Backlog;
