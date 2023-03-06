import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static Check_BuildingID check_BuildingID;
    private static Scanner sc;
    private static String input = null;
    private static Stack<Command> commandHistory_Stack = null;
    private static Stack<Command> command_redo_list = null;
    private static Stack<Building> buildingList = null;
    private static String[] command_factories = {"Factory_addBuilding", "Factory_addRoom", "Factory_deleteRoom",
                                             "Factory_displayBuiding", "Factory_modifyBuiding", "Factory_modifyRoom"};
    private static CommandFactory[] com_facs = null;
    private static Caretaker caretaker;

    public static void main(String[] args) throws Exception {
        sc = new Scanner(System.in);
        caretaker = new Caretaker();
        buildingList = new Stack<Building>();
        commandHistory_Stack = new Stack<Command>();
        command_redo_list = new Stack<Command>();
        check_BuildingID = new Check_BuildingID(buildingList);

        com_facs = new CommandFactory[command_factories.length];
        try {
            for (int i = 0; i < com_facs.length; i++) {
                com_facs[i] = (CommandFactory) Class.forName(command_factories[i]).newInstance();
                com_facs[i].set_building_list(buildingList);
            }
            

        } catch(InstantiationException | IllegalAccessException | ClassNotFoundException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        operate();
    }
        
        

    private static void operate() throws Exception {
        Command command = null;
        while (true) {
            
            System.out.println("Building Management System (BMS)");
            System.out.println("Please enter command: [a|d|m|e|u|r|l|x]");
            System.out.println("a = add building, d = display buildings, m = modify building, e = edit rooms ");
            System.out.println("u = undo, r = redo, l = list undo/redo, x = exit system");
            input = sc.nextLine();
            //exit
            if ( input.matches("x") | input.matches("X")  ){
                System.out.println("bye");
                break;
                //System.exit(0);
            }

            //add building finished
            else if (input.matches("a") | input.matches("A")){
                System.out.println("Enter Building Type (a=Apartment/h=House):");
                String building = sc.nextLine();

                
                if ( building.equals("a") || building.equals("A") || 
                    building.equals("h") || building.equals("H") ) {
                    
                    System.out.print("Building No.: ");
                    int building_id = sc.nextInt();

                    ((Factory_addBuilding) com_facs[0]).set_building_option_and_id(building, building_id);
                    command = (Command) com_facs[0].create();
                }
                
                else{
                    System.out.println("*****");
                    System.out.println("Typo!");
                    System.out.println("*****");
                }

                if (command!=null) {
                    commandHistory_Stack.add(command);
                    command.execute();
                }
                sc.nextLine();

            }

            //display buildings
            else if (input.matches("d") | input.matches("D")){
                // com_facs[2] for display
                command = (Command) com_facs[3].create();
                command.execute();
            }

            //modify building
            else if (input.matches("m") | input.matches("M")){
                command = (Command) com_facs[4].create();

                if (command!=null) {
                    commandHistory_Stack.add(command);
                    caretaker.add_Memento( ((Command_modifyBuiding) command).saveToMemento() );
                    command.execute();
                }
                
                
            }

            //edit rooms / modify room
            else if (input.matches("e") | input.matches("E")){
                System.out.print("Building No.: ");
                int building_id = sc.nextInt();
                if (!check_BuildingID.hasBuilding(building_id)) {
                    System.out.println("No such building");
                    System.out.println();
                    sc.nextLine();
                } else{
                    Building checkedBuilding = check_BuildingID.getBuilding_ifHasBuilding();
                    checkedBuilding.printBuilding_detail();
                    checkedBuilding.printRooms();
                    System.out.println("Please enter command: [a|d|m]");
                    System.out.println("a = add room, d = delete room, m = modify room");
                    sc.nextLine();
                    String edit_room_Com = sc.nextLine();
                    if ( edit_room_Com.matches("a") || edit_room_Com.matches("A") ) {
                        //1 for add room
                        ((Factory_addRoom) com_facs[1]).set_building(checkedBuilding);
                        command = (Command) com_facs[1].create();

                    } else if(edit_room_Com.matches("d") || edit_room_Com.matches("D")){
                        //2 for del room
                        ((Factory_deleteRoom) com_facs[2]).set_building(checkedBuilding);
                        command = (Command) com_facs[2].create();
                        
                    } else if(edit_room_Com.matches("m") || edit_room_Com.matches("M")){
                        //5 for modify room
                        ((Factory_modifyRoom) com_facs[5]).set_building(checkedBuilding);
                        command = (Command) com_facs[5].create();
                        
                    } else {
                        System.out.println("*****");
                        System.out.println("Typo!");
                        System.out.println("*****");
                    }

                    if (command!=null) {
                        commandHistory_Stack.add(command);
                        if(command instanceof Command_modifyRoom){
                            caretaker.add_Memento ( ((Command_modifyRoom) command).saveToMemento() );
                        }
                        command.execute();
                    }
                }
                
            }

            //undo
            else if (input.matches("u") | input.matches("U")){
                if (!commandHistory_Stack.isEmpty()) {
                    command = commandHistory_Stack.pop();
                    command_redo_list.add(command);
                    if (command instanceof Command_modifyBuiding ) {
                        ((Command_modifyBuiding) command).get_memento_from_caretaker(caretaker.get_Memento());
                    } else if (command instanceof Command_modifyRoom) {
                        ((Command_modifyRoom) command).get_memento_from_caretaker(caretaker.get_Memento());
                    }
                    command.undo();
                } else{
                    System.out.println("Nothing to undo!");
                }
            }

            //redo
            else if (input.matches("r") | input.matches("R")){
                if (!command_redo_list.isEmpty()) {
                    command = command_redo_list.pop();
                    commandHistory_Stack.add(command);
                    command.execute();
                } else {
                    System.out.println("Nothing to redo!");
                }
                
            }

            //list undo/redo
            else if (input.matches("l") | input.matches("L")){
                System.out.println("Undo List: ");
                for (int i = commandHistory_Stack.size()-1; i > -1; i--) {
                    System.out.println(commandHistory_Stack.get(i));
                }
                System.out.println("Redo List: ");
                for (int i = command_redo_list.size()-1; i > -1; i--) {
                    System.out.println(command_redo_list.get(i));
                }
                System.out.println();
            }

            else{
                System.out.println("*****");
                System.out.println("Typo!");
                System.out.println("*****");
            }
        } // while loop end

    }
}

    

