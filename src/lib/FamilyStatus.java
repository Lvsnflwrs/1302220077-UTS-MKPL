package lib;

public class FamilyStatus {
    private final boolean isMarried;
    private final int numberOfChildren;

    public FamilyStatus(boolean isMarried, int numberOfChildren) {
        this.isMarried = isMarried;
        this.numberOfChildren = Math.min(numberOfChildren, 3);
    }

    public int getPTKP() {
        final int BASIC_PTKP = 54_000_000;
        final int SPOUSE_PTKP = 4_500_000;
        final int CHILD_PTKP = 1_500_000;

        int total = BASIC_PTKP;
        if (isMarried) {
            total += SPOUSE_PTKP + (numberOfChildren * CHILD_PTKP);
        }
        return total;
    }
}
