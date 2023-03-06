import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Factory_modifyBuiding implements CommandFactory {
    private Stack<Building> buildingList;
    private Scanner sc = new Scanner(System.in);
    private Building building = null;
    private ArrayList modify_info = null;
    private Check_BuildingID check_BuildingID;

    public Factory_modifyBuiding(){
        buildingList = null;
    }

    @Override
    public void set_building_list(Stack<Building> buildingList) {
        // TODO Auto-generated method stub
        this.buildingList = buildingList;
        this.check_BuildingID = new Check_BuildingID(buildingList);
    }

    @Override
    public Command create() throws Exception {
        // TODO Auto-generated method stub

        boolean boo = true;
        System.out.print("Building No.: ");
        String building_id_enter = sc.nextLine();
        int building_id = 0;

        //check if the input is number
        while (boo) {
            if ( building_id_enter.matches("-?\\d+(\\.\\d+)?") ) {
                building_id = Integer.parseInt(building_id_enter);
                boo = false;
            } else {
                System.out.println("*****");
                System.out.println("Typo!");
                System.out.println("*****");
                System.out.print("Building No.: ");
                building_id_enter = sc.nextLine();
            }
        } //end while loop

        if (buildingList.size()==0) {
            System.out.println("No building yet.");
            System.out.println();
        }
        else {
            if (!check_BuildingID.hasBuilding(building_id)) {
                System.out.println("No such building");
                System.out.println();
            } else {
                this.building = check_BuildingID.getBuilding_ifHasBuilding();
                modify_info = new ArrayList<>();
            }


            if (this.building!=null) {
                modify_operate();
                return new Command_modifyBuiding(buildingList, building, modify_info);
            }
        } 

        return null;
    }

    private void modify_operate() {
        this.building.printBuilding();

        if (this.building instanceof Apartment) {
            System.out.print("Modify Monthly Rental: ");
            double changed_rental = sc.nextDouble();
            
            System.out.print("Modify Support Staff: ");
            sc.nextLine();
            String changed_staff = sc.nextLine();

            this.modify_info.add(changed_rental);
            this.modify_info.add(changed_staff);

        } else if (this.building instanceof House) {
            System.out.print("No. of Floors: ");
            int floor_no = sc.nextInt();
            this.modify_info.add(floor_no);

            sc.nextLine();
        }
    }

    
    
}
