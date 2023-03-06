public class Command_deleteRoom implements Command {
    private Building building;
    private int room_index;
    private double oldRoom_Length;
    private double oldRoom_Width;

    public Command_deleteRoom(Building building, int room_index) {
        this.building = building;
        this.room_index = room_index;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        if (room_index < this.building.getRooms().size() && room_index >= 0 ) {
            this.oldRoom_Length = this.building.getRooms().get(this.room_index).getLength();
            this.oldRoom_Width = this.building.getRooms().get(this.room_index).getWidth();
            this.building.deleteRoom(room_index);
            System.out.println("Updated Building:");
            this.building.printBuilding_detail();
            this.building.printRooms();
        } else{
            System.out.println("No such Room");
            System.out.println();
        }
        
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        this.building.addRoom(oldRoom_Length, oldRoom_Width);
        System.out.println("Updated Building:");
        this.building.printBuilding_detail();
        this.building.printRooms();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        
        return "Delete Room: Building No. "+this.building.getId()+", "+
            "Room No. "+room_index+", Length: "+this.oldRoom_Length+", Width: " + this.oldRoom_Width;
    }
    
}
