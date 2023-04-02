public class Command_addRoom implements Command {
    private Building building;
    private double[] newRoom;
    private int room_list_index;

    public Command_addRoom(Building building, double[] newRoom) {
        this.building = building;
        this.newRoom = newRoom;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        this.building.addRoom(newRoom[0], newRoom[1]);
        this.room_list_index = building.getRoomQty()-1;
        System.out.println("Updated Building:");
        this.building.printBuilding_detail();
        this.building.printRooms();
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        this.building.deleteRoom(room_list_index);
        this.building.printBuilding_detail();
        this.building.printRooms();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        
        return "Add Add Room: Building No. "+ this.building.getId() +","+
            " Room No. "+room_list_index+", Length: " + newRoom[0] + ", Width: " + newRoom[1] ;
    }
    
}
