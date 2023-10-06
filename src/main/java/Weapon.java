public class Weapon extends Item {
    private int attackPower;

    public Weapon(String name, String description, int attackPower) {
        super(name, description);
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
}
