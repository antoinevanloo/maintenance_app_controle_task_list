package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final List<Project> projects = new ArrayList<>();
    private final BufferedReader in;
    private final PrintWriter out;

    private long lastId = 0;

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split("/", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                check(commandRest[1]);
                break;
            case "uncheck":
                uncheck(commandRest[1]);
                break;
            case "today":
                today();
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    private void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    private void today() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                if (task.getDeadline() != null && task.getDeadline().isDateToday()) {
                    out.printf("    %s%n", task.getDescription());
                }
            }
            out.println();
        }
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split("/", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            Project project = new Project(subcommand, new ArrayList<Task>());
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split("/", 3);
            Project projectToAdd = new Project();
            for(Project project : projects){
                if (project.getId().equals(projectTask[0])){
                    projectToAdd = project;
                }
            }
            if(projectTask.length > 2){
                addTask(projectToAdd, projectTask[1], projectTask[2]);
            } else {
                addTask(projectToAdd, projectTask[1], null);
            }
        }
    }

    private void addProject(Project projet) {
        projects.add(projet);
    }

    private void addTask(Project project, String description, String deadlineString) {
        List<Task> projectTasks = project.getTasks();
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        Task task = new Task(nextId(), description, false);
        projectTasks.add(task);

        if(deadlineString != null){
            Deadline deadline = new Deadline(nextId());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateDeadline = LocalDate.parse(deadlineString, formatter);
            deadline.setDate(dateDeadline);
            task.setDeadline(deadline);
        }
    }

    private void check(String idString) {
        setDone(idString, true);
    }

    private void uncheck(String idString) {
        setDone(idString, false);
    }

    private void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

    private long nextId() {
        return ++lastId;
    }
}
