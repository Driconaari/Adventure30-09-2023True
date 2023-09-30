public class RangedWeapon extends Item {
    private int attackPower;

    public RangedWeapon(String name, String description, int attackPower) {
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