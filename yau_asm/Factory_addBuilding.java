import java.util.Stack;

public class Factory_addBuilding implements CommandFactory {
    private Stack<Building> buildingList;
    private Building_Creator creator;
    private Building building;
    private String building_option;
    private int building_id;
    private Check_BuildingID check_BuildingID;

    public Factory_addBuilding(){
        buildingList = null;
    }

    public void set_building_option_and_id(String option, int building_id) {
        this.building_option = option;
        this.building_id = building_id;
    }

    @Override
    public void set_building_list(Stack<Building> buildingList) {
        // TODO Auto-generated method stub
        this.buildingList = buildingList;
        this.check_BuildingID = new Check_BuildingID(buildingList);
    }

    @Override
    public Command create() throws Exception {
        if(check_BuildingID.hasBuilding(building_id)){
            System.out.println("Building existed!");
            return null;
        }

        else {
            if ( building_option.matches("a") || building_option.matches("A") ) {
                creator = new Concrete_Creator_Apartment(building_id);
            } 
            else if (building_option.matches("h") || building_option.matches("H")) {
                creator = new Concrete_Creator_House(building_id);
            }
            this.building = creator.FactoryMethod();
            return new Command_addBuilding(buildingList, building);
        }
    }

}
