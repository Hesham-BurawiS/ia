package ia;

public class Expenses {
	double other;
	double projectedCostSubtotal;
	double actualCostSubtotal;
	double differenceSubtotal;
	public Expenses() {
		other = 0;
		projectedCostSubtotal = 0;
		actualCostSubtotal = 0;
		differenceSubtotal = 0;
	}
public Expenses(double o, double p, double a, double d) {
	other = o;
	projectedCostSubtotal = p;
	actualCostSubtotal = a;
	differenceSubtotal = d;
}
}

