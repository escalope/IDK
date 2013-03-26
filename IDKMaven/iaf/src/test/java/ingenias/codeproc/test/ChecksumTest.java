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
package ingenias.codeproc.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.testng.Assert;
import org.testng.annotations.Test;



import ingenias.codeproc.IAFGenerator;


public class ChecksumTest {

	@Test
	public void testCheckSum() throws FileNotFoundException, IOException, NoSuchAlgorithmException{
		String testingString="This is an example";
		byte[] checksum = IAFGenerator.getCheckSum(testingString);
		IAFGenerator.storeChecksum("test",checksum);
		byte[] lastChecksum = IAFGenerator.getLastCheckSum("test");
		Assert.assertTrue(java.util.Arrays.equals(lastChecksum,checksum));
	}
}
