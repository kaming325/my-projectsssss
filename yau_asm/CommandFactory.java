import java.util.Stack;

public interface CommandFactory {
    public Command create() throws Exception;
    public void set_building_list(Stack<Building> buildingList);
}
