package heroes;

import heroes.character.Elf;
import heroes.character.Goblin;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Evgeny Borisov
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("heroes");
        Elf elf = context.getBean(Elf.class);
        Goblin goblin = context.getBean(Goblin.class);
        for (int i = 0; i < 10; i++) {
            elf.fight(goblin);
            goblin.fight(elf);
        }
    }
}
