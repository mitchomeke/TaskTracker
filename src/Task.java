import java.time.LocalDateTime;

public interface Task {
    String getDescription();
    int getID();
    String getStatus();
    String getcreatedAt();
    String getupdatedAt();
    void setCreatedAt(LocalDateTime time);
    void setUpdatedAt(LocalDateTime time);
    void setId(int id);
    void setStatus(String status);
    void setDescription(String description);

}
