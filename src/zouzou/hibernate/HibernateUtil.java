package zouzou.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	 private static SessionFactory sessionFactory;

	 static {
	   try {
	   sessionFactory =
	         new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	   } catch (Exception ex) {
		   throw new RuntimeException("Problème de configuration : "+ ex.getMessage(), ex);
	   }
	 }

	 public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}