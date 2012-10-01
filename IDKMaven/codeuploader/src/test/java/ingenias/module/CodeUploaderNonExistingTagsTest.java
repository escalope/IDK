package ingenias.module;


import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

import ingenias.exception.CannotLoad;
import ingenias.exception.DamagedFormat;
import ingenias.exception.NotFound;
import ingenias.exception.UnknowFormat;
import ingenias.generator.browser.Browser;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.util.FileUtils;

import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;




public class CodeUploaderNonExistingTagsTest  {



	@Test(invocationCount=1)
	public void testNoExistingTags() throws UnknowFormat, DamagedFormat, CannotLoad{
		new File("target/newspecs/modifiedtestCompomentModification.xml").delete();
		CodeUploaderUtils.uploadSpec("src/test/resources/specificationwithcodecompoments.xml", 
				"src/test/resources/javasourceswithbadcompomenttags",
				"target/newspecs/modifiedtestCompomentModification.xml");
		assertTrue("There was no change to the original specification. No updated specification should" +
				"have been generated",
				!new File("target/newspecs/modifiedtestCompomentModification.xml").exists());
		/*Browser bimp1=BrowserImp.initialise("target/newspecs/modifiedtestCompomentModification.xml");
		Browser bimp2=BrowserImp.initialise("src/test/resources/specificationwithcodecompoments.xml");
		
		assertTrue("A spec not triggering changes should remain the same. " +
				"Files (A) target/newspecs/modifiedtestCompomentModification.xml  and " +
				"(B) src/test/resources/specificationwithcodecompoments.xml \n Differences are (A) in (B):"+
				BrowserImp.findAllDifferences(bimp1,bimp2)+
				" \n Differences (B) in (A) "+BrowserImp.findAllDifferences(bimp2,bimp1), 
				BrowserImp.compare(bimp1,bimp2));*/
	}

}
