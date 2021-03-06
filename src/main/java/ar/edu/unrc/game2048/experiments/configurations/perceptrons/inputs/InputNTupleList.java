/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unrc.game2048.experiments.configurations.perceptrons.inputs;

import java.util.*;

/**
 * Implementación de una Lista, útil para almacenar valores binarios, optimizada para no ocupar memoria con los valores 0. Utilizada para las entradas
 * de una red neuronal grande.
 *
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public
class InputNTupleList
        implements List< Double > {

    private final Set< Integer > set;

    /**
     *
     */
    public
    InputNTupleList() {
        super();
        set = new HashSet<>();
    }

    @Override
    public
    boolean add( final Double e ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    void add(
            final int index,
            final Double element
    ) {
        set.add(index);
    }

    @Override
    public
    boolean addAll( final Collection< ? extends Double > c ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    boolean addAll(
            final int index,
            final Collection< ? extends Double > c
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public
    void clear() {
        set.clear();
    }

    @Override
    public
    boolean contains( final Object o ) {
        return set.contains(o);
    }

    @Override
    public
    boolean containsAll( final Collection< ? > c ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    Double get( final int index ) {
        return ( set.contains(index) ) ? 1.0d : 0.0d;
    }

    /**
     * @return tamaño interno usado.
     */
    public
    int getInternalSetSize() {
        return set.size();
    }

    @Override
    public
    int indexOf( final Object o ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    Iterator< Double > iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    int lastIndexOf( final Object o ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    ListIterator< Double > listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    ListIterator< Double > listIterator( final int index ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    boolean remove( final Object o ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    Double remove( final int index ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    boolean removeAll( final Collection< ? > c ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    boolean retainAll( final Collection< ? > c ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    Double set(
            final int index,
            final Double element
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public
    List< Double > subList(
            final int fromIndex,
            final int toIndex
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public
    < T > T[] toArray( final T[] a ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
