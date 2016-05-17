package br.com.upgrade.ocp6;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalExamples {
	
	public static void main(String[] args) {
	
		Insurance defaultInsurance = new Insurance("First Company Insurances", 
				new BigDecimal("1234.78"));
		
		Truck withInsurance = new Truck("Mercedes-Bens", defaultInsurance);
		Truck withoutInsurance = new Truck("Scania", null);
		
		Driver d1 = new Driver("Sr. With Insurance", withInsurance);
		Driver d2 = new Driver("Sr. Without Insurance", withoutInsurance);
		Driver d3 = new Driver("Sr. Without Truck", null);
		
		printAutomaker(d1);
		printAutomaker(d2);
		printAutomaker(d3);
		
		printInsurance(d1);
		printInsurance(d2);
		printInsurance(d3);
		
		printAfterFilter(d1);
		printAfterFilter(d2);
		printAfterFilter(d3);
		
	}
	
	/**
	 * Se o motorista possuir caminhão, imprime a montadora do mesmo
	 * @param d
	 */
	private static void printAutomaker(Driver d) {
		
		System.out.println("\nDriver: " + d.getName());
		
		try {
			//imprime a montadora se o caminão existir, senão lança a exceção NoSuchElementException
			System.out.println(d.getTruck().get().getAutomaker());
		} catch(NoSuchElementException e) {
			System.out.println("Truck is null");
		}
		
	}
	
	/**
	 * Se o motorista possuir caminhão e o caminhão possuir seguro, imprime as informações do seguro.
	 * Repare que, quando o caminhão é nulo ou quando o seguro é nulo, não irá ocorrer erro em tempo
	 * de execução
	 * @param d
	 */
	private static void printInsurance(Driver d) {
		
		System.out.println("\nDriver: " + d.getName());

		//imprime os dados do seguro se existir um caminhão e se o caminhão possuir seguradora
		System.out.println(d.getTruck().flatMap(Truck::getInsurance).map(Insurance::toString));

		//o mesmo que o método anterior mas utilizando lambda e o método ifPresent
		d.getTruck().flatMap(truck -> truck.getInsurance()).ifPresent(param -> {
			System.out.println(param.toString());
		});
		
		//mapeia o resultado de Truck::getInsurance e se o mesmo não existir, retorna uma nova instância
		Insurance i = d.getTruck().flatMap(Truck::getInsurance).orElse(new Insurance("Unknown", BigDecimal.ZERO));
		System.out.println("Insurance: "+i.getCompany());
		
		//mapeia o resultado de Truck::getAutomaker e se o mesmo não existir, imprime Unknow
		System.out.println(d.getTruck().map(Truck::getAutomaker).orElse("Unknown autoamaker"));
		
		//descomente para entender porquê devemos utilizar o flatMap em alguns casos como este
		//d.getTruck().map(Truck::getInsurance).ifPresent(Insurance::toString);
		System.out.println();
	}
	
	/**
	 * Executa o filtro no caminão informado e, caso existir, imprime no console
	 * @param d
	 */
	private static void printAfterFilter(Driver d) {
		
		System.out.println("\nDriver: " + d.getName());
		
		//filtra o caminão do motorista, se o mesmo existir e se possuir seguradora
		d.getTruck().filter(truck -> truck.getInsurance().isPresent())
			.ifPresent((Truck t) -> {
				System.out.println("The driver " + d.name +" drives a "+ t.getAutomaker() +" truck.");	
			});
		
	}
		
	private static class Driver {
		
		private String name;
		private Optional<Truck> truck;
		
		public Driver(String name, Truck truck) {
			this.name = name;
			//Optional.ofNullable cria um Optional<Truck> que pode ou não conter null
			this.truck = Optional.ofNullable(truck);
			
			try {
				//o método Optional.of(T element) sempre vai lançar NullPointerException se for informado
				//um objeto nulo para ele
				this.truck = Optional.of(truck);
			} catch(NullPointerException e) {
				System.out.println("NullPointerException because the param truck is null and the method"
						+ "Optional.of(T value) does not acept null elements\n");
			}
			
		}

		public String getName() {
			return name;
		}

		public Optional<Truck> getTruck() {
			return truck;
		}
		
	}
	
	private static class Truck {
		
		private String automaker;
		private Optional<Insurance> insurance;
		
		public Truck(String automaker, Insurance insurance) {
			this.automaker = automaker;
			this.insurance = Optional.ofNullable(insurance);
		}

		public String getAutomaker() {
			return automaker;
		}

		public Optional<Insurance> getInsurance() {
			return insurance;
		}
		
	}
	
	private static class Insurance {
		
		private String company;
		private BigDecimal value;
		
		public Insurance(String company, BigDecimal value) {
			this.company = company;
			this.value = value;
		}

		public BigDecimal getValue() {
			return value;
		}

		public String getCompany() {
			return company;
		}

		@Override
		public String toString() {
			return "Company: " + company + " | Value: " + value;
		}
		
	}
	
}
