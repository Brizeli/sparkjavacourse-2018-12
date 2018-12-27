package heroes.character;

import heroes.DataFactory;
import heroes.weapon.Weapon;
import heroes.weapon.WeaponType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static heroes.weapon.WeaponType.LONG_DISTANCE;

public abstract class BaseCharacter implements Character {

    protected int distance = 10;
    protected Map<WeaponType, List<Weapon>> weaponTypeMap;
    protected Weapon currentWeapon;
    protected DataFactory dataFactory = new DataFactory();

    public BaseCharacter(List<Weapon> weapons) {
        weaponTypeMap = weapons.stream().collect(Collectors.groupingBy(Weapon::getType));
        currentWeapon = dataFactory.getRandomWeapon(weaponTypeMap.get(LONG_DISTANCE));
        setDistance();
    }

    protected abstract void setDistance();
}
