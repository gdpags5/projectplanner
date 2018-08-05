Project Planner version 1.0

Below is the test you will be accomplishing for one of our clients: Vlocity (you can visit their website: https://vlocity.com/, for more details about our client)

1. We need to calculate calendar schedules for project plans
2. Each project plan consists of tasks. Every task has a certain duration. 
3. A task can depend on zero or more other tasks. If a task depends on some other tasks, it can only be started after these tasks are completed
4. So, for a set of tasks (with durations and dependencies), the solution for the challenge should generate a schedule, i.e. assign Start and End Dates for every task
5. It is ok to have a console app
6. The solution should be pushed to GitHub


Technologies used:

1. Primefaces (JavaServer Faces 2) - for web user interface
2. Eclipselink (Java Persistence API 2) - for mapping entity objects to database's tables
3. HyperSQL Database (HSLQDB) - for the backend.  I used an in-memory database for this demo


Issue(s) that I ran off during the development (in which I have almost ran out of time because of my busy schedules):

1. I did not worked on the filtering of tasks based on the selected project in the task form.
2. Dates not correctly saved based on what you have selected (e.g., selected Aug 7, 2018 but it will save in the backend as Aug 6, 2018).
3. Did not check the other validation messages that might catch during the form fill-ups.
