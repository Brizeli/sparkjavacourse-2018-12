package heroes.weapon;

import heroes.character.Character;

/**
 * @author Evgeny Borisov
 */
public interface Weapon {
    void kick(Character owner, Character enemy);

    WeaponType getType();
}
