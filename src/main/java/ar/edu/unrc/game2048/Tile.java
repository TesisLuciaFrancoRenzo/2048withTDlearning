package ar.edu.unrc.game2048;

import ar.edu.unrc.coeus.tdlearning.training.ntuple.SamplePointValue;

/**
 * @author lucia bressan, franco pellegrini, renzo bianchini
 */
public
class Tile
        implements SamplePointValue {
    private boolean merged = false;
    private int value;

    public
    Tile( final int val ) {
        super();
        value = val;
    }

    public static
    int getCodeFromTileValue( final int tileValue ) {
        return (int) ( Math.log(tileValue) / Math.log(2) );
    }

    boolean canMergeWith( final Tile other ) {
        return !merged && ( other != null ) && !other.merged && ( value == other.value );
    }

    @Override
    public
    boolean equals( final Object o ) {
        if ( this == o ) { return true; }
        if ( !( o instanceof Tile ) ) { return false; }

        final Tile tile = (Tile) o;

        return value == tile.value;
    }

    int getValue() {
        return value;
    }

    @Override
    public
    int hashCode() {
        return value;
    }

    int mergeWith( final Tile other ) {
        if ( canMergeWith(other) ) {
            value <<= 1;
            merged = true;
            return value;
        }
        return -1;
    }

    void setMerged( final boolean merged ) {
        this.merged = merged;
    }

    @Override
    public
    String toString() {
        return Integer.toString(value);
    }
}

