/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.guvnor.client.widgets.decoratedgrid.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.drools.guvnor.client.widgets.drools.decoratedgrid.CellValue;
import org.drools.guvnor.client.widgets.drools.decoratedgrid.CellValue.CellState;
import org.drools.guvnor.client.widgets.drools.decoratedgrid.CellValue.GroupedCellValue;
import org.drools.guvnor.client.widgets.drools.decoratedgrid.data.Coordinate;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for DynamicData
 */
public class DynamicDataTestsWithGroupingWithOtherwise extends BaseDynamicDataTests {

    @Before
    public void setup() {
        super.setup();

        //Setup date to merge
        //[1][-][3]
        //[1][2][*]
        //[-][2][3]
        rows.get( 0 ).get( 0 ).setValue( "1" );
        rows.get( 0 ).get( 1 ).setValue( "-" );
        rows.get( 0 ).get( 2 ).setValue( "3" );

        rows.get( 1 ).get( 0 ).setValue( "1" );
        rows.get( 1 ).get( 1 ).setValue( "2" );
        rows.get( 1 ).get( 2 ).addState( CellState.OTHERWISE );

        rows.get( 2 ).get( 0 ).setValue( "-" );
        rows.get( 2 ).get( 1 ).setValue( "2" );
        rows.get( 2 ).get( 2 ).setValue( "3" );
    }

    @Test
    @SuppressWarnings("rawtypes")
    public void testDataGrouping() {
        //[1][-][3] --> [1][x][x]
        //[1][2][*] --> [-][2][3]
        //[-][2][3] -->
        GroupedCellValue gcv;
        CellValue< ? extends Comparable< ? >> cv = data.get( 0 ).get( 0 );

        data.setMerged( true );
        data.applyModelGrouping( cv );

        assertEquals( data.size(),
                      2 );

        cv = data.get( 0 ).get( 0 );
        assertTrue( cv instanceof GroupedCellValue );
        gcv = (GroupedCellValue) cv;
        assertFalse( gcv.hasMultipleValues() );
        assertEquals( cv.getValue(),
                      "1" );
        cv = data.get( 0 ).get( 1 );
        assertTrue( cv instanceof GroupedCellValue );
        gcv = (GroupedCellValue) cv;
        assertTrue( gcv.hasMultipleValues() );
        cv = data.get( 0 ).get( 2 );
        assertTrue( cv instanceof GroupedCellValue );
        gcv = (GroupedCellValue) cv;
        assertTrue( gcv.hasMultipleValues() );
        assertEquals( cv.getValue(),
                      "3" );

        cv = data.get( 1 ).get( 0 );
        assertFalse( cv instanceof GroupedCellValue );
        assertEquals( cv.getValue(),
                      "-" );
        cv = data.get( 1 ).get( 1 );
        assertFalse( cv instanceof GroupedCellValue );
        assertEquals( cv.getValue(),
                      "2" );
        cv = data.get( 1 ).get( 2 );
        assertFalse( cv instanceof GroupedCellValue );
        assertEquals( cv.getValue(),
                      "3" );

    }

    @Test
    public void testIndexing_DataCoordinates() {
        //[1][-][3] --> [1][x][x] --> [0,0][0,1][0,2]
        //[1][2][*] --> [-][2][3] --> [1,0][1,1][1,2]
        //[-][2][3] -->
        CellValue< ? extends Comparable< ? >> cv = data.get( 0 ).get( 0 );

        data.setMerged( true );
        data.applyModelGrouping( cv );

        assertEquals( data.size(),
                      2 );

        Coordinate c;
        c = data.get( 0 ).get( 0 ).getCoordinate();
        assertEquals( c.getRow(),
                      0 );
        assertEquals( c.getCol(),
                      0 );
        c = data.get( 0 ).get( 1 ).getCoordinate();
        assertEquals( c.getRow(),
                      0 );
        assertEquals( c.getCol(),
                      1 );
        c = data.get( 0 ).get( 2 ).getCoordinate();
        assertEquals( c.getRow(),
                      0 );
        assertEquals( c.getCol(),
                      2 );

        c = data.get( 1 ).get( 0 ).getCoordinate();
        assertEquals( c.getRow(),
                      1 );
        assertEquals( c.getCol(),
                      0 );
        c = data.get( 1 ).get( 1 ).getCoordinate();
        assertEquals( c.getRow(),
                      1 );
        assertEquals( c.getCol(),
                      1 );
        c = data.get( 1 ).get( 2 ).getCoordinate();
        assertEquals( c.getRow(),
                      1 );
        assertEquals( c.getCol(),
                      2 );

    }

