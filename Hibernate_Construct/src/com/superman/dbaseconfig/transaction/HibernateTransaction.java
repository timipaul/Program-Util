package com.superman.dbaseconfig.transaction;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.superman.dbaseconfig.session.HibernateSessionFactory;

/**
 * 事务类
 * @author Ningsc(supermanNingsc)
 *
 */
public class HibernateTransaction {
	private static Transaction transaction = null;

	
	/**
	 * 对于insert,delete,update操作开启事务
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
	 * 对于insert,delete,update操作提交事务
	 */
	public static void commitTransaction(){
		if(transaction != null && transaction.isActive()){
			transaction.commit();
		}
	}
	
	/**
	 * 对于insert,delete,update操作回滚事务
	 */
	public static void rollbackTransaction(){
		if(transaction != null && transaction.isActive()){
			transaction.rollback();
		}
	}
}
