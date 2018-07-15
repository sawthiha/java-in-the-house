/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.Collection;

/**
 *
 * @author sawthiha
 */
public interface Observable {
    /**
     * Add an Observer.
     * @param o Observer
     */
    public void addObserver(Observer o);
    
    /**
     * Add a list of Observers.
     * @param list
     */
    public void addObserver(Collection<Observer> list);
    
    /**
     * Remove an Observer.
     * @param o Observer
     */
    public void removeObserver(Observer o);
    
    /**
     * Remove Observers.
     * @param list list of Observers
     */
    public void removeObserver(Collection<Observer> list);
    
    /**
     * Update all observers.
     */
    public void updateAll();
}
