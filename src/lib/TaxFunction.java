package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */

		
	 public static int calculateTax(IncomeDetail income, FamilyStatus family) {
	        int annualIncome = income.getAnnualIncome();
	        int deductible = income.getDeductible();
	        int ptkp = family.getPTKP();

	        int taxableIncome = annualIncome - deductible - ptkp;
	        if (taxableIncome <= 0) return 0;

	        return (int) Math.round(taxableIncome * 0.05);
	    }
			 
	}
	

