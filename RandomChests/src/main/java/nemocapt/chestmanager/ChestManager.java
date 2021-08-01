package nemocapt.chestmanager;

import nemocapt.entity.Enchentment;
import nemocapt.entity.Item;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ChestManager implements Listener {

    private Set<Location> usedChests = new HashSet<>();
    private List<Item> items = new ArrayList<>();
    private List<Item> doubleChestItems = new ArrayList<>();

    public ChestManager(FileConfiguration fileConfiguration) {

        try {
            ConfigurationSection itemConfigurationSection = fileConfiguration.getConfigurationSection("items");
            ConfigurationSection itemConfigurationSectionForDoubleChests =
                    fileConfiguration.getConfigurationSection("doublechest_items");
            assert itemConfigurationSection != null;
            for (String key : itemConfigurationSection.getKeys(false)) {
                ConfigurationSection currentSection = itemConfigurationSection.getConfigurationSection(key);
                assert currentSection != null;
                ConfigurationSection enchantmentSection;
                List<Enchentment> enchentmentList = new ArrayList<>();
                if ("MELEE_WEAPON".equals(currentSection.getString("type"))) {
                    enchantmentSection = fileConfiguration.getConfigurationSection("melee_weapon_enchantments");
                    for (String key2 : enchantmentSection.getKeys(false)) {
                        ConfigurationSection currentEnchantmentSection = enchantmentSection.getConfigurationSection(key2);
                        enchentmentList.add(new Enchentment(currentEnchantmentSection));
                    }
                }
                if ("BOW".equals(currentSection.getString("type"))) {
                    enchantmentSection = fileConfiguration.getConfigurationSection("bow_enchantments");
                    for (String key2 : enchantmentSection.getKeys(false)) {
                        ConfigurationSection currentEnchantmentSection = enchantmentSection.getConfigurationSection(key2);
                        enchentmentList.add(new Enchentment(currentEnchantmentSection));
                    }
                }
                items.add(new Item(currentSection, enchentmentList));
            }
            for (String key : itemConfigurationSectionForDoubleChests.getKeys(false)) {
                ConfigurationSection currentSection =
                        itemConfigurationSectionForDoubleChests.getConfigurationSection(key);
                assert currentSection != null;
                ConfigurationSection enchantmentSection;
                List<Enchentment> enchentmentList = new ArrayList<>();
//                if ("MELEE_WEAPON".equals(currentSection.getString("type"))) {
//                    enchantmentSection = fileConfiguration.getConfigurationSection("melee_weapon_enchantments");
//                    for (String key2 : enchantmentSection.getKeys(false)) {
//                        ConfigurationSection currentEnchantmentSection = enchantmentSection.getConfigurationSection(key2);
//                        enchentmentList.add(new Enchentment(currentEnchantmentSection));
//                    }
//                }
                System.out.println(currentSection.toString());
                doubleChestItems.add(new Item(currentSection, enchentmentList));
            }
        } catch (Exception e) {
            Bukkit.getLogger().severe("Check your config file" + Arrays.toString(e.getStackTrace()));
        }

    }

    @EventHandler
    private void onChestOpened(InventoryOpenEvent event) {
        InventoryHolder inventoryHolder = event.getInventory().getHolder();

        if (inventoryHolder instanceof Chest) {
            Chest chest = (Chest) inventoryHolder;
            if (isUsed(chest.getLocation())) {
                return;
            }
            setUsed(chest.getLocation());
            fillChest(chest.getBlockInventory());
        } else if (inventoryHolder instanceof DoubleChest) {
            DoubleChest doubleChest = (DoubleChest) inventoryHolder;
            if (isUsed(doubleChest.getLocation())) {
                return;
            }
            setUsed(doubleChest.getLocation());
            fillDoubleChest(doubleChest.getInventory());
        }

    }

    private void fillChest(Inventory inventory) {
        inventory.clear();
        for (int j = 0; j < 2; j++) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Set<Item> usedItems = new HashSet<>();

            for (int i = 0; i < inventory.getSize(); i++) {
                Item item = items.get(random.nextInt(items.size()));
                if (usedItems.contains(item)) {
                    continue;
                }
                usedItems.add(item);
                if (item.shouldAdd(random)) {
                    ItemStack itemStack = item.add(random);
                    inventory.setItem(i, itemStack);
                }
            }
            if (inventory.isEmpty()) {
                fillChest(inventory);
            }
        }

    }

    private void fillDoubleChest(Inventory inventory) {
        inventory.clear();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Set<Item> usedItems = new HashSet<>();

        for (int i = 0; i < inventory.getSize(); i++) {
            Item item = doubleChestItems.get(random.nextInt(doubleChestItems.size()));
            if (usedItems.contains(item)) {
                continue;
            }
            usedItems.add(item);
            if (item.shouldAdd(random)) {
                ItemStack itemStack = item.add(random);
                inventory.setItem(i, itemStack);
            }
        }
        if (inventory.isEmpty()) {
            fillDoubleChest(inventory);
        }

    }


    private void setUsed(Location location) {
        usedChests.add(location);
    }

    private boolean isUsed(Location location) {
        return usedChests.contains(location);
    }

    public void resetChests() {
        usedChests.clear();
    }

}
