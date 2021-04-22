package ia;

public class Housing extends Expenses{
	double rent;
	double phone;
	double electricity;
	double gas;
	double water;
	double cable;
	double maintenance;
	double supplies;
	Expenses other = new Expenses();
	public Housing(String eT, double pC, double aC, double d) {
		String expenseType = eT;
		double projectedCost = pC;
		double actualCost = aC;
		double difference = d;
	}
//	public Housing(double r, double p, double e, double g, double w, double c, double m, double s) {
//		// TODO Auto-generated constructor stub
//	}

}
