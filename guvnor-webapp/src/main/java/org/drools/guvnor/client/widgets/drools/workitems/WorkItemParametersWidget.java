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
package org.drools.guvnor.client.widgets.drools.workitems;

import java.util.Set;

import org.drools.ide.common.shared.workitems.PortableParameterDefinition;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * A Widget to display a list of Work Item parameters
 */
public class WorkItemParametersWidget extends VerticalPanel {

    private Set<PortableParameterDefinition> parameters;

    public WorkItemParametersWidget() {
    }

    public WorkItemParametersWidget(Set<PortableParameterDefinition> parameters) {
        setParameters( parameters );
    }

    public void setParameters(Set<PortableParameterDefinition> parameters) {
        this.clear();
        this.parameters = parameters;
        for ( PortableParameterDefinition ppd : parameters ) {
            WorkItemParameterWidget pw = WorkItemParameterWidgetFactory.getWidget( ppd );
            add( pw );
        }
    }

    public Set<PortableParameterDefinition> getParameters() {
        return this.parameters;
    }

}
