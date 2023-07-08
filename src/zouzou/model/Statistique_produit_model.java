package zouzou.model;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.google.gson.Gson;

import zouzou.bean.Produit;
import zouzou.bean.Sortie;
import zouzou.hibernate.HibernateUtil;

public class Statistique_produit_model {
	public Statistique_produit_model() {
		
	}

	public String create_chart() {
		Gson gsonObj = new Gson();
        Map<Object,Object> map = null;
        List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();          
        String dataPoints = null;
        
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q = s.createQuery("SELECT p FROM Produit p");
		List<Produit> l = q.list();
		s.getTransaction().commit();
        String xVal;
        Integer yVal;
        
		for(Produit a : l){
            xVal = a.getDesign();
            yVal = a.getStock();
            
            map = new HashMap<Object,Object>(); 
            map.put("label", xVal ); 
            map.put("y",yVal); 
            list.add(map);
            dataPoints = gsonObj.toJson(list);		
		}
        
        return dataPoints;
	}
}
