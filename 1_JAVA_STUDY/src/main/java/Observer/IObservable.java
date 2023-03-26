//============================================================================
// Name        : IObservable.java
// Created on  : December 24, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : IObservable interface
//============================================================================

package Observer;

/**
 * @author a.tokmakov
 * @interface IObserver.
 * 
 * This interface represents an observable object, or "data"
 * in the model-view paradigm. It can be subclassed to represent an
 * object that the application wants to have observed.
 *
 * An observable object can have one or more observers. An observer
 * may be any object that implements interface IObserver. After an
 * observable instance changes, an application calling the
 * IObservable's 'notifyObservers' method
 * causes all of its observers to be notified of the change by a call
 * to their 'update' method.
 *
 * The order in which notifications will be delivered is unspecified.
 * The default implementation provided in the Observable class will
 * notify Observers in the order in which they registered interest, but
 * subclasses may change this order, use no guaranteed order, deliver
 * notifications on separate threads, or may guarantee that their
 * subclass follows this order, as they choose.
 * 
 * When an observable object is newly created, its set of observers is
 * empty. Two observers are considered the same if and only if the
 * equals method returns true for them.
 */
public interface IObservable {

	/**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     * The order in which notifications will be delivered to multiple
     * observers is not specified. See the class comment.
     *
     * @param  observer   an observer to be added.
     * @throws NullPointerException   if the parameter observer is null.
	 */
    public void addObserver(IObserver observer);
    
    /**
     * Deletes an observer from the set of observers of this object.
     * Passing null to this method will have no effect.
     * @param   observer   the observer to be deleted.
     */
    public void deleteObserver(IObserver observer);
  
    /**
     * Clears the observer list so that this object no longer has any observers.
     */
    public void deleteObservers();
    
    /**
     * Notify all of its observers
     * Each observer has its 'update' method called with two
     * arguments: this observable object and the 'null' argument.
     *
     * @see  java.util.Observable#clearChanged()
     * @see  java.util.Observable#hasChanged()
     * @see  java.util.Observer#update(java.util.Observable, Object)
     */
    public void notifyObservers();
    
    /**
     * Notify all of its observers
     * Each observer has its 'update' method called with two
     * arguments: this observable object and the 'arguments' argument.
     *
     * @param  arguments   any object.
     * @see    java.util.Observable#clearChanged()
     * @see    java.util.Observable#hasChanged()
     * @see    java.util.Observer#update(java.util.Observable, Object)
     */
    public void notifyObservers(Object arguments);
    
    /**
     * Returns the number of observers of this object.
     *
     * @return  the number of observers of this object.
     */
    public int countObservers();
}
