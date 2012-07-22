package ingenias.merge;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import ingenias.codeproc.IAFMerger;
import ingenias.exception.CannotLoad;
import ingenias.exception.DamagedFormat;
import ingenias.exception.UnknowFormat;

import org.junit.Test;
import org.xml.sax.SAXException;


public class MergeSpecTest {
	@Test
	public void testExpressionEvaluator() throws SAXException, IOException, ParserConfigurationException, UnknowFormat, DamagedFormat, CannotLoad{
		IAFMerger.main(new String[]{"src/test/resources/mergetest/testmergefile.xml"});
	}
}
