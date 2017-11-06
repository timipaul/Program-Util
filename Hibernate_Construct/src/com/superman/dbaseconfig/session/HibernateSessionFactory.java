package com.superman.dbaseconfig.session;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



/**
 * ���ݿ����Ӷ���
 * @author Ningsc(supermanNingsc)
 *
 */
public class HibernateSessionFactory {
	private static Logger logger = Logger.getLogger(HibernateSessionFactory.class);
	private static String HIBERNATE_CONFIG = "./hibernate.cfg.xml";
	private static Configuration configuration = new Configuration();
	private static SessionFactory sessionFactory = null;
	private static Session session = null;
	
	static{
		try{
			configuration.configure(HIBERNATE_CONFIG);
			sessionFactory = configuration.buildSessionFactory();
		}catch(Exception e){
			logger.error("Error Create SessionFactory,Please Check Your File of Configuration");
			e.printStackTrace();
		}
	}
	
	/**
	 * ����session����
	 * @return
	 */
	public static Session createSession(){
		if(sessionFactory != null){
			session = sessionFactory.openSession();
			return session;
		}
		return null;
	}
	
	/**
	 * �ر�session�Ự
	 */
	public static void closeSession(){
		if(session != null && session.isOpen()){
			session.close();
		}
	}

}
