/* @(#)AccessibilityPanel.java	1.3 98/09/01
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
import javax.accessibility.*;

abstract public class AccessibilityPanel extends Panel {
    
    public AccessibilityPanel() {
        super();
    }
    
    abstract public void updateInfo(AccessibleContext ac, Point p);
}
