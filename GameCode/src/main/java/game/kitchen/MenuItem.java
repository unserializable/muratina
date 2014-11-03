package game.kitchen;
/**
 * @(#) MenuItem.java
 */

public abstract class MenuItem {
	public String name;

	public double price;

	public QualityLevel quality;

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public QualityLevel getQuality() {
		return quality;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public MenuItem() {
		
	}

	public void setQuality(QualityLevel quality) {
		this.quality = quality;
	}

	public MenuItem(String name, double price, QualityLevel quality) {
		this.name = name;
		this.price = price;
		this.quality = quality;
	}

}
