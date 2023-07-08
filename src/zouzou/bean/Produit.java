package zouzou.bean;

public class Produit {
	private String numProduit;
	private String design;
	private int stock;
	
	public Produit() {}

	public Produit(String numProduit, String design, int stock) {
		this.numProduit = numProduit;
		this.design = design;
		this.stock = stock;
	}

	public String getNumProduit() {
		return numProduit;
	}

	public void setNumProduit(String numProduit) {
		this.numProduit = numProduit;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
