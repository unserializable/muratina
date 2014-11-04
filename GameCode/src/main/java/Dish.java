import java.util.Random;


/**
 * @(#) Dish.java
 */

public class Dish extends MenuItem
{
	
	public Integer calorieCount;
	
	private Menu menu;
	
	public Dish() {
		generateDishData();
	}

	public Dish(String name, double price, QualityLevel quality,
			double ingredientCost) {
		super(name, price, quality, ingredientCost);
		// TODO Auto-generated constructor stub
	}

	private MealOrder mealOrder;
	
	/**
	 * @param beverage
	 * @param quality
	 * @return 
	 */
	public QualityLevel calculateIngredientCost(String quality) {
		if (quality.contains("1")) {
			super.quality = QualityLevel.High;
			ingredientCost = 10;
		}else{
			super.quality = QualityLevel.Low;
			ingredientCost = 3;
		}
		return super.quality;
	}
	
	public void generateDishData() {
		Random r = new Random();

//		int i1 = r.nextInt(8); // returns random number between 0 and 7
//		int i2 = r.nextInt(8);
//		int i3 = r.nextInt(8);
//		int i4 = r.nextInt(742); // returns random number between 0 and 741
//		int i5 = r.nextInt(10000); // returns random number between 0 and 9999
//
//		name = String.format("%d%d%d-%03d-%04d", i1, i2, i3, i4, i5);
//		taxCode = String.format("tc-%03d-%04d", i1, i2, i3, i4, i5);

		String[] names = { "Rice", "Peas", "Bread", "Mutton", "Fish",
				"Chapati", "Grilled Toast", "Potatoes", "Beef", "Burger", "Pizza", "Fries",
				"Onions", "Barley" };
		
		int nindex = (int) (Math.random() * names.length);
		name = names[nindex];
		String qindex = Integer.toString((int)(Math.random() * 2));
		calorieCount = r.nextInt(499);
		quality = calculateIngredientCost(qindex);

	}

}
