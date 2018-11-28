package de.comparus.opensource.longmap;

import java.util.*;
import java.util.stream.IntStream;


public class LongMapImpl<V> implements LongMap <V> {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_INITIAL_CAPACITY = 32;
    private List <Note <V>>[] lists;
    private Set <Long> longSet;
    private Collection <V> vCollection;
    private int anInt;


    private static int hash( Long key ) {
        return Long.hashCode ( key );
    }

    public V put( long key,V value ) {
        if ( lists != null ) {
        } else {
            intList ( DEFAULT_INITIAL_CAPACITY );
        }

        V val = putEnter ( key,value );

        if ( ! ( anInt > ( lists.length * DEFAULT_LOAD_FACTOR ) ) ) {
            return val;
        }
        List <Note <V>>[] oldTable = Arrays.copyOf ( lists,lists.length );
        intList ( DEFAULT_INITIAL_CAPACITY * 2 );
        Arrays.stream ( oldTable ).filter ( Objects :: nonNull ).flatMap ( Collection :: stream ).forEach ( entrance -> {
            putEnter ( entrance.getKey ( ),entrance.getValue ( ) );
        } );
        return val;
    }

    private V putEnter( long key,V value ) {
        List <Note <V>> entries = lists[( lists.length - 1 ) & hash ( key )];
        V o = null;
        if ( longSet.contains ( key ) ) {
            for ( Note <V> entrance : entries ) {
                if ( entrance.getKey ( ).longValue ( ) == key ) {
                    o = entrance.setValue ( value );
                    break;
                }
            }
            vCollection.remove ( o );
        } else {
            entries.add ( new Note <> ( key,value ) );
            longSet.add ( key );
            anInt++;
        }
        vCollection.add ( value );
        return o;
    }

    private void intList( int capacity ) {
        anInt = 0;
        lists = new List[capacity];
        IntStream range = IntStream.range ( 0,lists.length );
        range.forEach ( i -> {
            lists[i] = new ArrayList <> ( );
        } );
        longSet = new HashSet <> ( );
        vCollection = new ArrayList <> ( );
    }

    public V get( long key ) {
        List <Note <V>> entries = lists[hash ( key ) & ( lists.length - 1 )];
        if ( entries == null ) {
            return null;
        }
        for ( Note <V> entrance : entries ) {
            if ( entrance.getKey ( ) == key ) {
                return entrance.getValue ( );
            }
        }
        return null;
    }

    public V remove( long key ) {
        List <Note <V>> entries = lists[hash ( key ) & ( lists.length - 1 )];
        for ( Note <V> entrance : entries ) {
            if ( entrance.getKey ( ).longValue ( ) == key ) {
                entries.remove ( entrance );
                vCollection.remove ( entrance.getValue ( ) );
                longSet.remove ( entrance.getKey ( ) );
                return entrance.getValue ( );
            }
        }
        return null;
    }

    public boolean isEmpty( ) {
        return lists == null ? true : false;
    }

    public boolean containsKey( long key ) {
        if ( vCollection == null ) return false;
        return longSet.contains ( key ) ? true : false;
    }

    public boolean containsValue( V value ) {
        if ( vCollection == null ) return false;
        return vCollection.contains ( value );
    }

    public long[] keys( ) {
        if ( longSet == null ) {
            return null;
        }
        long[] result = new long[longSet.size ( )];
        int i = 0;
        for ( Long key : longSet ) {
            result[i] = key;
            i++;
        }
        return result;
    }

    public List <V> values( ) {

        List <V> result = new ArrayList <> ( );
        if ( vCollection == null ) {
            return null;
        }

        for ( V value : vCollection ) {
            result.add ( value );
        }
        return result;
    }


    public long size( ) {
        return longSet == null ? 0 : longSet.size ( );
    }

    public void clear( ) {
        anInt = 0;
        longSet = null;
        vCollection = null;
        for ( List <Note <V>> list : lists ) {
            for ( Note <V> entrance : list ) {
                entrance = null;
            }
            list = null;
        }
        lists = null;
    }

    @Override
    public String toString( ) {
        return "LongMapImpl{" +
                "lists=" + Arrays.toString ( lists ) +
                ", longSet=" + longSet +
                ", vCollection=" + vCollection +
                ", anInt=" + anInt +
                '}';
    }
}


class Note<V> {
    private Long key;
    private V value;

    Note( Long key,V value ) {
        this.setKey ( key );
        this.setValue ( value );
    }

    Long getKey( ) {
        return key;
    }

    public void setKey( Long key ) {
        this.key = key;
    }

    V getValue( ) {
        return value;
    }

    V setValue( V value ) {
        V old = this.value;
        this.value = value;
        return old;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( ( o == null ) || ( getClass ( ) != o.getClass ( ) ) ) return false;
        Note <?> entrance = ( Note <?> ) o;
        return Objects.equals ( getKey ( ),entrance.getKey ( ) ) &&
                Objects.equals ( getValue ( ),entrance.getValue ( ) );
    }

    @Override
    public int hashCode( ) {
        return Objects.hash ( getKey ( ),getValue ( ) );
    }

    @Override
    public String toString( ) {
        return "Note{" +
                "key=" + getKey ( ) +
                ", value=" + getValue ( ) +
                '}';
    }
}



