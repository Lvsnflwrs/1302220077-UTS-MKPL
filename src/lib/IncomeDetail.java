package lib;

public class IncomeDetail {
    private final int monthlySalary;
    private final int otherMonthlyIncome;
    private final int monthsWorked;
    private final int deductible;

    public IncomeDetail(int monthlySalary, int otherMonthlyIncome, int monthsWorked, int deductible) {
        if (monthsWorked > 12) {
            throw new IllegalArgumentException("Working months cannot exceed 12.");
        }

        this.monthlySalary = monthlySalary;
        this.otherMonthlyIncome = otherMonthlyIncome;
        this.monthsWorked = monthsWorked;
        this.deductible = deductible;
    }

    public int getAnnualIncome() {
        return (monthlySalary + otherMonthlyIncome) * monthsWorked;
    }

    public int getDeductible() {
        return deductible;
    }
}
