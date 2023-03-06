import java.util.Stack;

public class Command_addBuilding implements Command {
    private Building building;
    private Stack<Building> buildingList;

    public Command_addBuilding(Stack<Building> buildingList, Building building){
        this.buildingList = buildingList;
        this.building = building;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        building.print_add_result();
        buildingList.add(building);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        if (this.building!=null) {
            this.buildingList.remove(building);
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Add Building : " + this.building.toString();
    }

}