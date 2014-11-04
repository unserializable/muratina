import java.util.*;

public class SimulationGenerator {
	private static final Random random = new Random();

	private static final Integer MILLION = 1000 * 1000;

	private static final String[] FORENAMES = {
			"Taimo", "Diamond", "Mihkel", "Michael", "Lauri", "Robert", "Nikolai", "Fabrizio", "Naved", "Jeremy",
			"Helena", "Marin", "Tiina", "Kadri", "Phoebe", "Shazia", "Akiko", "Jamie", "Mia", "Liisa"
	};

	private static final String[] SURNAMES = {
			"Peelo", "Michuki", "Tamm", "Saar", "Kask", "Reyes", "Nguyen", "Javed", "Naved", "Raie", "Vilo",
			"Maggi", "Tretjakov", "Torvalds", "Gates", "Laks", "Perv", "Gainsbourg", "Adekunle", "Formaggi"
	};

	private static final String[] PHONE_COUNTRY_CODES = {
			"+372", "+61", "+254", "+39", "+358", "+92", "+856", "+84", "+255"
	};

	private static final String[] TEST_DISHES = {
			"Verivorst", "Mulgipuder", "Lasagne", "Foie Gras", "Buckwheat Porridge", "Bean Stew w/ Minced Meat",
			"Spaghetti alla Carbonara", "Ahjukartul", "Chilli Con Carne", "Vegetariana a la Carnivore"
	};

	private static final String[] TEST_BEVERAGES = {
			"Muratina", "Coca-Cola", "Red Bull", "Gin & Tonic", "Metsakohin", "Buckwheat Porridge", "Sex on the Beach",
			"Cider", "Fizz", "Strongbow", "Virmalised", "Puhja Konn"
	};


	public static Client rndClient() {
		Client result = new Client();
		result.setName(rndForeName());
		result.setSurname(rndSurname());
		result.setPhoneNo(rndPhoneNumber());
		result.setTaxCode(rndTaxCode());
		return result;
	}

	public static Set<Client> rndPopulation(Integer populationSize) {
		Set<Client> result = new HashSet<Client>(populationSize);
		while (result.size() < populationSize) {
			result.add(rndClient());
		}
		return result;
	}

	public static Employee rndEmployee(EmployeeType employeeType) {
		Employee result = new Employee();
		result.setName(rndForeName());
		result.setSurname(rndSurname());
		result.setExperience(ExperienceLevel.LOW);
		result.setEmployeeType(employeeType);
		result.setPhoneNo(rndPhoneNumber());
		if (EmployeeType.CHEF.equals(employeeType))
			result.setTaxCode(rndTaxCode());

		return result;
	}

	public static Set<Employee> rndStaff(Map<EmployeeType, Integer> employeeCounts) {
		int empCount = employeeCounts.values().stream().reduce(0, (l, r) -> l + r);
		Set<Employee> result = new HashSet<>(empCount);
		for (Map.Entry<EmployeeType, Integer>  entry: employeeCounts.entrySet()) {
			Set<Employee> employees = new HashSet<>(entry.getValue());
			while (employees.size() < entry.getValue()) {
				employees.add(rndEmployee(entry.getKey()));
			}
			result.addAll(employees);
		}

		return result;
	}

	public static String rndPhoneNumber() {
		String prefix = PHONE_COUNTRY_CODES[random.nextInt(PHONE_COUNTRY_CODES.length)];
		String suffix = String.valueOf(10*MILLION + random.nextInt(90*MILLION));
		return prefix + suffix;
	}

	public static String rndForeName() {
		return FORENAMES[random.nextInt(FORENAMES.length)];
	}

	public static String rndSurname() {
		return SURNAMES[random.nextInt(SURNAMES.length)];
	}

	public static String rndTaxCode() {
		return String.valueOf(10 + random.nextInt(89)) + String.valueOf(100000000 + random.nextInt(899999999));
	}

	public static Set<Integer> rndCombination(Integer k, Integer n) {
		Set<Integer> combination = new HashSet<>(k);
		if (k == n) {
			for (int i = 1; i <= n; i++)
				combination.add(Integer.valueOf(i));
		} else {
			while (combination.size() < k) {
				combination.add((1+random.nextInt(n)));
			}
		}
		return combination;
	}

	public static <T> Set<T> rndCombination(Integer k, Set<T> things) {
		Set<T> result = new HashSet<>(k);
		Set<Integer> combination = rndCombination(k, things.size());
		for (Integer i: combination) {
			result.add((T)things.toArray()[i-1]);
		}
		return result;
	}

	// returns a list of dishes with different names, but same calorie counts, quality (HIGH),
	public static Set<Dish> rndTestDishes() {
		Set<Dish> dishes = new LinkedHashSet<>(5);
		for (Integer i: rndCombination(5, TEST_DISHES.length)) {
			Dish dish = new Dish();
			dish.setCalorieCount(1213).setName(TEST_DISHES[i-1]).setQuality(QualityLevel.HIGH).setPrice(18);
			dishes.add(dish);
		}
		return dishes;
	}

	// returns a list of beverages with different names, but same volumes and quality (HIGH),
	public static Set<Beverage> rndTestBeverages() {
		Set<Beverage> beverages = new LinkedHashSet<>(5);
		for (Integer i: rndCombination(5, TEST_BEVERAGES.length)) {
			Beverage dish = new Beverage();
			dish.setVolume(100).setName(TEST_BEVERAGES[i-1]).setQuality(QualityLevel.HIGH).setPrice(11);
			beverages.add(dish);
		}
		return beverages;
	}
}
