package heroes.weapon;

import heroes.character.Character;
import heroes.annotation.DarkArmy;
import org.springframework.stereotype.Component;

import static heroes.weapon.WeaponType.SHORT_DISTANCE;

/**
 * @author Evgeny Borisov
 */
@Component
@DarkArmy
public class Log implements Weapon {
    @Override
    public void kick(Character owner, Character enemy) {
        System.out.println("Log kick you");
    }

    @Override
    public WeaponType getType() {
        return SHORT_DISTANCE;
    }
}
