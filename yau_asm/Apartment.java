import java.util.ArrayList;

public class Apartment extends Building {
    private double monthlyRental;
    private String supportStaff;

    public Apartment(int id, int noOfRooms, double monthlyRental, String supportStaff) {
        super(id, noOfRooms);
        this.monthlyRental = monthlyRental;
        this.supportStaff = supportStaff;
    }

    public void setMonthlyRental(double monthlyRental) {
        this.monthlyRental = monthlyRental;
    }
    public double getMonthlyRental() {
        return monthlyRental;
    }
    public void setSupportStaff(String supportStaff) {
        this.supportStaff = supportStaff;
    }
    public String getSupportStaff() {
        return supportStaff;
    }
    


    @Override
    public void modifyBuilding(ArrayList modify_info) {
        // TODO Auto-generated method stub
        setMonthlyRental( (double) modify_info.get(0) );
        setSupportStaff( (String) modify_info.get(1) );
    }

    @Override
    public void printBuilding() {
        // TODO Auto-generated method stub
        //System.out.println("Building No: " + getId() + ", Support Staff: " + getSupportStaff() +  ", Monthly Rental: " + monthlyRental );
        System.out.println(this.toString());
    }

    public String toString() {
        return "Building No: " + getId() + ", Support Staff: " + getSupportStaff() +  ", Monthly Rental: " + monthlyRental;
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
        System.out.println("Support Staff: " + getSupportStaff());
        System.out.println("Monthly Rental: " + monthlyRental);
        
    }
}
