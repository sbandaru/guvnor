/*
 * Copyright 2011 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.drools.guvnor.client.widgets.drools.decoratedgrid;

import java.util.ArrayList;
import java.util.List;

import org.drools.guvnor.client.widgets.drools.decoratedgrid.MergableGridWidget.CellSelectionDetail;
import org.drools.guvnor.client.widgets.drools.decoratedgrid.data.DynamicDataRow;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;

/**
 * Abstract grid, decorated with DecoratedGridHeaderWidget and
 * DecoratedGridSidebarWidget encapsulating basic operation: keyboard navigation
 * and column resizing.
 * 
 * @param <T>
 *            The type of domain columns represented by the Grid
 */
public abstract class DecoratedGridWidget<T> extends Composite {

    // Widgets for UI
    protected Panel                         mainPanel;
    protected Panel                         bodyPanel;
    protected ScrollPanel                   scrollPanel;
    protected MergableGridWidget<T>         gridWidget;
    protected DecoratedGridHeaderWidget<T>  headerWidget;
    protected DecoratedGridSidebarWidget<T> sidebarWidget;
    protected HasSystemControlledColumns    hasSystemControlledColumns;

    protected int                           height;
    protected int                           width;

    // Resources
    protected ResourcesProvider<T>          resources;

    /**
     * Construct at empty DecoratedGridWidget, without DecoratedGridHeaderWidget
     * or DecoratedGridSidebarWidget These should be set before the grid is
     * displayed using setHeaderWidget and setSidebarWidget respectively.
     */
    public DecoratedGridWidget(ResourcesProvider<T> resources) {
        this.resources = resources;

        mainPanel = getMainPanel();
        bodyPanel = getBodyPanel();
        gridWidget = getGridWidget();
        if ( mainPanel == null ) {
            throw new IllegalArgumentException( "mainPanel cannot be null" );
        }
        if ( bodyPanel == null ) {
            throw new IllegalArgumentException( "bodyPanel cannot be null" );
        }
        if ( gridWidget == null ) {
            throw new IllegalArgumentException( "gridWidget cannot be null" );
        }

        scrollPanel = new ScrollPanel();
        scrollPanel.add( gridWidget );
        scrollPanel.addScrollHandler( getScrollHandler() );

        initWidget( mainPanel );

        //Add handler for when the selected cell changes, to ensure the cell is visible
        gridWidget.addSelectedCellChangeHandler( new SelectedCellChangeHandler() {

            public void onSelectedCellChange(SelectedCellChangeEvent event) {
                cellSelected( event.getCellSelectionDetail() );
            }

        } );

    }

    /**
     * Append a column to the end of the column list
     * 
     * @param column
     * @param columnData
     * @param bRedraw
     *            Redraw the grid after the column has been appended
     */
    public void appendColumn(DynamicColumn<T> column,
                             List<CellValue< ? extends Comparable< ? >>> columnData,
                             boolean bRedraw) {
        insertColumnBefore( null,
                            column,
                            columnData,
                            bRedraw );
    }

    /**
     * Resize the DecoratedGridHeaderWidget and DecoratedGridSidebarWidget when
     * DecoratedGridWidget shows scrollbars
     */
    public void assertDimensions() {
        headerWidget.setWidth( scrollPanel.getElement().getClientWidth()
                               + "px" );
        sidebarWidget.setHeight( scrollPanel.getElement().getClientHeight()
                                 + "px" );
    }

    /**
     * Delete the given column
     * 
     * @param column
     * @param bRedraw
     */
    public void deleteColumn(DynamicColumn<T> column,
                             boolean bRedraw) {
        if ( column == null ) {
            throw new IllegalArgumentException( "Column cannot be null." );
        }
        gridWidget.deleteColumn( column,
                                 bRedraw );
        if ( bRedraw ) {
            headerWidget.redraw();
            assertDimensions();
        }
    }

    /**
     * Delete the given row
     * 
     * @param row
     */
    public void deleteRow(DynamicDataRow row) {
        if ( row == null ) {
            throw new IllegalArgumentException( "row cannot be null" );
        }
        sidebarWidget.deleteSelector( row );
        gridWidget.deleteRow( row );
        assertDimensions();
    }

    /**
     * Get the DecoratedGridWidget inner panel to which the
     * DecoratedGridHeaderWidget will be added. This allows subclasses to have
     * some control over the internal layout of the grid.
     * 
     * @return
     */
    public abstract Panel getBodyPanel();

    /**
     * Return the Widget responsible for rendering the DecoratedGridWidget
     * "grid".
     * 
     * @return
     */
    public abstract MergableGridWidget<T> getGridWidget();

    /**
     * Return the Widget responsible for rendering the DecoratedGridWidget
     * "header".
     * 
     * @return
     */
    public DecoratedGridHeaderWidget<T> getHeaderWidget() {
        return headerWidget;
    }

