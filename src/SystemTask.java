import java.io.IOException;

public interface SystemTask {
    boolean addTask(String description) throws IOException;
    boolean markTask(int id,String Status) throws IOException;
    TaskIterator listAllTasks();
    boolean updateTask(String desc,int id) throws IOException;
    boolean deleteTask(int arg) throws IOException;
    int getId(String description);
}
