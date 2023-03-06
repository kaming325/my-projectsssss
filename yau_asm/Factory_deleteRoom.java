import java.util.Scanner;
import java.util.Stack;

public class Factory_deleteRoom implements CommandFactory {
    private Scanner sc = new Scanner(System.in);
    private Stack<Building> buildingList;
    private Building building;

    public Factory_deleteRoom(){
        buildingList = null;
    }

    public void set_building(Building building){
        this.building = building;
    }

    @Override
    public Command create() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("Room No.: ");
        int room_index = sc.nextInt();
        if (room_index < this.building.getRooms().size() && room_index >= 0 ) {
            return new Command_deleteRoom(building, room_index-1);
        } else{
            System.out.println("No such Room");
            System.out.println();
            return null;
        }
    }

    @Override
    public void set_building_list(Stack<Building> buildingList) {
        // TODO Auto-generated method stub
        this.buildingList = buildingList;
    }
    
}
