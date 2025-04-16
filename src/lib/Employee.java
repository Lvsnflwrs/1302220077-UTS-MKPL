package lib;

import java.time.LocalDate;

public class Employee {

    private String employeeId;
    private PersonalInfo personalInfo;
    private LocalDate joinDate;
    private Nationality nationality;
    private Gender gender;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private boolean hasSpouse;
    private int numberOfChildren;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
                    int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
        this.employeeId = employeeId;
        this.personalInfo = new PersonalInfo(firstName, lastName, idNumber, address);
        this.joinDate = LocalDate.of(yearJoined, monthJoined, dayJoined);
        this.nationality = isForeigner ? Nationality.FOREIGNER : Nationality.LOCAL;
        this.gender = gender ? Gender.MALE : Gender.FEMALE;
    }

    public void setMonthlySalary(Grade grade) {
        this.monthlySalary = grade.calculateSalary(this.nationality);
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String name, String idNumber) {
        this.hasSpouse = true;
    }

    public void addChild(String name, String idNumber) {
        this.numberOfChildren++;
    }

    public int getAnnualIncomeTax() {
        int monthWorked = calculateMonthWorked();
        IncomeDetail income = new IncomeDetail(monthlySalary, otherMonthlyIncome, monthWorked, annualDeductible);
        FamilyStatus family = new FamilyStatus(hasSpouse, numberOfChildren);

        TaxFunction calculator = new TaxFunction(income, family);
        return calculator.calculateAnnualTax();
    }

    private int calculateMonthWorked() {
        LocalDate today = LocalDate.now();
        return (today.getYear() == joinDate.getYear()) ? today.getMonthValue() - joinDate.getMonthValue() : 12;
    }

    public enum Gender { MALE, FEMALE }
    public enum Nationality { LOCAL, FOREIGNER }

    public enum Grade {
        GRADE1(3_000_000),
        GRADE2(5_000_000),
        GRADE3(7_000_000);

        private final int baseSalary;

        Grade(int baseSalary) {
            this.baseSalary = baseSalary;
        }

        public int calculateSalary(Nationality nationality) {
            return (nationality == Nationality.FOREIGNER) ? (int)(baseSalary * 1.5) : baseSalary;
        }
    }

    public static class PersonalInfo {
        private String firstName;
        private String lastName;
        private String idNumber;
        private String address;

        public PersonalInfo(String firstName, String lastName, String idNumber, String address) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.idNumber = idNumber;
            this.address = address;
        }
    }
}
