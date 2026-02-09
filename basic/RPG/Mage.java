public class Mage extends Job {
    public Mage(String name, int level, int hp){
        super(name, level, hp);
    }
    public Mage(String name){
        super(name);
    }

    @Override
    public void attack(){
        System.out.println(getName() + " Fireball!!");
    }
}
