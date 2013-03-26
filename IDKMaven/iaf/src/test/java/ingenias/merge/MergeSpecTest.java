/*
    Copyright (C) 2013 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/
package ingenias.merge;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import ingenias.codeproc.IAFMerger;
import ingenias.exception.CannotLoad;
import ingenias.exception.DamagedFormat;
import ingenias.exception.UnknowFormat;


import org.testng.annotations.Test;
import org.xml.sax.SAXException;


public class MergeSpecTest {
	@Test
	public void testExpressionEvaluator() throws SAXException, IOException, ParserConfigurationException, UnknowFormat, DamagedFormat, CannotLoad{
		IAFMerger.main(new String[]{"src/test/resources/mergetest/testmergefile.xml"});
	}
}
