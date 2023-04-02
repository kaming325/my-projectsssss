import java.util.Stack;

public class Command_displayBuiding implements Command {
    private Stack<Building> buildingList;
    private String display_option;

    public Command_displayBuiding(Stack<Building> buildingList, String option) {
        this.buildingList = buildingList;
        this.display_option = option;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        if (buildingList.size()==0) {
            System.out.println("No building yet.");
            System.out.println();
        }
        else if (display_option.matches("-?\\d+(\\.\\d+)?")) {

            int building_id = Integer.parseInt(display_option);
            // check if building has the id
            for (int i = 0; i < buildingList.size(); i++) {
                if (  (i == buildingList.size()-1 & buildingList.get(i).getId() != building_id) ) {
                    System.out.println("No such building");
                    System.out.println();
                    break;
                }

                if (buildingList.get(i).getId() == building_id){
                    buildingList.get(i).printBuilding_detail();
                    buildingList.get(i).printRooms();
                    break;
                } 
                
            }

        } 
        
        else if (display_option.matches("\\*")) {

            for (int i = 0; i < buildingList.size(); i++) {
                buildingList.get(i).printBuilding();
            }

        }


    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        
        return super.toString();
    }
    
}
