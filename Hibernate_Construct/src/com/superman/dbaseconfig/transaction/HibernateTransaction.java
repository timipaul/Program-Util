package com.superman.dbaseconfig.transaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.superman.dbaseconfig.session.HibernateSessionFactory;

/**
 * ������
 * @author Ningsc(supermanNingsc)
 *
 */
public class HibernateTransaction {
	private static Transaction transaction = null;

	
	/**
	 * ����insert,delete,update������������
	 */
	public static Transaction beginTransaction(){
		Session session = HibernateSessionFactory.createSession();
		if(session != null && session.isOpen()){
			transaction = session.beginTransaction();
			return transaction;
		}
		return null;
	}
	
	/**
	 * ����insert,delete,update�����ύ����
	 */
	public static void commitTransaction(){
		if(transaction != null && transaction.isActive()){
			transaction.commit();
		}
	}
	
	/**
	 * ����insert,delete,update�����ع�����
	 */
	public static void rollbackTransaction(){
		if(transaction != null && transaction.isActive()){
			transaction.rollback();
		}
	}
}
