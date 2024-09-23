package ru.healthanmary.trainingplugin.ShulkerAnimation;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.healthanmary.trainingplugin.TrainingPlugin;

public class CreateArmorStand {
    public static void spawnArmorStand(Player p) {
        Bukkit.getScheduler().runTask(TrainingPlugin.getInstance(), () -> {
            ArmorStand armorStand = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);

            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setCustomNameVisible(false);

            ItemStack shulker  = new ItemStack(Material.SHULKER_BOX);
            armorStand.getEquipment().setHelmet(shulker);

//            Bukkit.getScheduler().runTask(TrainingPlugin.getInstance(), new Runnable() {
//                float yaw = 0;
//                @Override
//                public void run() {
//                    if (armorStand.isValid()) {
//                        yaw +=5;
//                        if (yaw >= 360)
//                            yaw = 0;
//                        armorStand.setRotation(yaw, 0);
//                    } else {
//                        Bukkit.getScheduler().cancelTask(this.hashCode());
//                    }
//                }
//            }, 0L, 5L);
//
//        Bukkit.getScheduler().runTask(TrainingPlugin.getInstance(), new Runnable() {
//            @Override
//            public void run() {
//                if (5 >3)
//                    System.out.println("hbjdfbjk");
//            }
//        }, 5L, 0L);
//
//            Bukkit.getScheduler().runTaskTimer(TrainingPlugin.getInstance(), new Runnable() {
//                float yaw = 0; // начальный угол
//
//                @Override
//                public void run() {
//                    if (armorStand.isValid()) {
//                        yaw += 5; // Увеличиваем угол поворота на 5 градусов
//                        if (yaw >= 360) yaw = 0; // Сбрасываем угол после полного круга
//                        armorStand.setRotation(yaw, 0); // Устанавливаем новый угол поворота (Yaw)
//                    } else {
//                        // Останавливаем вращение, если стойка удалена
//                        Bukkit.getScheduler().cancelTask(this.hashCode());
//                    }
//                }
//            }, 0L, 5L);

            Bukkit.getScheduler().runTaskLater(TrainingPlugin.getInstance(), () -> {
                if (armorStand.isValid()) {
                        armorStand.remove(); }
            }, 200L);
        });
    }
}
