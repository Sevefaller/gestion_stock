package zouzou.model;

import java.io.FileOutputStream;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import zouzou.bean.Entree;
import zouzou.bean.Pdf;
import zouzou.bean.Produit;
import zouzou.bean.Sortie;
import zouzou.hibernate.HibernateUtil;

public class Model_sortie {
	//private Session s;
	
	public Model_sortie(){
		//Session s = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public void addSortie(Sortie e){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(e);
		Produit p = s.get(Produit.class, e.getNumProduit());
		if(p!=null){
			p.setStock(p.getStock()-e.getQteSortie());
			s.update(p);
		}
		s.getTransaction().commit();
	}
	
	public void updateSortie(Sortie e){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Sortie mp = s.get(Sortie.class, e.getNumBonSortie());
		if(mp!=null){
			Produit p = s.get(Produit.class, e.getNumProduit());
			p.setStock(p.getStock()+mp.getQteSortie()-e.getQteSortie());
			s.update(p);
		}
		s.getTransaction().commit();
		
		Session sS = HibernateUtil.getSessionFactory().getCurrentSession();
		sS.beginTransaction();
		sS.update(e);
		sS.getTransaction().commit();		
	}
	
	public void removeSortie(String numBonSortie){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Sortie e = s.get(Sortie.class, numBonSortie);
		if(e!=null){
			Produit p = s.get(Produit.class, e.getNumProduit());
			p.setStock(p.getStock()+e.getQteSortie());
			s.update(p);
			s.delete(e);
		}
		s.getTransaction().commit();		
	}

	public boolean verifieNumExiste(String numBonSortie){
		boolean existe = false;
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Sortie p = s.get(Sortie.class, numBonSortie);
		if(p!=null) existe = true;
		s.getTransaction().commit();
		return existe;
	}
	
	public Sortie getSortie(String numBonSortie){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Sortie e = s.get(Sortie.class, numBonSortie);
		s.getTransaction().commit();
		return e;
	}
	
	public List<Sortie> getSorties(){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Sortie e");
		List<Sortie> sortie = q.list();
		s.getTransaction().commit();
		return sortie;
	}
	
	public List<Sortie> getSorties(String keyWord){
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Sortie e WHERE e.numProduit LIKE :x");
		q.setParameter("x", "%"+keyWord+"%");
		List<Sortie> sortie = q.list();
		s.getTransaction().commit();
		return sortie;
	}
	
	public void exporterPDF() {
		try{
			String filename = "C:\\Users\\ZOU\\Desktop\\sortie.pdf";
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filename));
			PdfPTable table = new PdfPTable(4);
			document.open();

			//En tete du tableau
			table.addCell("Numero de Bon de sortie");
			table.addCell("Produit");
			table.addCell("Quantité");
			table.addCell("Date");

			Session s = HibernateUtil.getSessionFactory().getCurrentSession();
			s.beginTransaction();
			Query q = s.createQuery("SELECT e.numBonSortie, p.design, e.qteSortie, e.dateSortie FROM Produit p, Sortie e WHERE e.numProduit = p.numProduit ORDER BY numBonSortie");
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
