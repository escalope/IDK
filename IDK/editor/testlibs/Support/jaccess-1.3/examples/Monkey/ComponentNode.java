/*
 * @(#)ComponentNode.java	1.6 98/09/01
 * 
 * Copyright (c) 1997 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 * 
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * 
 */

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*;
import javax.swing.tree.*;
import javax.accessibility.*;
import com.sun.java.accessibility.util.*;

/**
 * <P>ComponentTree extends TreeNode to add support for storing the component
 * represented by the node.
 *
 * @version     1.6 09/01/98 15:34:30
 * @author      Willie Walker
 * @author	Peter Korn
 */
public class ComponentNode extends DefaultMutableTreeNode
{
    public Component component = null;
    private String name;

    public ComponentNode(String name) {
        super();
        this.setUserObject(this);
        this.name = name;
    }

    public ComponentNode(Component component) {
        super();
        this.setUserObject(this);
        this.component = component;
        if (component instanceof Accessible) {
	    AccessibleContext ac = ((Accessible) component).getAccessibleContext();
	    if ((ac != null) 
		&& (ac.getAccessibleRole() == AccessibleRole.UNKNOWN)) {
                name = '!' + ac.getAccessibleName();
	    } else {
		if (ac != null) {
		    name = '*' + ac.getAccessibleName();
		} else {
		    name = component.getName();
		}
	    }
        } else {
	    name = component.getName();
        }
        name = name + " (" + component.getClass().toString() + ")";
    }

    public String toString() {
        return name;
    }
}
