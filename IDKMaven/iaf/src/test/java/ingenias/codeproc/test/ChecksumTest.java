package ingenias.codeproc.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.Assert;

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
