import java.util.ArrayList;

public class Command_modifyRoom implements Command {
    private Building building; 
    private int room_index;
    private double[] newRoom;
    // private ArrayList old_info;
    // private double oldRoom_Length;
    // private double oldRoom_Width;
    private Memento memento;

    public Command_modifyRoom(Building building, int room_index, double[] newRoom) {
        this.building = building;
        this.room_index = room_index;
        this.newRoom = newRoom;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        if (room_index <= this.building.getRooms().size() && room_index >= 0 ) {
            this.building.modifyRoom(room_index, newRoom[0], newRoom[1]);
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
        ArrayList old_info = memento.get_old_info();
        int room_index = (int) old_info.get(0);
        double oldRoom_Length = (double) old_info.get(1);
        double oldRoom_Width = (double) old_info.get(2);
        // double oldRoom_Length = this.building.getRooms().get(room_index).getLength();
        // double oldRoom_Width = this.building.getRooms().get(room_index).getWidth();

        this.building.modifyRoom(room_index, oldRoom_Length, oldRoom_Width);
        System.out.println("Updated Building:");
        this.building.printBuilding_detail();
        this.building.printRooms();
    }

    public void get_memento_from_caretaker(Memento memento) {
        this.memento = memento;
    }

    public Memento saveToMemento(){
        ArrayList old_info = new ArrayList<>();
        double oldRoom_Length = this.building.getRooms().get(room_index).getLength();
        double oldRoom_Width = this.building.getRooms().get(room_index).getWidth();
        old_info.add(room_index);
        old_info.add(oldRoom_Length);
        old_info.add(oldRoom_Width);
        return new Memento(old_info);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        
        return "Add Add Room: Building No. "+ this.building.getId() +","+
        " Room No. "+room_index+", Length: " + newRoom[0] + ", Width: " + newRoom[1];
    }
    
}
