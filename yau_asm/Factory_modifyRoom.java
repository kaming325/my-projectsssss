import java.util.Scanner;
import java.util.Stack;

public class Factory_modifyRoom implements CommandFactory {
    private Stack<Building> buildingList;
    private Building building;
    private Scanner sc = new Scanner(System.in);

    public Factory_modifyRoom(){
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
        room_index -= 1;
        if (room_index < this.building.getRooms().size() && room_index > -1 ) {
            System.out.print("Length: ");
            double length = sc.nextDouble();
            System.out.print("Width:");
            double width = sc.nextDouble();
            double[] newRoom = {length, width};
            return new Command_modifyRoom(this.building, room_index, newRoom);
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
