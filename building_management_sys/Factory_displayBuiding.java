import java.util.Scanner;
import java.util.Stack;

public class Factory_displayBuiding implements CommandFactory {
    private Scanner sc = new Scanner(System.in);
    private Stack<Building> buildingList;

    public Factory_displayBuiding(){
        buildingList = null;
    }

    

    @Override
    public Command create() throws Exception {
        // TODO Auto-generated method stub
        boolean boo = true;
        String display_method = null;
        System.out.println("Enter Building No.(* to display all):");
        display_method = sc.nextLine();
        while (boo) {
            // sc.nextLine();
            if ( display_method.matches("-?\\d+(\\.\\d+)?") ||  display_method.matches("\\*") ) {
                boo = false;
                
            } else {
                System.out.println("*****");
                System.out.println("Typo!");
                System.out.println("*****");
                System.out.println("Enter Building No.(* to display all):");
                display_method = sc.nextLine();
            }
            
        }
        
        return new Command_displayBuiding(buildingList, display_method);
    }

    @Override
    public void set_building_list(Stack<Building> buildingList) {
        // TODO Auto-generated method stub
        this.buildingList = buildingList;
    }
    
}
