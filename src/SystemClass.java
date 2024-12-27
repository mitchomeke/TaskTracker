import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Random;

public class SystemClass implements SystemTask {
    File file;
    ObjectMapper mapper;
    ObjectNode rootNode;
    int size;
    public SystemClass() throws IOException {
        file = new File("user.json");
        mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(file);
        if (node.isObject()){
            rootNode = (ObjectNode) node;
        }
        if (rootNode == null){
            rootNode = mapper.createObjectNode();
            rootNode.putArray("tasks");
            mapper.writeValue(file,rootNode);
        }
    }
    @Override
    public boolean addTask(String description) throws IOException {
        if (canAddTask(description)){
            size++;
                ArrayNode tasksNode = (ArrayNode) rootNode.withArray("tasks");
                TaskClass task =  new TaskClass(description,size,LocalDateTime.now(),LocalDateTime.now(),"todo");
                tasksNode.add(mapper.valueToTree(task));
                mapper.writeValue(file,rootNode);
                return true;
        }
        return false;
    }
    private boolean canAddTask(String description) throws IOException {
        if (file.exists()){
            ArrayNode taskNode = (ArrayNode) rootNode.path("tasks");
            for (JsonNode tasks :taskNode){
                if (tasks.path("description").asText().equals(description)){
                 return false;
                 }
            }
        }
        return true;
    }

    @Override
    public boolean markTask(int id,String Status) throws IOException {
        ObjectNode node = getNode(id);
        if (node == null){
            return false;
        }
        node.put("status",Status);
        mapper.writeValue(file,rootNode);
        return true;
    }
    private ObjectNode getNode(int id) throws IOException {
        ArrayNode tasks = rootNode.withArray("tasks");
        ObjectNode objectNode = null;
        for (JsonNode task: tasks) {
            if (task.path("id").asInt() == id) {
                objectNode = (ObjectNode) task;
            }
        }
        return objectNode;
    }
    @Override
    public TaskItClass listAllTasks(){
        ArrayNode tasks = (ArrayNode) rootNode.withArray("tasks");
        return new TaskItClass(tasks,tasks.size());
    }


    @Override
    public boolean updateTask(String desc,int id) throws IOException {
        ObjectNode node = getNode(id);
        if (node == null){
            return false;
        }
        node.put("description",desc);
        node.put("updatedAt", String.valueOf(LocalDateTime.now()));
        mapper.writeValue(file,rootNode);
        return true;
    }
    @Override
    public boolean deleteTask(int arg) throws IOException {
        ArrayNode tasks = (ArrayNode) rootNode.withArray("tasks");
        for(int i = 0; i < tasks.size();i++){
            JsonNode task = tasks.get(i);
            if (task.path("id").asInt() == arg){
                tasks.remove(i);
                mapper.writeValue(file,rootNode);
                return true;
            }
        }
        return false;
    }
    @Override
    public int getId(String description){
        ArrayNode tasks = rootNode.withArray("tasks");
        int id = 0;
        for (int i = 0; i < tasks.size();i++){
            JsonNode task = tasks.get(i);
            if (task.path("description").asText().equals(description)){
                id = task.path("id").asInt();
                break;
            }
        }
        return id;
    }
}
