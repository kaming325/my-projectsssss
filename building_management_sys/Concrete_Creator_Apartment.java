import java.util.Scanner;

public class Concrete_Creator_Apartment extends Building_Creator {
    private Scanner sc = new Scanner(System.in);
    private double[][] rooms;
    private int building_id = 0;
    private int room_no = 0;
    private double monthlyRental = 0;
    private String supportStaff = null;

    public Concrete_Creator_Apartment(int building_id) {
        this.building_id = building_id;
    }

    @Override
    public Building FactoryMethod() {
        // TODO Auto-generated method stub

        // System.out.print("Building No.: ");
        // building_id = sc.nextInt();
        System.out.print("Monthly Rental: ");
        monthlyRental = sc.nextDouble();
        sc.nextLine();
        System.out.print("Support Staff: ");
        supportStaff = sc.nextLine();
        do {
            System.out.print("Number of rooms: ");
            room_no = sc.nextInt();
        }  while (room_no < 1);
        
        //setting rooms
        this.rooms = new double[room_no][2];
        for (int i = 0; i < room_no; i++) {
            System.out.println("Room No. "+ (i+1) +" :");
            System.out.print("Length: ");
            double length = sc.nextDouble();
            System.out.print("Width:");
            double width = sc.nextDouble();
            double[] newRoom = {length, width};
            rooms[i] = newRoom;
        }
        //print_result();
        Apartment a = new Apartment(building_id, room_no, monthlyRental, supportStaff);
        // a.setMonthlyRental(monthlyRental);
        // a.setSupportStaff(supportStaff);
        for (int i = 0; i < rooms.length; i++) {
            a.addRoom(rooms[i][0], rooms[i][1]);
        }
        return a;
    }

    
    
}
