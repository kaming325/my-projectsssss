import java.util.ArrayList;

public class House extends Building {
    private int noOfFloors;


    public House(int id, int noOfRooms, int noOfFloors) {
        super(id, noOfRooms);
        this.noOfFloors = noOfFloors;
        //TODO Auto-generated constructor stub
    }

    public void setFloors(int noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public int getFloors() {
        return noOfFloors;
    }



    @Override
    public void modifyBuilding(ArrayList modify_info) {
        // TODO Auto-generated method stub
        setFloors( (int) modify_info.get(0) );
    }

    @Override
    public void printBuilding() {
        // TODO Auto-generated method stub
        //System.out.println("Building No: " + getId() + ", No. of Floors: " + getFloors() );
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Building No: " + getId() + ", No. of Floors: " + getFloors();
    }

    @Override
    public void print_add_result() {
        // TODO Auto-generated method stub
        System.out.println("New Building Added:");
        printBuilding_detail();
        printRooms();
    }

    @Override
    public void printBuilding_detail() {
        // TODO Auto-generated method stub
        System.out.println("Building No: " + getId());
        System.out.println("No of Floors: " + getFloors());
    }
    
}
