package br.com.earthpvp.core.spigot;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class UtilItem {

	public static ItemStack item(Material material) {
		return item(1, material);
	}

	public static ItemStack item(int amount, Material material) {
		return item(amount, material, 0);
	}

	public static ItemStack item(Material material, int data) {
		return item(1, material, data, null);
	}

	public static ItemStack item(int amount, Material material, int data) {
		return item(amount, material, data, null);
	}

	public static ItemStack item(Material material, String display) {
		return item(1, material, 0, display);
	}

	public static ItemStack item(int amount, Material material, String display) {
		return item(amount, material, 0, display);
	}

	public static ItemStack item(int amount, Material material, int data, String display) {
		return item(amount, material, data, display, null);
	}

	public static ItemStack item(Material material, String display, List<String> lore) {
		return item(1, material, 0, display, lore);
	}

	public static ItemStack item(Material material, int data, String display, List<String> lore) {
		return item(1, material, data, display, lore);
	}

	public static ItemStack item(int amount, Material material, String display, List<String> lore) {
		return item(amount, material, 0, display, lore);
	}

	public static List<String> asLore(String string) {
		if (string.contains("\n")) {
			String[] arr = string.split("\n");
			for (int x = 1; x < arr.length; x++) {
				arr[x] = ChatColor.getLastColors(arr[x - 1]) + arr[x];
			}
			return Arrays.asList(arr);
		} else {
			return Arrays.asList(string);
		}
	}

	public static ItemStack item(int amount, Material material, int data, String display, List<String> lore) {
		ItemStack i = new ItemStack(material, amount, (short) data);
		if (display != null)
			setDisplay(i, display);
		if (lore != null)
			setLore(i, lore);
		return i;
	}

	public static ItemStack head(String player, String display, List<String> lore) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(display);
		meta.setOwner(player);
		if (lore != null)
			meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack skull(String display, String url, List<String> lore) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setDisplayName(display);

		UUID uuid = new UUID(url.hashCode(), url.hashCode());
		GameProfile profile = new GameProfile(uuid, null);
		byte[] encodedData = Base64.getEncoder()
				.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		Field profileField = null;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		if (lore != null)
			meta.setLore(lore);

		skull.setItemMeta(meta);
		return skull;
	}

	public static ItemStack skull(String display, String url) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setDisplayName(display);

		UUID uuid = new UUID(url.hashCode(), url.hashCode());
		GameProfile profile = new GameProfile(uuid, null);
		byte[] encodedData = Base64.getEncoder()
				.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		Field profileField = null;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}

		skull.setItemMeta(meta);
		return skull;
	}

	public static SkullMeta setSkullURL(SkullMeta meta, String url) {
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		byte[] encodedData = Base64.getEncoder()
				.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		Field profileField = null;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return meta;
	}

	public static void setLeatherDye(ItemStack a, DyeColor color) {
		setLeatherDye(a, color.getColor());
	}

	public static void setLeatherDye(ItemStack a, Color color) {
		if (a != null) {
			LeatherArmorMeta meta = (LeatherArmorMeta) a.getItemMeta();
			meta.setColor(color);
			a.setItemMeta(meta);
		}
	}

	public static void setDisplay(ItemStack a, String b) {
		if (b == null)
			return;
		ItemMeta c = a.getItemMeta();
		c.setDisplayName(b);
		a.setItemMeta(c);
	}

	public static String getDisplay(ItemStack a) {
		if (a == null || !a.hasItemMeta() || !a.getItemMeta().hasDisplayName())
			return null;
		return a.getItemMeta().getDisplayName();
	}

	public static void setLore(ItemStack a, List<String> b) {
		if (b == null)
			return;
		ItemMeta c = a.getItemMeta();
		c.setLore(b);
		a.setItemMeta(c);
	}

	public static List<String> getLore(ItemStack a) {
		if (a == null || !a.hasItemMeta() || !a.getItemMeta().hasLore())
			return null;
		return a.getItemMeta().getLore();
	}

	public static boolean hasDisplay(ItemStack a, String b) {
		if (a == null || !a.hasItemMeta())
			return false;
		if (!a.getItemMeta().hasDisplayName())
			return false;
		return a.getItemMeta().getDisplayName().equalsIgnoreCase(b);
	}
}
