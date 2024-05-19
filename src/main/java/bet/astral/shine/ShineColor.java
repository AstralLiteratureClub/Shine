package bet.astral.shine;

import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.HSVLike;
import net.kyori.adventure.util.RGBLike;
import net.kyori.examination.ExaminableProperty;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.awt.Color;
import java.util.stream.Stream;

/**
 * Represents a glowing color for an entity.
 * Has 2 color values {@link java.awt.Color} and {@link org.bukkit.ChatColor}.
 * Bukkit's ChatColor is to support glowing entities and other vanilla colors.
 * Java AWT color is to support lunar client's custom glowing colors
 */
public final class ShineColor extends Color implements TextColor, HSVLike, RGBLike {
	/**
	 * Represents vanilla BLACK glowing color
	 */
	public static final ShineColor BLACK = new ShineColor(0, 0, 0, ChatColor.BLACK);
	/**
	 * Represents vanilla DARK BLUE glowing color
	 */
	public static final ShineColor DARK_BLUE = new ShineColor(0, 0, 170, ChatColor.DARK_BLUE);
	/**
	 * Represents vanilla DARK GREEN glowing color
	 */
	public static final ShineColor DARK_GREEN = new ShineColor(0, 170, 0, ChatColor.DARK_GREEN);
	/**
	 * Represents vanilla DARK AQUA glowing color
	 */
	public static final ShineColor DARK_AQUA = new ShineColor(0, 170, 170, ChatColor.DARK_AQUA);
	/**
	 * Represents vanilla DARK RED glowing color
	 */
	public static final ShineColor DARK_RED = new ShineColor(170, 0, 0, ChatColor.DARK_RED);
	/**
	 * Represents vanilla DARK PURPLE glowing color
	 */
	public static final ShineColor DARK_PURPLE = new ShineColor(170, 0, 170, ChatColor.DARK_PURPLE);
	/**
	 * Represents vanilla GOLD glowing color
	 */
	public static final ShineColor GOLD = new ShineColor(255, 170, 0, ChatColor.GOLD);
	/**
	 * Represents vanilla GRAY glowing color
	 */
	public static final ShineColor GRAY = new ShineColor(170, 170, 170, ChatColor.GRAY);
	/**
	 * Represents vanilla DARK GRAY glowing color
	 */
	public static final ShineColor DARK_GRAY = new ShineColor(85, 85, 85, ChatColor.DARK_GRAY);
	/**
	 * Represents vanilla BLUE glowing color
	 */
	public static final ShineColor BLUE = new ShineColor(85, 85, 255, ChatColor.BLUE);
	/**
	 * Represents vanilla GREEN glowing color
	 */
	public static final ShineColor GREEN = new ShineColor(85, 225, 85, ChatColor.GREEN);
	/**
	 * Represents vanilla AQUA glowing color
	 */
	public static final ShineColor AQUA = new ShineColor(85, 255, 255, ChatColor.AQUA);
	/**
	 * Represents vanilla RED glowing color
	 */
	public static final ShineColor RED = new ShineColor(255, 85, 85, ChatColor.RED);
	/**
	 * Represents vanilla LIGHT PURPLE glowing color
	 */
	public static final ShineColor LIGHT_PURPLE = new ShineColor(255, 85, 255, ChatColor.LIGHT_PURPLE);
	/**
	 * Represents vanilla YELLOW glowing color
	 */
	public static final ShineColor YELLOW = new ShineColor(255, 255, 85, ChatColor.YELLOW);
	/**
	 * Represents vanilla WHITE glowing color
	 */
	public static final ShineColor WHITE = new ShineColor(255, 255, 255, ChatColor.WHITE);

	/**
	 * Text Color for those who want to use shine color for chat or similar
	 */
	private final TextColor textColor;
	/**
	 * The bukkit equivalent color for the java.awt.Color
	 */
	private final ChatColor bukkitEquivalent;

	/**
	 * Creates a new instance of ShineColor.
	 * Lunar client supports more colors than vanilla minecraft, so it's possible to tell custom red,
	 * green and blue for the glowing.
	 * Switches to bukkit equivalent color for non-lunar client players.
	 * @param red red
	 * @param green green
	 * @param blue blue
	 * @param bukkitEquivalent bukkit equivalent
	 */
	public ShineColor(int red, int green, int blue, ChatColor bukkitEquivalent) {
		super(red, green, blue);
		this.textColor = TextColor.color(red, green, blue);
		this.bukkitEquivalent = bukkitEquivalent;
	}
	/**
	 * Creates a new instance of ShineColor.
	 * Lunar client supports more colors than vanilla minecraft, so it's possible to tell custom red,
	 * green and blue for the glowing.
	 * Switches to bukkit equivalent color for non-lunar client players.
	 * @param color the color to get RGB from
	 * @param bukkitEquivalent bukkit equivalent
	 */
	public ShineColor(Color color, @NotNull ChatColor bukkitEquivalent){
		super(color.getRed(), color.getGreen(), color.getBlue());
		this.textColor = TextColor.color(color.getRed(), color.getGreen(), color.getBlue());
		this.bukkitEquivalent = bukkitEquivalent;
	}

	/**
	 * Returns the bukkit equivalent of the java.awt.Color.
	 * @return color
	 */
	public ChatColor getBukkitEquivalent() {
		return bukkitEquivalent;
	}

	@Override
	public int value() {
		return textColor.value();
	}

	@Override
	public @NotNull String asHexString() {
		return TextColor.super.asHexString();
	}

	@Override
	public @Range(from = 0L, to = 255L) int red() {
		return getRed();
	}

	@Override
	public @Range(from = 0L, to = 255L) int green() {
		return getGreen();
	}

	@Override
	public @Range(from = 0L, to = 255L) int blue() {
		return getBlue();
	}

	@Override
	public void styleApply(Style.Builder style) {
		TextColor.super.styleApply(style);
	}

	@Override
	public int compareTo(TextColor that) {
		return TextColor.super.compareTo(that);
	}

	@Override
	public @NotNull Stream<? extends ExaminableProperty> examinableProperties() {
		return TextColor.super.examinableProperties();
	}

	@Override
	public float h() {
		return textColor.asHSV().h();
	}

	@Override
	public float s() {
		return textColor.asHSV().s();
	}

	@Override
	public float v() {
		return textColor.asHSV().v();
	}
}
