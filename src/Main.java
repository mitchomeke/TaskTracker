import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String COULD_NOT_EXPORT = "Data could not export\n";
    static String TASK_ADDED = "Task added successfully (ID: %d)\n";
    static String TASK_UPDATED = "Task Updated successfully\n";
    static String TASK_NOT_FOUND = "Task Not Found\n";
    static String TASK_DELETED = "Task Deleted Successfully\n";
    static String TASK_NOT_DELETED = "Task Not Deleted\n";
    static String TASK_MARKED = "Task Marked Successfully\n";
    static String STATUS = "status";
    static String IN_PROGRESS = "in-progress";
    static String DONE = "done";
    static String QUIT = "quit";

    public static void main(String[] args) throws IOException {
        SystemClass system = new SystemClass();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        do {
            input = reader.readLine().trim();
            String[] commandArgs = input.split(" ",3);
            switch (commandArgs[0].toLowerCase()) {
                case "add" -> {
                    if (commandArgs.length > 1){
                        AddTask(commandArgs[1], system);
                    }
                }
                case "mark" -> {
                    if (commandArgs.length > 2){
                        MarkTask(commandArgs[1],commandArgs[2], system);
                    }
                }
                case "update" -> {
                    if (commandArgs.length > 2){
                        UpdateTask(commandArgs[1], commandArgs[2], system);
                    }
                }
                case "delete" -> {
                    if (commandArgs.length > 1){
                        DeleteTask(commandArgs[1], system);
                    }
                }
                case "list_all" -> ListAll(system);
                case "list_done" -> ListDone(system);
                case "list_in_progress" -> ListInProgress(system);
            }
        } while (!input.equalsIgnoreCase(QUIT));
    }

    private static void ListInProgress(SystemClass system) {
        TaskItClass allTasks = system.listAllTasks();
        while (allTasks.hasNext()){
            JsonNode task = allTasks.next();
            if (task.path(STATUS).asText().equals(IN_PROGRESS)){
                System.out.printf(task.toString());
            }
        }
    }

    private static void ListDone(SystemClass system) {
        TaskItClass allTasks = system.listAllTasks();
        while (allTasks.hasNext()){
            JsonNode task = allTasks.next();
            if (task.path(STATUS).asText().equals(DONE)){
                System.out.printf(task.toString());
            }
        }
    }

    private static void ListAll(SystemClass system) {
        TaskItClass allTasks = system.listAllTasks();
        while (allTasks.hasNext()){
            JsonNode task = allTasks.next();
            System.out.printf(task.toString());
        }
    }

    private static void DeleteTask(String arg, SystemClass system) throws IOException {
        if (system.deleteTask(Integer.parseInt(arg))){
            System.out.printf(TASK_DELETED);
        }
        else {
            System.out.printf(TASK_NOT_DELETED);
        }
    }

    private static void UpdateTask(String arg, String s, SystemClass system) throws IOException {
        if (system.updateTask(arg,Integer.parseInt(s))){
            System.out.printf(TASK_UPDATED);
        }
        else{
            System.out.printf(TASK_NOT_FOUND);
        }
    }

    private static void MarkTask(String arg, String Status,SystemClass system) throws IOException {
        if (system.markTask(Integer.parseInt(arg),Status)){
            System.out.printf(TASK_MARKED);
        }
        else {
            System.out.printf(TASK_NOT_FOUND);
        }
    }

    private static void AddTask(String arg, SystemClass system) throws IOException {
        if (system.addTask(arg)){
            System.out.printf(TASK_ADDED,system.getId(arg));
        }
        else {
            System.out.println(COULD_NOT_EXPORT);
        }

    }
}