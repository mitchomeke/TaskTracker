import java.time.LocalDateTime;

public class TaskClass implements Task{
    String description;
    int id;
    String Status;
    String createdAt;
    String updatedAt;

    public TaskClass(String description,int id, LocalDateTime createdAt, LocalDateTime updatedAt, String status){
        this.description = description;
        this.id = id;
        this.createdAt = createdAt.toString();
        this.updatedAt = updatedAt.toString();
        this.Status = status;
    }
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getStatus() {
        return Status;
    }

    @Override
    public String getcreatedAt() {
        return createdAt;
    }

    @Override
    public String getupdatedAt() {
        return updatedAt;
    }

    @Override
    public void setCreatedAt(LocalDateTime time) {
        createdAt = time.toString();
    }

    @Override
    public void setUpdatedAt(LocalDateTime time){
        updatedAt = time.toString();
    }

    @Override
    public void setId(int id){
        this.id = id;

    }
    @Override
    public void setStatus(String status){
        this.Status = status;
    }
    @Override
    public void setDescription(String description){
        this.description = description;
    }
}
