import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class TaskItClass implements TaskIterator{
    ArrayNode tasks;
    private final int size;
    private int currentIndex;
    public TaskItClass(ArrayNode arrayNode, int size){
        this.tasks = arrayNode;
        this.size = size;
        currentIndex = 0;
    }
    @Override
    public boolean hasNext() {
        return currentIndex < size;
    }
    @Override
    public JsonNode next() {
        return tasks.get(currentIndex++);
    }
}
