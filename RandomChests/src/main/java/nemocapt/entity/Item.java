package nemocapt.entity;

import nemocapt.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Item {

    private Material material;
    private double chance;
    private int min;
    private int max;
    private String type;
    private int duration;
    private String name;
    private int color;
    private List<Enchentment> possibleEnchentments;

    public Item(ConfigurationSection configurationSection, List<Enchentment> possibleEnchentments) {

        try {
            this.material = Material.valueOf(configurationSection.getString("material"));
        } catch (Exception e) {
            material = Material.AIR;
            Bukkit.getLogger().severe("Material " + configurationSection.getString("material") + " was not found");
        }
        this.chance = configurationSection.getDouble("chance");
        this.min = configurationSection.getInt("min");
        this.max = configurationSection.getInt("max");
        this.type = configurationSection.getString("type");
        if (configurationSection.getString("duration") != null) {
            this.duration = configurationSection.getInt("duration");
        }
        if (configurationSection.getString("name") != null) {
            this.name = Objects.requireNonNull(configurationSection.getString("name")).replace("§", "&");
        }
        if (configurationSection.getString("color") != null) {
            this.color = configurationSection.getInt("color");
        }
        this.possibleEnchentments = possibleEnchentments;

    }

    public boolean shouldAdd(Random random) {
        return random.nextDouble() < this.chance;
    }

    public ItemStack add(ThreadLocalRandom random) {
        int amount = random.nextInt(this.min, this.max + 1);

        System.out.println(this.material.toString());
        if ("POTION".equals(this.material.toString()) || "SPLASH_POTION".equals(this.material.toString())) {
            ItemStack potion = new ItemStack(this.material, amount);
            PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
            PotionEffect potionEffect = new PotionEffect(Objects.requireNonNull(
                    PotionEffectType.getByName(this.type)),
                    this.duration * 20,
                    0);
            //тут можно юзать свой метод для установки цвета
            potionMeta.setDisplayName(Color.colorizeMyMessage(this.name));
            potionMeta.setColor(org.bukkit.Color.fromRGB(this.color));
            potionMeta.addCustomEffect(potionEffect, true);
            potion.setItemMeta(potionMeta);
            return potion;

        } else {
            ItemStack itemStack = new ItemStack(this.material, amount);

            ItemMeta itemMeta = itemStack.getItemMeta();

            double enchantChance = 1;
            double enchantModifier = 1;
            int enchantCount = 0;
            if (random.nextDouble() <= enchantChance) {
                for (Enchentment enchentment : possibleEnchentments) {
                    System.out.println(enchentment.getEnchantment());
                    if (random.nextDouble() < (enchentment.getChance() / enchantModifier) && enchantCount <= 3) {
                        assert itemMeta != null;
                        itemMeta.addEnchant(enchentment.getEnchantment(),
                                random.nextInt(enchentment.getMin(), enchentment.getMax() + 1),
                                true);
                        enchantModifier *= 2;
                        enchantCount++;
                    }
                }
            }
            itemStack.setItemMeta(itemMeta);

            return itemStack;
        }
    }

}
