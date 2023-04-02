import java.util.Stack;

public class Check_BuildingID {
    private Stack<Building> buildingList;
    private Building building;

    public Check_BuildingID(Stack<Building> buildingList){
        this.buildingList = buildingList;
    }

    public boolean hasBuilding(int building_id){
        for (int i = 0; i < buildingList.size(); i++) {
            if (  (i == buildingList.size()-1 & buildingList.get(i).getId() != building_id) ) {
                return false;
            }

            if (buildingList.get(i).getId() == building_id){
                this.building = buildingList.get(i);
                return true;
            }
        }

        return false;
    }

    public Building getBuilding_ifHasBuilding(){
        return this.building;
    }
}