    /**
     * Return the DecoratedGridWidget outer most panel to which all child
     * widgets is added. This allows subclasses to have some control over the
     * internal layout of the grid.
     * 
     * @return
     */
    public abstract Panel getMainPanel();

    /**
     * Return the ScrollPanel in which the DecoratedGridWidget "grid" is nested.
     * This allows ScrollEvents to be hooked up to other defendant controls
     * (e.g. the Header).
     * 
     * @return
     */
    public abstract ScrollHandler getScrollHandler();

    /**
     * Return the Widget responsible for rendering the DecoratedGridWidget
     * "sidebar".
     * 
     * @return
     */
    public DecoratedGridSidebarWidget<T> getSidebarWidget() {
        return sidebarWidget;
    }

    /**
     * Insert a column before that specified
     * 
     * @param columnBefore
     * @param newColumn
     * @param columnData
     * @param bRedraw
     *            Redraw the grid after the column has been inserted
     */
    public void insertColumnBefore(DynamicColumn<T> columnBefore,
                                   DynamicColumn<T> newColumn,
                                   List<CellValue< ? extends Comparable< ? >>> columnData,
                                   boolean bRedraw) {

        if ( newColumn == null ) {
            throw new IllegalArgumentException( "newColumn cannot be null" );
        }
        if ( columnData == null ) {
            throw new IllegalArgumentException( "columnData cannot be null" );
        }
        gridWidget.insertColumnBefore( columnBefore,
                                       newColumn,
                                       columnData,
                                       bRedraw );

        // Redraw
        if ( bRedraw ) {
            headerWidget.redraw();
            assertDimensions();
        }
    }

    /**
     * Insert a row before that specified
     * 
     * @param rowBefore
     *            Row before which the new row will be inserted, or null in
     *            which case the row will be appended to the end
     * @param rowData
     *            New row data
     */
    public void insertRowBefore(DynamicDataRow rowBefore,
                                List<CellValue< ? extends Comparable< ? >>> rowData) {

        if ( rowData == null ) {
            throw new IllegalArgumentException( "rowData cannot be null" );
        }

        DynamicDataRow row = gridWidget.insertRowBefore( rowBefore,
                                                         rowData );
        sidebarWidget.insertSelector( row );
        assertDimensions();
    }

    /**
     * Redraw any columns that have their values programmatically manipulated
     */
    public void redrawSystemControlledColumns() {
        final List<DynamicColumn<T>> columns = gridWidget.getColumns();
        for ( DynamicColumn< ? > col : columns ) {
            if ( col.isSystemControlled() ) {
                gridWidget.redrawColumn( col.getColumnIndex() );
            }
        }
    }

    /**
     * Set the visibility of a column
     * 
     * @param index
     *            The index of the column to hide
     * @param isVisible
     *            true if the column is to be visible
     */
    public void setColumnVisibility(int index,
                                    boolean isVisible) {

        final List<DynamicColumn<T>> columns = gridWidget.getColumns();

        if ( index < 0
             || index > columns.size() ) {
            throw new IllegalArgumentException(
                                                "Column index must be greater than zero and less than then number of declared columns." );
        }

        if ( isVisible
             && !columns.get( index ).isVisible() ) {
            columns.get( index ).setVisible( isVisible );
            gridWidget.getData().setColumnVisibility( index,
                                                      isVisible );
            gridWidget.showColumn( index );
            headerWidget.redraw();
        } else if ( !isVisible
                    && columns.get( index ).isVisible() ) {
            columns.get( index ).setVisible( isVisible );
            gridWidget.getData().setColumnVisibility( index,
                                                      isVisible );
            gridWidget.hideColumn( index );
            headerWidget.redraw();
        }
    }

    /**
     * Some implementations may require the values of cells within the
     * DecoratedGridWidget to be programmatically manipulated (such as
     * "Row Number", which has to be recalculated after a sort operation). Such
     * implementations can register themselves here to receive requests to
     * update cell values when necessary (currently only after a sort).
     * 
     * @param hasSystemControlledColumns
     */
    public void setHasSystemControlledColumns(HasSystemControlledColumns hasSystemControlledColumns) {
        this.hasSystemControlledColumns = hasSystemControlledColumns;
    }

    /**
     * Set the Header Widget and attach resize handlers to GridWidget to support
     * column resizing and to resize GridWidget's ScrollPanel when header
     * resizes.
     * 
     * @param headerWidget
     */
    public void setHeaderWidget(DecoratedGridHeaderWidget<T> headerWidget) {
        if ( headerWidget == null ) {
            throw new IllegalArgumentException( "headerWidget cannot be null" );
        }
        this.headerWidget = headerWidget;
        headerWidget.addColumnResizeHandler( new ColumnResizeHandler() {

            public void onColumnResize(ColumnResizeEvent event) {

                // Resizing columns can cause the scrollbar to appear
                assertDimensions();
                gridWidget.resizeColumn( event.getColumn(),
                                         event.getWidth() );
            }

        } );
        this.headerWidget.addResizeHandler( new ResizeHandler() {

            public void onResize(ResizeEvent event) {
                scrollPanel.setHeight( (height - event.getHeight())
                                       + "px" );
                assertDimensions();
            }
        } );

        bodyPanel.add( headerWidget );
        bodyPanel.add( scrollPanel );
    }

