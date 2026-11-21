package pixelbattle.ultimates;

import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

public class Meteor implements Ultimate {

    @Override
    public String getName() {
        return "Метеорит";
    }

    @Override
    public String getDescription() {
        return "Викликає метеорит, який завдає всім ворогам 20-30 шкоди.";
    }

    @Override
    public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
        int totalDamage = 0;
        for (Fighter enemy : enemies) {
            if (enemy.isAlive()) {
                int damage = Fighter.random(20, 30);
                totalDamage += enemy.takeDamage(damage);
            }
        }
        return user.getName() + " викликає Метеорит - Загальна шкода: " + totalDamage;
    }
}