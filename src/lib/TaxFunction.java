package lib;

public class TaxFunction {
		
    private final IncomeDetail incomeDetail;
    private final FamilyStatus familyStatus;

    public TaxFunction(IncomeDetail incomeDetail, FamilyStatus familyStatus) {
        this.incomeDetail = incomeDetail;
        this.familyStatus = familyStatus;
    }

    public int calculateAnnualTax() {
        int taxableIncome = incomeDetail.getAnnualIncome() - incomeDetail.getDeductible() - familyStatus.getPTKP();
        return (taxableIncome <= 0) ? 0 : (int) Math.round(taxableIncome * 0.05);
    }
			 
}
	

