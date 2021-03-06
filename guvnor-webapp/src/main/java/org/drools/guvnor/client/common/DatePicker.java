/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.guvnor.client.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.drools.guvnor.client.configurations.ApplicationPreferences;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;

abstract class DatePicker extends DirtyableComposite {

    protected DatePickerPopUp     datePickerPopUp;

    protected Panel               panel                 = new HorizontalPanel();
    protected TextBox             textWidget            = new TextBox();

    // Format that the text box uses.
    protected String              visualFormat          = "";
    // Format that the system uses.
    protected final static String defaultFormat         = ApplicationPreferences.getDroolsDateFormat();
    protected DateTimeFormat      visualFormatFormatter = null;

    protected List<ValueChanged>  valueChangeds         = new ArrayList<ValueChanged>();

    protected void solveVisualFormat(String visualFormat) {

        if ( visualFormat == null || visualFormat.equals( "default" ) || visualFormat.equals( "" ) ) {
            visualFormat = defaultFormat;
        }
        this.visualFormat = visualFormat;
    }

    public String getVisualFormat() {
        return this.visualFormat;
    }

    public String getDateString() {
        String t = textWidget.getText();
        Date date = this.visualFormatFormatter.parse( t );
        DateTimeFormat formatter = DateTimeFormat.getFormat( defaultFormat );
        return formatter.format( date );
    }

    /**
     * Set the date from the dropdowns
     * @return
     */
    protected Date fillDate() {
        Date date = visualFormatFormatter.parse( textWidget.getText() );

        // years
        date.setYear( Integer.parseInt( datePickerPopUp.years.getItemText( datePickerPopUp.years.getSelectedIndex() ) ) - 1900 );
        // months
        date.setMonth( datePickerPopUp.months.getSelectedIndex() );
        // days
        date.setDate( datePickerPopUp.dates.getSelectedIndex() + 1 );

        if ( datePickerPopUp.showTime ) {
            // hours
            date.setHours( datePickerPopUp.hours.getSelectedIndex() );
            // minutes
            date.setMinutes( datePickerPopUp.minutes.getSelectedIndex() );
        }
        return date;
    }

    public Date getDate() throws IllegalArgumentException {
        if ( textWidget.getText() == null || "".equals( textWidget.getText() ) ) {
            return null;
        } else {
            return this.visualFormatFormatter.parse( textWidget.getText().trim() );
        }
    }

    protected void valueChanged() {
        for ( ValueChanged changed : valueChangeds ) {
            changed.valueChanged( getDateString() );
        }
    }

    public void addValueChanged(ValueChanged listener) {
        valueChangeds.add( listener );
    }

    public void removeValueChanged(ValueChanged listener) {
        valueChangeds.remove( listener );
    }
}
