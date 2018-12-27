package heroes.character;

import heroes.annotation.DarkArmy;
import heroes.weapon.Weapon;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static heroes.weapon.WeaponType.LONG_DISTANCE;
import static heroes.weapon.WeaponType.SHORT_DISTANCE;

@Component
@Scope("prototype")
public class Goblin extends BaseCharacter {
    @DarkArmy
    public Goblin(List<Weapon> weapons) {
        super(weapons);
    }

    @Override
    protected void setDistance() {
        distance = 7;
    }

    @Override
    public void fight(Character enemy) {
        currentWeapon.kick(this, enemy);
        currentWeapon = dataFactory.getRandomWeapon(weaponTypeMap.get(
                --distance > 0 ? LONG_DISTANCE : SHORT_DISTANCE
        ));
    }
}
