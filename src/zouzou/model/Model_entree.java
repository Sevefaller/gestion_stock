package zouzou.model;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import zouzou.bean.Entree;
import zouzou.bean.Pdf;
import zouzou.bean.Produit;
import zouzou.hibernate.HibernateUtil;

public class Model_entree {
	//private Session s;
	
	public Model_entree(){
		//Session s = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public void addEntree(Entree e){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(e);
		Produit p = s.get(Produit.class, e.getNumProduit());
		if(p!=null){
			p.setStock(p.getStock()+e.getQteEntree());
			s.update(p);
		}
		s.getTransaction().commit();
	}
	
	public void updateEntree(Entree e){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Entree mp = s.get(Entree.class, e.getNumBonEntree());
		Produit p = s.get(Produit.class, e.getNumProduit());
		if(p!=null){
			p.setStock(p.getStock()-mp.getQteEntree()+e.getQteEntree());
			s.update(p);
		}
		s.getTransaction().commit();
		
		Session sS = HibernateUtil.getSessionFactory().getCurrentSession();
		sS.beginTransaction();
		sS.update(e);
		sS.getTransaction().commit();	
	}
	
	public void removeEntree(String numBonEntree){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Entree e = s.get(Entree.class, numBonEntree);
		if(e!=null){
			Produit p = s.get(Produit.class, e.getNumProduit());
			p.setStock(p.getStock()-e.getQteEntree());
			s.update(p);
			s.delete(e);
		}
		s.getTransaction().commit();		
	}
	
	public boolean verifieNumExiste(String numBonEntree){
		boolean existe = false;
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Entree p = s.get(Entree.class, numBonEntree);
		if(p!=null) existe = true;
		s.getTransaction().commit();
		return existe;
	}
	
	public Entree getEntree(String numBonEntree){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Entree e = s.get(Entree.class, numBonEntree);
		s.getTransaction().commit();
		return e;
	}
	
	public List<Entree> getEntrees(){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Entree e");
		List<Entree> lE = q.list();
		s.getTransaction().commit();
		return lE;
	}
	
	public List<Entree> getEntrees(String keyWord){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Entree e WHERE e.numProduit LIKE :x");
		q.setParameter("x", "%"+keyWord+"%");
		List<Entree> lE = q.list();
		s.getTransaction().commit();
		return lE;	
	}

	public void exporterPDF() {
		try{
			String filename = "C:\\Users\\ZOU\\Desktop\\entree.pdf";
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filename));
			PdfPTable table = new PdfPTable(4);
			document.open();

			//En tete du tableau
			table.addCell("Numero de Bon d'entrée");
			table.addCell("Produit");
			table.addCell("Quantité");
			table.addCell("Date");

			Session s = HibernateUtil.getSessionFactory().getCurrentSession();
			s.beginTransaction();
			Query q = s.createQuery("SELECT e.numBonEntree, p.design, e.qteEntree, e.dateEntree FROM Produit p, Entree e WHERE e.numProduit = p.numProduit ORDER BY numBonEntree");
			List<Pdf> l = q.list();
			s.getTransaction().commit();

			for(Pdf a : l){
				table.addCell(a.getA());
				table.addCell(a.getB());
				table.addCell(Integer.toString(a.getC()));
				table.addCell(a.getD().toString());
			}
			document.add(table);
			document.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
