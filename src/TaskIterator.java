import com.fasterxml.jackson.databind.JsonNode;

public interface TaskIterator {
    boolean hasNext();
    JsonNode next();

}
