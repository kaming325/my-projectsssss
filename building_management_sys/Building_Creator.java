public abstract class Building_Creator {
    private Building building;

    public abstract Building FactoryMethod();
    
    public void anOperation(){
        this.building = FactoryMethod();
    }
}
