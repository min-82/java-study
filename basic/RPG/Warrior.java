public class Warrior extends Job implements Mountable{
    public Warrior(String name, int level, int hp){
        super(name, level, hp);
    }
    public Warrior(String name){
        super(name);
    }

    @Override
    public void attack(){
        System.out.println(getName() + " Sword Attack!!");
    }

    @Override
    public void ride() {
        System.out.println(getName() + "님이 말에 올라타 빠르게 이동합니다.");
    }
}
