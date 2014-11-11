/**
 * @(#) Beverage.java
 */

public class Beverage extends MenuItem
{
	public static final Integer HIGH_COST = 3;
	public static final Integer LOW_COST = 1;

	private Integer volume;

	@Override
	public Integer getIngredientCost() {
		switch (getQuality()) {
			case HIGH: return HIGH_COST;
			case LOW: return LOW_COST;
		}
		throw new IllegalStateException("Unknown Quality Beverage (" + getQuality().name() + ")");
	}

	public Integer getVolume() {
		return volume;
	}

	public Beverage setVolume(Integer volume) {
		this.volume = volume;
		return this;
	}

	public String menuName() {
		return getName() +  " (" + volume + " ml)";
	}
}
