import java.util.ArrayList;
import java.util.Stack;

public class Command_modifyBuiding implements Command {
    private Stack<Building> buildingList;
    private Building building;
    private ArrayList modify_info;//, old_info;
    private Memento memento;

    public Command_modifyBuiding(Stack<Building> buildingList, Building building, ArrayList modify_info) {
        this.buildingList = buildingList;
        this.building = building;
        this.modify_info = modify_info;
        // this.old_info = new ArrayList<>();

        // if (this.building instanceof Apartment) {
        //     this.old_info.add(  ((Apartment) this.building).getMonthlyRental()   );
        //     this.old_info.add(  ((Apartment) this.building).getSupportStaff()   );

        // } else if (this.building instanceof House) {
            
        //     this.old_info.add(( (House) this.building).getFloors());

        // }
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        this.building.modifyBuilding(modify_info);
        System.out.println();
        System.out.println("Building is modified:");
        this.building.printBuilding();
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        //
        ArrayList old_info = memento.get_old_info();
        this.building.modifyBuilding(old_info);
        System.out.println();
        System.out.println("Building is modified:");
        this.building.printBuilding();
        //


    }

    public void get_memento_from_caretaker(Memento memento) {
        this.memento = memento;
    }

    public Memento saveToMemento(){
        ArrayList old_info = new ArrayList<>();
        if (this.building instanceof Apartment) {
            old_info.add(  ((Apartment) this.building).getMonthlyRental()   );
            old_info.add(  ((Apartment) this.building).getSupportStaff()   );

        } else if (this.building instanceof House) {
            
            old_info.add(( (House) this.building).getFloors());

        }
        return new Memento(old_info);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Modify Building : " + this.building.toString();
    }
    
}