    /**
     * This should be used instead of setHeight(String) and setWidth(String) as
     * various child Widgets of the DecisionTable need to have their sizes set
     * relative to the outermost Widget (i.e. this).
     */
    @Override
    public void setPixelSize(int width,
                             int height) {
        if ( width < 0 ) {
            throw new IllegalArgumentException( "width cannot be less than zero" );
        }
        if ( height < 0 ) {
            throw new IllegalArgumentException( "height cannot be less than zero" );
        }
        super.setPixelSize( width,
                            height );
        this.height = height;
        setHeight( height );
        setWidth( width );
    }

    /**
     * Set the SidebarWidget and attach a ResizeEvent handler to the Sidebar for
     * when the header changes size and the Sidebar needs to be redrawn to align
     * correctly. Also attach a RowGroupingChangeEvent handler to the
     * MergableGridWidget so the Sidebar can redraw itself when rows are merged,
     * grouped, ungrouped or unmerged.
     * 
     * @param sidebarWidget
     */
    public void setSidebarWidget(final DecoratedGridSidebarWidget<T> sidebarWidget) {
        if ( sidebarWidget == null ) {
            throw new IllegalArgumentException( "sidebarWidget cannot be null" );
        }
        this.sidebarWidget = sidebarWidget;
        this.headerWidget.addResizeHandler( new ResizeHandler() {

            public void onResize(ResizeEvent event) {
                sidebarWidget.resizeSidebar( event.getHeight() );
            }

        } );
        this.gridWidget.addRowGroupingChangeHandler( new RowGroupingChangeHandler() {

            public void onRowGroupingChange(RowGroupingChangeEvent event) {
                sidebarWidget.redraw();
            }

        } );

        this.mainPanel.add( sidebarWidget );
        this.mainPanel.add( bodyPanel );
    }

    /**
     * Sort data based upon information stored in Columns
     */
    public void sort() {

        //Extract list of sort information
        List<SortConfiguration> sortConfig = new ArrayList<SortConfiguration>();
        List<DynamicColumn<T>> columns = gridWidget.getColumns();
        for ( DynamicColumn<T> column : columns ) {
            SortConfiguration sc = column.getSortConfiguration();
            if ( sc.getSortIndex() != -1 ) {
                sortConfig.add( sc );
            }
        }

        gridWidget.getData().sort( sortConfig );

        //Redraw whole table
        gridWidget.redraw();
        sidebarWidget.redraw();
    }

    //Ensure the selected cell is visible
    private void cellSelected(CellSelectionDetail ce) {

        //Left extent
        if ( ce.getOffsetX() < scrollPanel.getHorizontalScrollPosition() ) {
            scrollPanel.setHorizontalScrollPosition( ce.getOffsetX() );
        }

        //Right extent
        int scrollWidth = scrollPanel.getElement().getClientWidth();
        if ( ce.getOffsetX() + ce.getWidth() > scrollWidth + scrollPanel.getHorizontalScrollPosition() ) {
            int delta = ce.getOffsetX() + ce.getWidth() - scrollPanel.getHorizontalScrollPosition() - scrollWidth;
            scrollPanel.setHorizontalScrollPosition( scrollPanel.getHorizontalScrollPosition() + delta );
        }

        //Top extent
        if ( ce.getOffsetY() < scrollPanel.getScrollPosition() ) {
            scrollPanel.setScrollPosition( ce.getOffsetY() );
        }

        //Bottom extent
        int scrollHeight = scrollPanel.getElement().getClientHeight();
        if ( ce.getOffsetY() + ce.getHeight() > scrollHeight + scrollPanel.getScrollPosition() ) {
            int delta = ce.getOffsetY() + ce.getHeight() - scrollPanel.getScrollPosition() - scrollHeight;
            scrollPanel.setScrollPosition( scrollPanel.getScrollPosition() + delta );
        }

    }

    // Set height of outer most Widget and related children
    private void setHeight(final int height) {
        mainPanel.setHeight( height
                             + "px" );

        // The Sidebar and Header sizes are derived from the ScrollPanel
        Scheduler.get().scheduleFinally( new ScheduledCommand() {

            public void execute() {
                assertDimensions();
            }

        } );
    }

    // Set width of outer most Widget and related children
    private void setWidth(int width) {
        mainPanel.setWidth( width
                            + "px" );
        scrollPanel.setWidth( (width - resources.sidebarWidth())
                              + "px" );

        // The Sidebar and Header sizes are derived from the ScrollPanel
        Scheduler.get().scheduleFinally( new ScheduledCommand() {

            public void execute() {
                assertDimensions();
            }

        } );
    }

}
