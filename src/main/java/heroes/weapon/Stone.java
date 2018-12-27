package heroes.weapon;

import heroes.character.Character;
import heroes.annotation.DarkArmy;
import org.springframework.stereotype.Component;

import static heroes.weapon.WeaponType.LONG_DISTANCE;

/**
 * @author Evgeny Borisov
 */
@Component
@DarkArmy
public class Stone implements Weapon {
    @Override
    public void kick(Character owner, Character enemy) {
        System.out.println("Stone kick you");
    }

    @Override
    public WeaponType getType() {
        return LONG_DISTANCE;
    }
}
