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

import org.drools.ide.common.shared.workitems.PortableBooleanParameterDefinition;
import org.drools.ide.common.shared.workitems.PortableEnumParameterDefinition;
import org.drools.ide.common.shared.workitems.PortableFloatParameterDefinition;
import org.drools.ide.common.shared.workitems.PortableIntegerParameterDefinition;
import org.drools.ide.common.shared.workitems.PortableListParameterDefinition;
import org.drools.ide.common.shared.workitems.PortableObjectParameterDefinition;
import org.drools.ide.common.shared.workitems.PortableParameterDefinition;
import org.drools.ide.common.shared.workitems.PortableStringParameterDefinition;

/**
 * 
 */
public class WorkItemParameterWidgetFactory {

    public static WorkItemParameterWidget getWidget(PortableParameterDefinition ppd) {
        if ( ppd instanceof PortableBooleanParameterDefinition ) {
            return new WorkItemBooleanParameterWidget( (PortableBooleanParameterDefinition) ppd );
        }
        if ( ppd instanceof PortableEnumParameterDefinition ) {
            return new WorkItemEnumParameterWidget( (PortableEnumParameterDefinition) ppd );
        }
        if ( ppd instanceof PortableFloatParameterDefinition ) {
            return new WorkItemFloatParameterWidget( (PortableFloatParameterDefinition) ppd );
        }
        if ( ppd instanceof PortableIntegerParameterDefinition ) {
            return new WorkItemIntegerParameterWidget( (PortableIntegerParameterDefinition) ppd );
        }
        if ( ppd instanceof PortableListParameterDefinition ) {
            return new WorkItemListParameterWidget( (PortableListParameterDefinition) ppd );
        }
        if ( ppd instanceof PortableObjectParameterDefinition ) {
            return new WorkItemObjectParameterWidget( (PortableObjectParameterDefinition) ppd );
        }
        if ( ppd instanceof PortableStringParameterDefinition ) {
            return new WorkItemStringParameterWidget( (PortableStringParameterDefinition) ppd );
        }
        throw new IllegalArgumentException( "Unrecognized PortableParameterDefinition" );
    }

}
