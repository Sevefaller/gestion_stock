package zouzou.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import zouzou.bean.Entree;
import zouzou.bean.Mvm;
import zouzou.bean.Produit;
import zouzou.bean.Sortie;
import zouzou.hibernate.HibernateUtil;

public class Mvm_stock_model {
	List<Mvm> mvm;
	
	public Mvm_stock_model() {}
	
	public List<Mvm> getMvm() {
		return mvm;
	}

	public void setMvm(List<Mvm> mvm) {
		this.mvm = mvm;
	}
	
	public int get_stock(String numProduit) {
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Produit p = s.get(Produit.class, numProduit);
		s.getTransaction().commit();
		return p.getStock();
	}
	
	public List<Mvm> list_simple(String numProduit){
		List<Mvm> liste_mvm = new ArrayList<Mvm>();

		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Entree e WHERE e.numProduit = :x");
		q.setParameter("x", numProduit);
		List<Entree> liste_entree = q.list();
		s.getTransaction().commit();

		Session s1 = HibernateUtil.getSessionFactory().getCurrentSession();
		s1.beginTransaction();
		Query q1 = s1.createQuery("SELECT e FROM Sortie e WHERE e.numProduit = :x1");
		q1.setParameter("x1", numProduit);
		List<Sortie> liste_sortie = q1.list();
		s1.getTransaction().commit();
		
		for(Entree a : liste_entree){
			Mvm mvm = new Mvm();
			mvm.setNum(a.getNumBonEntree());
			mvm.setQtEntree(a.getQteEntree());
			mvm.setQteSortie(0);
			mvm.setDate(a.getDateEntree());
			
			liste_mvm.add(mvm);
		}

		for(Sortie a : liste_sortie){
			Mvm mvm1 = new Mvm();
			mvm1.setNum(a.getNumBonSortie());
			mvm1.setQtEntree(0);
			mvm1.setQteSortie(a.getQteSortie());
			mvm1.setDate(a.getDateSortie());
			
			liste_mvm.add(mvm1);
		}
		return liste_mvm;
	}
	
	public int totalEntree(String numProduit) {
		int total = 0;
		
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Entree e WHERE e.numProduit = :x");
		q.setParameter("x", numProduit);
		List<Entree> l = q.list();
		s.getTransaction().commit();
		for(Entree a : l){
			total += a.getQteEntree(); 
		}
		
		return total;
	}

	public int totalSortie(String numProduit) {
		int total = 0;
		
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Sortie e WHERE e.numProduit = :x");
		q.setParameter("x", numProduit);
		List<Sortie> l = q.list();
		s.getTransaction().commit();
		for(Sortie a : l){
			total += a.getQteSortie();
		}
		
		return total;
	}
	public List<Mvm> list_entre_date(String numProduit, Date av, Date ap){
		List<Mvm> liste_mvm = new ArrayList<Mvm>();

		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Entree e WHERE e.numProduit = :x AND (e.dateEntree BETWEEN :xx and :xxx)");
		q.setParameter("x", numProduit);
		q.setParameter("xx", av);
		q.setParameter("xxx", ap);
		List<Entree> liste_entree = q.list();
		s.getTransaction().commit();

		Session s1 = HibernateUtil.getSessionFactory().getCurrentSession();
		s1.beginTransaction();
		Query q1 = s1.createQuery("SELECT e FROM Sortie e WHERE e.numProduit = :x1 AND (e.dateSortie BETWEEN :xx1 and :xxx1)");
		q1.setParameter("x1", numProduit);
		q1.setParameter("xx1", av);
		q1.setParameter("xxx1", ap);
		List<Sortie> liste_sortie = q1.list();
		s1.getTransaction().commit();
		
		for(Entree a : liste_entree){
			Mvm mvm = new Mvm();
			mvm.setNum(a.getNumBonEntree());
			mvm.setQtEntree(a.getQteEntree());
			mvm.setQteSortie(0);
			mvm.setDate(a.getDateEntree());
			
			liste_mvm.add(mvm);
		}

		for(Sortie a : liste_sortie){
			Mvm mvm1 = new Mvm();
			mvm1.setNum(a.getNumBonSortie());
			mvm1.setQtEntree(0);
			mvm1.setQteSortie(a.getQteSortie());
			mvm1.setDate(a.getDateSortie());
			
			liste_mvm.add(mvm1);
		}
		return liste_mvm;
	}
	
	public int totalEntree_deux_dates(String numProduit, Date av, Date ap) {
		int total = 0;
		
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Entree e WHERE e.numProduit = :x AND (e.dateEntree BETWEEN :xx and :xxx)");
		q.setParameter("x", numProduit);
		q.setParameter("xx", av);
		q.setParameter("xxx", ap);
		List<Entree> l = q.list();
		s.getTransaction().commit();
		for(Entree a : l){
			total += a.getQteEntree(); 
		}
		
		return total;
	}
	
