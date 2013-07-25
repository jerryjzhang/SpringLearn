package com.junz.hibernate.interceptor;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.type.Type;

import com.junz.hibernate.domain.IAuditable;

/*
 * Since this hibernate interceptor is going to be in
 * sessionFactory scope, shared by all sessions, it must
 * be thread-safe. A solution I can think of so far
 * is to store session-level information inside thread
 * local instead of instance fields
 */
public class LogInterceptor extends EmptyInterceptor {
	public static final ThreadLocal<LogInterceptorContext> logThreadLocal = 
			new ThreadLocal<LogInterceptorContext>();
	
	@Override
	public boolean onSave(
			Object entity, 
			Serializable id, 
			Object[] state, 
			String[] propertyNames, 
			Type[] types) {
		
		if (entity instanceof IAuditable){
			if(logThreadLocal.get() == null){
				logThreadLocal.set(new LogInterceptorContext());
			}

			logThreadLocal.get().getInserts().add((IAuditable)entity);
		}
			
	    return false;
	}
	
	@Override
	public void onDelete(
			Object entity, 
			Serializable id, 
			Object[] state, 
			String[] propertyNames, 
			Type[] types) {
		if (entity instanceof IAuditable){
			if(logThreadLocal.get() == null){
				logThreadLocal.set(new LogInterceptorContext());
			}
			
			logThreadLocal.get().getDeletes().add((IAuditable)entity);
		}
		
	}

	@Override
	public boolean onFlushDirty(
			Object entity, 
			Serializable id, 
			Object[] currentState, 
			Object[] previousState, 
			String[] propertyNames, 
			Type[] types) {
		if (entity instanceof IAuditable){
			if(logThreadLocal.get() == null){
				logThreadLocal.set(new LogInterceptorContext());
			}
			
			logThreadLocal.get().getUpdates().add((IAuditable)entity);
		}
		
		return false;
	}

	@Override
	public void postFlush(Iterator entities) {
		//System.out.println("invoking postFlush()");
		Set<IAuditable> inserts = logThreadLocal.get().getInserts();
		Set<IAuditable> updates = logThreadLocal.get().getUpdates();
		Set<IAuditable> deletes = logThreadLocal.get().getDeletes();
		
		try {
			for (Iterator<IAuditable> it = inserts.iterator(); it.hasNext();) {
				IAuditable entity = it.next();
				System.out.println("create " +  entity);
			}
			for (Iterator<IAuditable> it = updates.iterator(); it.hasNext();) {
				IAuditable entity = it.next();
				System.out.println("update " +  entity);
			}
			for (Iterator<IAuditable> it = deletes.iterator(); it.hasNext();) {
				IAuditable entity = it.next();
				System.out.println("delete " + entity);
			}
		} finally {
			inserts.clear();
			updates.clear();
			deletes.clear();
		}
	}
	
	public void afterTransactionBegin(Transaction tx) {
		System.out.println("junz----afterTransactionBegin");
	}
	public void afterTransactionCompletion(Transaction tx) {
		System.out.println("junz----afterTransactionCompletion");
	}
	
	private class LogInterceptorContext{
		private Set<IAuditable> inserts = new HashSet<IAuditable>();
		private Set<IAuditable> updates = new HashSet<IAuditable>();
		private Set<IAuditable> deletes = new HashSet<IAuditable>();
		public Set<IAuditable> getInserts() {
			return inserts;
		}

		public Set<IAuditable> getUpdates() {
			return updates;
		}

		public Set<IAuditable> getDeletes() {
			return deletes;
		}
	}

}
