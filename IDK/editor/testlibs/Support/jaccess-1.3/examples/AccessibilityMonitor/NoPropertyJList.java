/*
 * @(#)NoPropertyJList.java	1.2 98/09/01
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

import java.beans.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.accessibility.*;
import com.sun.java.accessibility.util.*;

    // A special JList which doesn't generate Accessible PropertyChange
    // events - else we'd stack overflow when whenever this JList
    // was updated - 'cause it'd cause a new PropertyChange event for
    // the AccessbleJList inner class, requiring another JList update,
    // generating a new PropertyChange event, requiring another...  well,
    // you get the idea.
    //
    public class NoPropertyJList extends JList {
	public NoPropertyJList(DefaultListModel dlm) {
	    super(dlm);
	}

	public AccessibleContext getAccessibleContext() {
	    if (accessibleContext == null) {
		accessibleContext = new AccessibleNoPropertyJList();
	    }
	    return accessibleContext;
	}

	// Override add/removePropertyChangeListener to do nothing.
	// Otherwise, leave the rest of the JList accessibility support
	// intact.
	//
	protected class AccessibleNoPropertyJList extends AccessibleJList {
	    public AccessibleNoPropertyJList() {
		super();
	    }
	    public void addPropertyChangeListener(PropertyChangeListener l) {
	    } 
	    public void removePropertyChangeListener(PropertyChangeListener l) {
	    }
	}
    }
