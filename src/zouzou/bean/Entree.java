package zouzou.bean;

import java.util.Date;

public class Entree {
	private String numBonEntree;
	private String numProduit;
	private int qteEntree;
	private Date dateEntree;
	
	public Entree() {}

	public Entree(String numBonEntree, String numProduit, int qteEntree, Date dateEntree) {
		this.numBonEntree = numBonEntree;
		this.numProduit = numProduit;
		this.qteEntree = qteEntree;
		this.dateEntree = dateEntree;
	}

	public String getNumBonEntree() {
		return numBonEntree;
	}

	public void setNumBonEntree(String numBonEntree) {
		this.numBonEntree = numBonEntree;
	}

	public String getNumProduit() {
		return numProduit;
	}

	public void setNumProduit(String numProduit) {
		this.numProduit = numProduit;
	}

	public int getQteEntree() {
		return qteEntree;
	}

	public void setQteEntree(int qteEntree) {
		this.qteEntree = qteEntree;
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}
	
}
