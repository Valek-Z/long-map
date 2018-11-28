package de.comparus.opensource.longmap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class LongMapImplTest {


    @Test
    public void putTested( ) {

        // When
        LongMap <LongMapImplTest.TestObject> map = new LongMapImpl <> ( );
        LongMapImplTest.TestObject value0 = new LongMapImplTest.TestObject ( );
        LongMapImplTest.TestObject value1 = new LongMapImplTest.TestObject ( );
        LongMapImplTest.TestObject value2 = new LongMapImplTest.TestObject ( );

        // Act
        map.put ( 1L,value0 ); //replaced by next
        map.put ( 2L,value2 );
        map.put ( 3L,null );
        LongMapImplTest.TestObject replaced = map.put ( 1L,value1 );

        // Assert
        assertThat ( "Should be replaced by same key",map.containsValue ( value0 ),is ( false ) );
        assertThat ( "Old value should be returned when replacing by new one",replaced,is ( value0 ) );
        assertThat ( map.get ( 1L ),is ( value1 ) );
        assertThat ( map.get ( 2L ),is ( value2 ) );
        assertThat ( map.get ( 3L ),nullValue ( ) );
        assertThat ( map.isEmpty ( ),is ( false ) );
        assertThat ( map.keys ( ),notNullValue ( ) );
        assertThat ( map.values ( ),notNullValue ( ) );
        assertThat ( 3L,is ( map.size ( ) ) );
    }

    @Test
    public void getTested( ) {

        // When
        LongMap <LongMapImplTest.TestObject> map = new LongMapImpl <> ( );
        LongMapImplTest.TestObject value = new LongMapImplTest.TestObject ( );
        map.put ( 1L,value );

        // Act
        LongMapImplTest.TestObject actual1 = map.get ( 1L );
        LongMapImplTest.TestObject actual2 = map.get ( 2L );

        // Assert
        assertThat ( actual1,is ( value ) );
        assertThat ( "Retrieving non existent key should return null",actual2,nullValue ( ) );


    }

    @Test
    public void GetValuesTested( ) {
        // When
        LongMap <String> map = new LongMapImpl <> ( );

        // Act
        map.put ( 224,"Help me" );
        map.put ( 2321,"Double trouble" );

        // Assert
        assertThat ( new List[]{map.values ( )},is ( new List[]{Arrays.asList ( "Help me","Double trouble" )} ) );
    }


    @Test
    public void removeTested( ) {
        // When
        LongMap <Double> map = new LongMapImpl <> ( );
        long key = 43;
        double value = 27.468767354;

        // Act
        map.put ( key,Double.valueOf ( value ) );
        double oldValue = map.remove ( key ).doubleValue ( );

        // Assert
        assertEquals ( oldValue,value,0.00000001 );
        assertNotEquals ( oldValue,27.4687674,0.00000001 );
        assertThat ( map.containsValue ( value ),is ( false ) );
        assertThat ( map.containsKey ( key ),is ( false ) );
        assertThat ( map.keys ( ),is ( new long[]{} ) );
        assertThat ( new ArrayList[]{new ArrayList ( )},is ( new ArrayList[]{( ArrayList ) map.values ( )} ) );

    }

    @Test
    public void isEmptyTested( ) {
        // When
        LongMap <LongMapImplTest.TestObject> map = new LongMapImpl <> ( );

        // Assert
        assertThat ( map.isEmpty ( ),is ( true ) );

        // when
        LongMapImplTest.TestObject value = new LongMapImplTest.TestObject ( );
        map.put ( 1L,value );

        // Assert
        assertThat ( map.isEmpty ( ),is ( false ) );
    }

    @Test
    public void containsKeyTested( ) {
        // When
        LongMap <LongMapImplTest.TestObject> map = new LongMapImpl <> ( );
        LongMapImplTest.TestObject value = new LongMapImplTest.TestObject ( );

        // Act
        map.put ( 1L,value );

        // Assert
        assertTrue ( map.containsKey ( 1L ) );
        assertFalse ( map.containsKey ( 2L ) );

    }

    @Test
    public void containsValueTested( ) {
        // When
        LongMap <LongMapImplTest.TestObject> map = new LongMapImpl <> ( );
        LongMapImplTest.TestObject value = new LongMapImplTest.TestObject ( );

        // Assert
        assertThat ( Boolean.valueOf ( map.containsValue ( value ) ),is ( Boolean.FALSE ) );

        // Act
        map.put ( 1L,value );

        // Assert
        assertThat ( Boolean.valueOf ( map.containsValue ( value ) ),is ( Boolean.TRUE ) );


    }

    @Test
    public void keysTested( ) {
        // When
        LongMap <LongMapImplTest.TestObject> map = new LongMapImpl <> ( );
        LongMapImplTest.TestObject value = new LongMapImplTest.TestObject ( );

        // Act
        long key = 1;
        map.put ( key,value );

        // Assert
        assertTrue ( map.keys ( ).length == 1 );
        assertTrue ( map.keys ( )[0] == key );


    }

    @Test
    public void valuesTested( ) {
        // When
        long key1 = 1, key2 = 2;
        LongMap <LongMapImplTest.TestObject> map = new LongMapImpl <> ( );
        LongMapImplTest.TestObject value1 = new LongMapImplTest.TestObject ( );
        LongMapImplTest.TestObject value2 = new LongMapImplTest.TestObject ( );

        // Act
        map.put ( key1,value1 );
        map.put ( key2,value2 );
        map.remove ( 1 );

        // Assert
        assertEquals ( null,map.get ( key1 ) );
        assertEquals ( value2,map.get ( key2 ) );
        assertEquals ( 1,map.keys ( ).length );
        assertEquals ( 1,map.values ( ).size ( ) );
        assertEquals ( 1,map.size ( ) );


    }

    @Test
    public void sizeTested( ) {
        // When
        LongMap <LongMapImplTest.TestObject> map = new LongMapImpl <> ( );

        // Assert
        assertEquals ( 0,map.size ( ) );

        // When
        LongMapImplTest.TestObject value = new LongMapImplTest.TestObject ( );
        map.put ( 1L,value );

        // Assert
        assertEquals ( 1,map.size ( ) );

    }

    @Test
    public void clearTested( ) {
        // When
        LongMap <Double> map = new LongMapImpl <> ( );

        // Act
        map.put ( 22,35. );
        map.put ( 141,3231.2 );
        map.clear ( );

        // Assert
        assertThat ( map.keys ( ),nullValue ( ) );
        assertThat ( map.values ( ),nullValue ( ) );
        assertThat ( map.isEmpty ( ),is ( Boolean.TRUE ) );
        assertEquals ( 0,map.size ( ) );
    }

    private class TestObject {
    }
}