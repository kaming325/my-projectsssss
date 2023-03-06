import java.util.Scanner;
import java.util.Stack;

public class Factory_addRoom implements CommandFactory {
    private Stack<Building> buildingList;
    private Building building;
    private Scanner sc = new Scanner(System.in);

    public Factory_addRoom(){
        buildingList = null;
    }

    public void set_building(Building building){
        this.building = building;
    }

    @Override
    public void set_building_list(Stack<Building> buildingList) {
        // TODO Auto-generated method stub
        this.buildingList = buildingList;
    }

    @Override
    public Command create() throws Exception {
        // TODO Auto-generated method stub
        System.out.print("Length: ");
        double length = sc.nextDouble();
        System.out.print("Width:");
        double width = sc.nextDouble();
        double[] newRoom = {length, width};
        return new Command_addRoom(this.building, newRoom);
    }

    
    
}
