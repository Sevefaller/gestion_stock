package zouzou.bean;

import java.util.Date;

public class Sortie {
	private String numBonSortie;
	private String numProduit;
	private int qteSortie;
	private Date dateSortie;
	
	public Sortie() {}

	public Sortie(String numBonSortie, String numProduit, int qteSortie, Date dateSortie) {
		this.numBonSortie = numBonSortie;
		this.numProduit = numProduit;
		this.qteSortie = qteSortie;
		this.dateSortie = dateSortie;
	}

	public String getNumBonSortie() {
		return numBonSortie;
	}

	public void setNumBonSortie(String numBonSortie) {
		this.numBonSortie = numBonSortie;
	}

	public String getNumProduit() {
		return numProduit;
	}

	public void setNumProduit(String numProduit) {
		this.numProduit = numProduit;
	}

	public int getQteSortie() {
		return qteSortie;
	}

	public void setQteSortie(int qteSortie) {
		this.qteSortie = qteSortie;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}
	
	
}
