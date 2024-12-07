package HCQLDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HCQLOpDemo 
{
	public static void main(String args[])
	{
		HCQLOpDemo operations= new HCQLOpDemo();
		//operations.addProject();
		operations.aggregatefunctions();


	}
	 public void addProject()
	    {
	    	Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        
	        SessionFactory sf = configuration.buildSessionFactory();
	        Session session = sf.openSession();
	     
	        Transaction t =  session.beginTransaction();
	     
	        Project p = new Project();
	         //use 
	         p.setName("EP");
	         p.setDuration(20);
	         p.setBudget(3000);
	         
	         p.setTeamLead("Sita");
	     
	         session.persist(p);
	         t.commit();
	         
	         System.out.println("Project Added Successfully");
	     
	        session.close();
	         sf.close();
	    }
	 
	 public void aggregatefunctions()
	    {
	    	Configuration configuration = new Configuration();
	 	    configuration.configure("hibernate.cfg.xml");
	 	        
	 	    SessionFactory sf = configuration.buildSessionFactory();
	 	    Session session = sf.openSession();
	 	    
	 	    CriteriaBuilder cb1 = session.getCriteriaBuilder();
		    CriteriaQuery<Long> cq1 = cb1.createQuery(Long.class);
		      
		    Root<Project> root1 = cq1.from(Project.class);
		    
		    cq1.select(cb1.count(root1.get("budget"))); 
		    
		    long totalcount =session.createQuery(cq1).getSingleResult();
		    
		    System.out.println("Total  Count = "+totalcount);
		    
		    /*************************************************************/
		    CriteriaBuilder cb2 = session.getCriteriaBuilder();
		    CriteriaQuery<Double> cq2 = cb2.createQuery(Double.class);
		      
		    Root<Project> root2= cq2.from(Project.class);
		    
		    cq2.select(cb2.sum(root2.get("budget"))); 
		    
		    double totalb = session.createQuery(cq2).getSingleResult();
		    
		    System.out.println("Total Budget = "+totalb);
		    
		    /*************************************************************/
		    
		    
		    CriteriaBuilder cb3 = session.getCriteriaBuilder();
		    CriteriaQuery<Double> cq3 = cb3.createQuery(Double.class);
		      
		    Root<Project> root3= cq3.from(Project.class);
		    
		    cq3.select(cb3.avg(root3.get("budget"))); 
		    
		    double avgage = session.createQuery(cq3).getSingleResult();
		    
		    System.out.println(" Average = "+avgage);
		    
		    /*************************************************************/
		    
		    CriteriaBuilder cb4 = session.getCriteriaBuilder();
		    CriteriaQuery<Double> cq4 = cb4.createQuery(Double.class);
		      
		    Root<Project> root4= cq4.from(Project.class);
		    
		    cq4.select(cb4.min(root4.get("budget"))); 
		    
		    double minsid = session.createQuery(cq4).getSingleResult();
		    
		    System.out.println(" Minimum  = "+minsid);
		    
		    /*************************************************************/
		    
		    
		    CriteriaBuilder cb5 = session.getCriteriaBuilder();
		    CriteriaQuery<Double> cq5 = cb5.createQuery(Double.class);
		      
		    Root<Project> root5= cq5.from(Project.class);
		    
		    cq5.select(cb5.max(root5.get("budget"))); 
		    
		    double maxsid = session.createQuery(cq5).getSingleResult();
		    
		    System.out.println(" Maximum = "+maxsid);
	    }
}
