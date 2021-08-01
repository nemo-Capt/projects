package nemocapt.entity;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;

import java.util.Objects;

public class Enchentment {

    private Enchantment enchantment;
    private double chance;
    private int min;
    private int max;

    public Enchentment(ConfigurationSection configurationSection) {

        try {
            this.enchantment = Enchantment.getByKey(NamespacedKey.minecraft(
                    Objects.requireNonNull(configurationSection.getString("name")).toLowerCase()));
        } catch (Exception e) {
            Bukkit.getLogger().severe("Check your config file for enchantments");
        }
        this.chance = configurationSection.getDouble("chance");
        this.min = configurationSection.getInt("min");
        this.max = configurationSection.getInt("max");
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public void setEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
