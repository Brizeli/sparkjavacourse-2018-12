package heroes.character;

import heroes.annotation.BrightArmy;
import heroes.weapon.Weapon;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

import static heroes.weapon.WeaponType.LONG_DISTANCE;
import static heroes.weapon.WeaponType.SHORT_DISTANCE;

/**
 * @author Evgeny Borisov
 */
@Service
@Scope("prototype")
public class Elf extends BaseCharacter {

    @BrightArmy
    public Elf(List<Weapon> weapons) {
        super(weapons);
    }

    @Override
    protected void setDistance() {
        distance = 3;
    }

    @Override
    public void fight(Character enemy) {
        currentWeapon.kick(this, enemy);
        currentWeapon = dataFactory.getRandomWeapon(weaponTypeMap.get(
                --distance > 0 ? LONG_DISTANCE : SHORT_DISTANCE
        ));
    }
}
