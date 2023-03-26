//============================================================================
// Name        : IObserver.java
// Created on  : December 24, 2019
// Author      : Tokmakov Andrey
// Version     : 1.0
// Copyright   : Your copyright notice
// Description : IObserver interface
//============================================================================

package Observer;

/**
 * @author a.tokmakov
 * @interface IObserver.
 * 
 * A class can implement the IObserver interface when it
 * wants to be informed of changes in observable objects.
 */
public interface IObserver {
	
    /**
     * This method is called whenever the observed object is changed. An
     * application calls an IObservable object's 'notifyObservers' 
     * method to have all the object's observers notified of the change.
     *
     * @param observable - the observable object.
     * @param arguments  - an argument passed to the method.
     */
	void update(final IObservable observable, 
				final Object arguments);
}
