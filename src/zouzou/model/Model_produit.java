package zouzou.model;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.List;
import zouzou.bean.Produit;
import zouzou.hibernate.HibernateUtil;

public class Model_produit {
	//private Session s;
	
	public Model_produit(){
		//Session s = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public void addProduit(Produit p){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(p);
		s.getTransaction().commit();
	}
	
	public void updateProduit(Produit p){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.update(p);
		s.getTransaction().commit();		
	}
	
	public void removeProduit(String numProduit){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Produit p = s.get(Produit.class, numProduit);
		if(p!=null) s.delete(p);
		s.getTransaction().commit();		
	}

	public boolean verifieNumExiste(String numProduit){
		boolean existe = false;
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Produit p = (Produit) s.get(Produit.class, numProduit);
		if(p!=null) existe = true;
		s.getTransaction().commit();
		return existe;
	}
	
	public boolean verifieDesignExiste(String design){
		boolean existe = false;
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		//Produit p = s.get(Produit.class, design);
		Query q = s.createQuery("SELECT p FROM Produit p WHERE p.design = :x");
		q.setParameter("x", design);
		List<Produit> p = q.list();
		if(p.size()!=0) existe = true;
		s.getTransaction().commit();
		return existe;
	}
	
	public Produit getProduit(String numProduit){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Produit p = s.get(Produit.class, numProduit);
		s.getTransaction().commit();
		return p;
	}
	
	public List<Produit> getProduits(){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT p FROM Produit p");
		List<Produit> p = q.list();
		s.getTransaction().commit();
		return p;
	}
	
	public List<Produit> getProduits(String keyWord){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT p FROM Produit p WHERE p.design LIKE :x");
		q.setParameter("x", "%"+keyWord+"%");
		List<Produit> p = q.list();
		s.getTransaction().commit();
		return p;	
	}
	
	public void exporterPDF() {
		try{
			String filename = "C:\\Users\\ZOU\\Desktop\\etat_stock.pdf";
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filename));
			PdfPTable table = new PdfPTable(3);
			document.open();

			//En tete du tableau
			table.addCell("Numero de Produit");
			table.addCell("Désignation");
			table.addCell("Stock");

			Session s = HibernateUtil.getSessionFactory().getCurrentSession();
			s.beginTransaction();
			Query q = s.createQuery("SELECT p FROM Produit p");
			List<Produit> p = q.list();
			s.getTransaction().commit();

			for(Produit a : p){
				table.addCell(a.getNumProduit());
				table.addCell(a.getDesign());
				table.addCell(Integer.toString(a.getStock()));
			}
			document.add(table);
			document.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}	
}
