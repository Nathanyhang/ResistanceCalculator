import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface InputHandler {

public String getLoadType();
public int getTotalLoad(String loadType);
public Map<String, Integer> getLoadInventory(String loadType);
public List<Double> getLoadValues(int totalLoad,String loadType);
public String getConnection(List<Load> loadList, List<Circuit> circuitList);

}
