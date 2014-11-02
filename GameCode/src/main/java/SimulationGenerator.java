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
		result.setExperience(ExperienceLevel.Low);
		result.setEmployeeType(employeeType);
		if (EmployeeType.Chef.equals(employeeType))
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

	public static void main(String[] args) {
		System.out.println(rndClient());
	}
}
