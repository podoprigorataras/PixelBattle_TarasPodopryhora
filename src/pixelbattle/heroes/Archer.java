package pixelbattle.heroes;

import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.engine.Sprite;
import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

/** Лучниця — швидка й спритна. */
public class Archer extends Hero {

    public Archer() {
        super("Лучниця", 80, new Sprite("elf_f_idle_anim", 4, 4));

        addSkill(new Skill("Постріл", Target.ENEMY, 0, (user, target) ->
                user.getName() + " стріляє в " + target.getName() + ": "
                        + target.takeDamage(random(10, 16)) + " шкоди"));

        addSkill(new Skill("Подвійний постріл", Target.ENEMY, 1, (user, target) -> {
            int firstShot = target.takeDamage(random(7, 11));
            int secondShot = target.takeDamage(random(7, 11));
            int totalDamage = firstShot + secondShot;
            return user.getName() + " робить подвійний постріл: "
                    + totalDamage + " загальної шкоди";
        }));

        setUltimate(new Ultimate() {

            private int useCount = 0;
            @Override
            public String getName() {
                return "Злива стріл";
            }

            @Override
            public String getDescription() {
                return "Випускає кілька стріл у кожного живого ворога.";
            }

            @Override
            public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                int arrows = 3 + useCount;
                useCount++;
                int totalDamage = 0;
                for (Fighter enemy : enemies) {
                    if (enemy.isAlive()) {
                        for (int i = 0; i < arrows; i++) {
                            int damage = Fighter.random(4, 8);
                            totalDamage += enemy.takeDamage(damage);
                        }
                    }
                }
                return user.getName() + " використовує Зливу стріл - Кількість стріл: "
                        + arrows + ". Загальна шкода: " + totalDamage;
            }
        });
    }

    @Override
    protected int modifyIncomingDamage(int damage) {
        if (chance(25)) {
            return 0;
        }
        return damage;
    }
}