	public int totalSortie_deux_dates(String numProduit, Date av, Date ap) {
		int total = 0;
		
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Sortie e WHERE e.numProduit = :x AND (e.dateSortie BETWEEN :xx and :xxx)");
		q.setParameter("x", numProduit);
		q.setParameter("xx", av);
		q.setParameter("xxx", ap);
		List<Sortie> l = q.list();
		s.getTransaction().commit();
		for(Sortie a : l){
			total += a.getQteSortie();
		}
		
		return total;
	}
	
	public List<Mvm> liste_mois(String numProduit, int mois){
		List<Mvm> liste_mvm = new ArrayList<Mvm>();
		
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Entree e WHERE e.numProduit = :x AND MONTH(e.dateEntree) = :xx");
		q.setParameter("x", numProduit);
		q.setParameter("xx", mois);
		List<Entree> liste_entree = q.list();
		s.getTransaction().commit();

		Session s1 = HibernateUtil.getSessionFactory().getCurrentSession();
		s1.beginTransaction();
		Query q1 = s1.createQuery("SELECT e FROM Sortie e WHERE e.numProduit = :x1 AND MONTH(e.dateSortie) = :xx1");
		q1.setParameter("x1", numProduit);
		q1.setParameter("xx1", mois);
		List<Sortie> liste_sortie = q1.list();
		s1.getTransaction().commit();
		
		for(Entree a : liste_entree){
			Mvm mvm = new Mvm();
			mvm.setNum(a.getNumBonEntree());
			mvm.setQtEntree(a.getQteEntree());
			mvm.setQteSortie(0);
			mvm.setDate(a.getDateEntree());
			
			liste_mvm.add(mvm);
		}

		for(Sortie a : liste_sortie){
			Mvm mvm1 = new Mvm();
			mvm1.setNum(a.getNumBonSortie());
			mvm1.setQtEntree(0);
			mvm1.setQteSortie(a.getQteSortie());
			mvm1.setDate(a.getDateSortie());
			
			liste_mvm.add(mvm1);
		}
		return liste_mvm;
	}
	
	public int totalEntree_mois(String numProduit, int mois) {
		int total = 0;
		
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Entree e WHERE e.numProduit = :x AND MONTH(e.dateEntree) = :xx");
		q.setParameter("x", numProduit);
		q.setParameter("xx", mois);
		List<Entree> l = q.list();
		s.getTransaction().commit();
		for(Entree a : l){
			total += a.getQteEntree(); 
		}
		
		return total;
	}
	
	public int totalSortie_mois(String numProduit, int mois) {
		int total = 0;
		
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Sortie e WHERE e.numProduit = :x AND MONTH(e.dateSortie) = :xx");
		q.setParameter("x", numProduit);
		q.setParameter("xx", mois);
		List<Sortie> l = q.list();
		s.getTransaction().commit();
		for(Sortie a : l){
			total += a.getQteSortie();
		}
		
		return total;
	}

	public List<Mvm> list_an(String numProduit, int an){
		List<Mvm> liste_mvm = new ArrayList<Mvm>();
		
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Entree e WHERE e.numProduit = :x AND YEAR(e.dateEntree) = :xx");
		q.setParameter("x", numProduit);
		q.setParameter("xx", an);
		List<Entree> liste_entree = q.list();
		s.getTransaction().commit();

		Session s1 = HibernateUtil.getSessionFactory().getCurrentSession();
		s1.beginTransaction();
		Query q1 = s1.createQuery("SELECT e FROM Sortie e WHERE e.numProduit = :x1 AND YEAR(e.dateSortie) = :xx1");
		q1.setParameter("x1", numProduit);
		q1.setParameter("xx1", an);
		List<Sortie> liste_sortie = q1.list();
		s1.getTransaction().commit();
		
		for(Entree a : liste_entree){
			Mvm mvm = new Mvm();
			mvm.setNum(a.getNumBonEntree());
			mvm.setQtEntree(a.getQteEntree());
			mvm.setQteSortie(0);
			mvm.setDate(a.getDateEntree());
			
			liste_mvm.add(mvm);
		}

		for(Sortie a : liste_sortie){
			Mvm mvm1 = new Mvm();
			mvm1.setNum(a.getNumBonSortie());
			mvm1.setQtEntree(0);
			mvm1.setQteSortie(a.getQteSortie());
			mvm1.setDate(a.getDateSortie());
			
			liste_mvm.add(mvm1);
		}
		return liste_mvm;		
	}
	
	public int totalEntree_an(String numProduit, int an) {
		int total = 0;
		
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Entree e WHERE e.numProduit = :x AND YEAR(e.dateEntree) = :xx");
		q.setParameter("x", numProduit);
		q.setParameter("xx", an);
		List<Entree> l = q.list();
		s.getTransaction().commit();
		for(Entree a : l){
			total += a.getQteEntree(); 
		}
		
		return total;
	}
	
	public int totalSortie_an(String numProduit, int an) {
		int total = 0;
		
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT e FROM Sortie e WHERE e.numProduit = :x AND YEAR(e.dateSortie) = :xx");
		q.setParameter("x", numProduit);
		q.setParameter("xx", an);
		List<Sortie> l = q.list();
		s.getTransaction().commit();
		for(Sortie a : l){
			total += a.getQteSortie();
		}
		
		return total;
	}
}
