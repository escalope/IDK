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




public class CodeUploaderModificationTest  {

	
	@Test(invocationCount=1)
	public void testCompomentModification() throws Exception{
		CodeUploaderUtils.generateCode("src/test/resources/specificationwithcodecompoments.xml");
		String newUploadedText="This text for example";
		String newContent=CodeUploaderUtils.modifyGeneratedCode("target/javagensrc/ingenias/jade/components/Determine_availabilityTask.java",
				newUploadedText);
		String codeCompomentName=CodeUploaderUtils.getComponentName(newContent);
		CodeUploaderUtils.uploadSpec("src/test/resources/specificationwithcodecompoments.xml", 
				"target/javagensrc/",
				"target/newspecs/modifiedtestCompomentModification.xml");
		
		CodeUploaderUtils.loadSpecAndCheckModification("target/newspecs/modifiedtestCompomentModification.xml",codeCompomentName, newUploadedText);
		CodeUploaderUtils.loadSpecAndCheckNotModified("src/test/resources/specificationwithcodecompoments.xml",codeCompomentName, newUploadedText);
	}

	


}
