import java.util.ArrayList;

/**
 * Building
 */
public abstract class Building {
    private int id;
    private ArrayList<Room> rooms;

    public Building(int id, int noOfRooms){
        this.id = id;
        rooms = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public Room addRoom(double length, double width) {
        rooms.add(new Room(length, width));
        return  rooms.get(rooms.size()-1 )  ;
    }
    
    public void modifyRoom(int roomNo, double length, double width) {
        rooms.get(roomNo).setLength(length);
        rooms.get(roomNo).setWidth(width);
    }

    public void deleteRoom(int roomNo){
        rooms.remove(roomNo);
    }

    public void printRooms(){
        for (int i = 0; i < getRoomQty(); i++) {
            System.out.println("Room No.: "+ (i+1) + ", Length: " 
                    + getRooms().get(i).getLength() + ", Width: " + getRooms().get(i).getWidth() );
        }
        System.out.println();
        
        
    }

    public int getRoomQty(){
        return rooms.size();
    }

    public abstract void modifyBuilding(ArrayList modify_info);
    public abstract void printBuilding();
    public abstract void printBuilding_detail();
    public abstract void print_add_result();
    
}