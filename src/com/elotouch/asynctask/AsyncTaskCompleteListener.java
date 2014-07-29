/**
 * 
 */
package com.elotouch.asynctask;

/**
 * @author Guest
 *
 */
public interface AsyncTaskCompleteListener <T> {

	public abstract void onStarted(); 
	public abstract void onTaskComplete(T result);
	public abstract void onStoped(); 
	public abstract void onStopedWithError(Error e);
}
