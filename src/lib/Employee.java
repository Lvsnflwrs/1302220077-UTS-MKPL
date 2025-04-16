package lib;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee {

    private String employeeId;
    private PersonalInfo personalInfo;
    private JoinDate joinDate;
    private Nationality nationality;
    private Gender gender;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private Spouse spouse;
    private List<Child> children = new ArrayList<>();

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
                    int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
        this.employeeId = employeeId;
        this.personalInfo = new PersonalInfo(firstName, lastName, idNumber, address);
        this.joinDate = new JoinDate(yearJoined, monthJoined, dayJoined);
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
        this.spouse = new Spouse(name, idNumber);
    }

    public void addChild(String name, String idNumber) {
        this.children.add(new Child(name, idNumber));
    }

    public int getAnnualIncomeTax() {
        int monthWorked = calculateMonthWorked();
        IncomeDetail income = new IncomeDetail(monthlySalary, otherMonthlyIncome, monthWorked, annualDeductible);
        FamilyStatus family = new FamilyStatus(spouse != null, children.size());
        return TaxFunction.calculateTax(income, family);
    }

    private int calculateMonthWorked() {
        LocalDate today = LocalDate.now();
        return (today.getYear() == joinDate.getYear()) ?
                today.getMonthValue() - joinDate.getMonth() : 12;
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

    public static class Spouse {
        private String name;
        private String idNumber;
        public Spouse(String name, String idNumber) {
            this.name = name;
            this.idNumber = idNumber;
        }
    }

    public static class Child {
        private String name;
        private String idNumber;
        public Child(String name, String idNumber) {
            this.name = name;
            this.idNumber = idNumber;
        }
    }

    public static class JoinDate {
        private int year;
        private int month;
        private int day;
        public JoinDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }
        public int getYear() { return year; }
        public int getMonth() { return month; }
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
