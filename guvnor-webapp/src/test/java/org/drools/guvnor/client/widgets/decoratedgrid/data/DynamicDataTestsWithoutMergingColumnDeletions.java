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

import org.drools.guvnor.client.widgets.drools.decoratedgrid.CellValue;
import org.drools.guvnor.client.widgets.drools.decoratedgrid.data.Coordinate;
import org.junit.Test;

/**
 * Tests for DynamicData
 */
public class DynamicDataTestsWithoutMergingColumnDeletions extends BaseDynamicDataTests {

    @Test
    public void testIndexing_DataCoordinates() {

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

        c = data.get( 2 ).get( 0 ).getCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      0 );
        c = data.get( 2 ).get( 1 ).getCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      1 );
        c = data.get( 2 ).get( 2 ).getCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      2 );

        data.deleteColumn( 2 );
        assertEquals( data.size(),
                      3 );
        assertEquals( data.get( 0 ).size(),
                      2 );
        assertEquals( data.get( 1 ).size(),
                      2 );
        assertEquals( data.get( 2 ).size(),
                      2 );

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

        c = data.get( 2 ).get( 0 ).getCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      0 );
        c = data.get( 2 ).get( 1 ).getCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      1 );

    }

    @Test
    public void testIndexing_HtmlCoordinates() {

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

        c = data.get( 2 ).get( 0 ).getHtmlCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      0 );
        c = data.get( 2 ).get( 1 ).getHtmlCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      1 );
        c = data.get( 2 ).get( 2 ).getHtmlCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      2 );

        data.deleteColumn( 2 );
        assertEquals( data.size(),
                      3 );
        assertEquals( data.get( 0 ).size(),
                      2 );
        assertEquals( data.get( 1 ).size(),
                      2 );
        assertEquals( data.get( 2 ).size(),
                      2 );

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

        c = data.get( 2 ).get( 0 ).getHtmlCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      0 );
        c = data.get( 2 ).get( 1 ).getHtmlCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      1 );

    }

    @Test
    public void testIndexing_PhysicalCoordinates() {

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

        c = data.get( 2 ).get( 0 ).getPhysicalCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      0 );
        c = data.get( 2 ).get( 1 ).getPhysicalCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      1 );
        c = data.get( 2 ).get( 2 ).getPhysicalCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      2 );

        data.deleteColumn( 2 );
        assertEquals( data.size(),
                      3 );
        assertEquals( data.get( 0 ).size(),
                      2 );
        assertEquals( data.get( 1 ).size(),
                      2 );
        assertEquals( data.get( 2 ).size(),
                      2 );

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

        c = data.get( 2 ).get( 0 ).getPhysicalCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      0 );
        c = data.get( 2 ).get( 1 ).getPhysicalCoordinate();
        assertEquals( c.getRow(),
                      2 );
        assertEquals( c.getCol(),
                      1 );

    }

    @Test
    public void testIndexing_RowSpans() {

        CellValue< ? extends Comparable< ? >> cv;
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

        cv = data.get( 2 ).get( 0 );
        assertEquals( cv.getRowSpan(),
                      1 );
        cv = data.get( 2 ).get( 1 );
        assertEquals( cv.getRowSpan(),
                      1 );
        cv = data.get( 2 ).get( 2 );
        assertEquals( cv.getRowSpan(),
                      1 );

        data.deleteColumn( 2 );
        assertEquals( data.size(),
                      3 );
        assertEquals( data.get( 0 ).size(),
                      2 );
        assertEquals( data.get( 1 ).size(),
                      2 );
        assertEquals( data.get( 2 ).size(),
                      2 );

        cv = data.get( 0 ).get( 0 );
        assertEquals( cv.getRowSpan(),
                      1 );
        cv = data.get( 0 ).get( 1 );
        assertEquals( cv.getRowSpan(),
                      1 );

        cv = data.get( 1 ).get( 0 );
        assertEquals( cv.getRowSpan(),
                      1 );
        cv = data.get( 1 ).get( 1 );
        assertEquals( cv.getRowSpan(),
                      1 );

        cv = data.get( 2 ).get( 0 );
        assertEquals( cv.getRowSpan(),
                      1 );
        cv = data.get( 2 ).get( 1 );
        assertEquals( cv.getRowSpan(),
                      1 );

    }

}
