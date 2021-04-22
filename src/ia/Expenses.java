package ia;

public class Expenses {
	String type;
	double projectedCost;
	double actualCost;
	double difference;
	public Expenses() {
		type = "";
		projectedCost = 0;
		actualCost = 0;
		difference = 0;
	}
public Expenses(String t, double p, double a, double d) {
	type = t;
	projectedCost = p;
	actualCost = a;
	difference= d;
}
}