    @Test
    public void testIndexing_HtmlCoordinates() {
        //[1][-][3] --> [1][x][x] --> [0,0][0,1][0,2]
        //[1][2][*] --> [-][2][3] --> [1,0][1,1][1,2]
        //[-][2][3] -->
        CellValue< ? extends Comparable< ? >> cv = data.get( 0 ).get( 0 );

        data.setMerged( true );
        data.applyModelGrouping( cv );

        assertEquals( data.size(),
                      2 );

        Coordinate c;
        c = data.get( 0 ).get( 0 ).getHtmlCoordinate();
        assertEquals( c.getRow(),
                      0 );
        assertEquals( c.getCol(),
                      0 );
        c = data.get( 0 ).get( 1 ).getHtmlCoordinate();
        assertEquals( c.getRow(),
                      0 );
        assertEquals( c.getCol(),
                      1 );
        c = data.get( 0 ).get( 2 ).getHtmlCoordinate();
        assertEquals( c.getRow(),
                      0 );
        assertEquals( c.getCol(),
                      2 );

        c = data.get( 1 ).get( 0 ).getHtmlCoordinate();
        assertEquals( c.getRow(),
                      1 );
        assertEquals( c.getCol(),
                      0 );
        c = data.get( 1 ).get( 1 ).getHtmlCoordinate();
        assertEquals( c.getRow(),
                      1 );
        assertEquals( c.getCol(),
                      1 );
        c = data.get( 1 ).get( 2 ).getHtmlCoordinate();
        assertEquals( c.getRow(),
                      1 );
        assertEquals( c.getCol(),
                      2 );

    }

    @Test
    public void testIndexing_PhysicalCoordinates() {
        //[1][-][3] --> [1][x][x] --> [0,0][0,1][0,2] --> [0,0][0,1][0,2]
        //[1][2][*] --> [-][2][3] --> [1,0][1,1][1,2] --> [1,0][1,1][1,2]
        //[-][2][3] -->
        CellValue< ? extends Comparable< ? >> cv = data.get( 0 ).get( 0 );

        data.setMerged( true );
        data.applyModelGrouping( cv );

        assertEquals( data.size(),
                      2 );

        Coordinate c;
        c = data.get( 0 ).get( 0 ).getPhysicalCoordinate();
        assertEquals( c.getRow(),
                      0 );
        assertEquals( c.getCol(),
                      0 );
        c = data.get( 0 ).get( 1 ).getPhysicalCoordinate();
        assertEquals( c.getRow(),
                      0 );
        assertEquals( c.getCol(),
                      1 );
        c = data.get( 0 ).get( 2 ).getPhysicalCoordinate();
        assertEquals( c.getRow(),
                      0 );
        assertEquals( c.getCol(),
                      2 );

        c = data.get( 1 ).get( 0 ).getPhysicalCoordinate();
        assertEquals( c.getRow(),
                      1 );
        assertEquals( c.getCol(),
                      0 );
        c = data.get( 1 ).get( 1 ).getPhysicalCoordinate();
        assertEquals( c.getRow(),
                      1 );
        assertEquals( c.getCol(),
                      1 );
        c = data.get( 1 ).get( 2 ).getPhysicalCoordinate();
        assertEquals( c.getRow(),
                      1 );
        assertEquals( c.getCol(),
                      2 );

    }

    @Test
    public void testIndexing_RowSpans() {
        //[1][-][3] --> [1][x][x] --> [0,0][0,1][0,2] --> [1][1][1]
        //[1][2][*] --> [-][2][3] --> [1,0][1,1][1,2] --> [1][1][1]
        //[-][2][3] -->
        CellValue< ? extends Comparable< ? >> cv = data.get( 0 ).get( 0 );

        data.setMerged( true );
        data.applyModelGrouping( cv );

        assertEquals( data.size(),
                      2 );

        cv = data.get( 0 ).get( 0 );
        assertEquals( cv.getRowSpan(),
                      1 );
        cv = data.get( 0 ).get( 1 );
        assertEquals( cv.getRowSpan(),
                      1 );
        cv = data.get( 0 ).get( 2 );
        assertEquals( cv.getRowSpan(),
                      1 );

        cv = data.get( 1 ).get( 0 );
        assertEquals( cv.getRowSpan(),
                      1 );
        cv = data.get( 1 ).get( 1 );
        assertEquals( cv.getRowSpan(),
                      1 );
        cv = data.get( 1 ).get( 2 );
        assertEquals( cv.getRowSpan(),
                      1 );

    }

}
