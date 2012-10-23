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




public class CodeUploaderTest  {

	
	@Test(invocationCount=1)
	public void testCompomentModification() throws Exception{
		generateCode("src/test/resources/specificationwithcodecompoments.xml");
		String newUploadedText="This text for example";
		String newContent=modifyGeneratedCode("target/javagensrc/ingenias/jade/components/Determine_availabilityTask.java",
				newUploadedText);
		String codeCompomentName=getComponentName(newContent);
		uploadSpec("src/test/resources/specificationwithcodecompoments.xml", 
				"target/javagensrc/",
				"target/newspecs/modifiedtestCompomentModification.xml");
		
		loadSpecAndCheckModification("target/newspecs/modifiedtestCompomentModification.xml",codeCompomentName, newUploadedText);
		loadSpecAndCheckNotModified("src/test/resources/specificationwithcodecompoments.xml",codeCompomentName, newUploadedText);
		finishScreens();
	}

	private void finishScreens() {
		for (Frame f:Frame.getFrames()){
			f.dispose();
		}
	}

	private String getComponentName(String newContent) {
		String idInitialSeq="//#start_node:";
		int indexStart=	newContent.indexOf(idInitialSeq)+idInitialSeq.length();
		int endOfTag=newContent.indexOf("<--",indexStart);
		return newContent.substring(indexStart,endOfTag).trim();
	}

	private void loadSpecAndCheckModification(String spec, String codecompname, String expectedContent) throws UnknowFormat, DamagedFormat, CannotLoad {
		Browser bi=BrowserImp.initialise(spec);
		GraphEntity entity = bi.findEntity(codecompname);
		assertTrue ("There is no entity named "+codecompname+" in spec file "+spec,entity!=null);
		try {
			assertTrue ("The entity "+codecompname+" in spec file "+spec+ " should have as content \""+expectedContent+"\" and it has \""+entity.getAttributeByName("code").getSimpleValue()+"\"",
					entity.getAttributeByName("code").getSimpleValue().equalsIgnoreCase(expectedContent));
		} catch (NotFound e) {
			fail("The entity "+codecompname+" in spec file "+spec+ " should have an attributed named \"code\"");
		}
		
	}
	
	private void loadSpecAndCheckNotModified(String spec, String codecompname, String expectedContent) throws UnknowFormat, DamagedFormat, CannotLoad {
		Browser bi=BrowserImp.initialise(spec);
		GraphEntity entity = bi.findEntity(codecompname);
		assertTrue ("There is no entity named "+codecompname+" in spec file "+spec,entity!=null);
		try {
			assertTrue ("The entity "+codecompname+" in spec file "+spec+ 
					" should have as content \""+expectedContent+"\" and it has \""+
					entity.getAttributeByName("code").getSimpleValue()+"\"",
					!entity.getAttributeByName("code").getSimpleValue().equalsIgnoreCase(expectedContent));
		} catch (NotFound e) {
			fail("The entity "+codecompname+" in spec file "+spec+ " should have an attributed named \"code\"");
		}
		
	}


	private void uploadSpec(String originalFile, String folderToConsider, String newFile)  {
		ingenias.module.CodeUploader.main(new String[]{originalFile,folderToConsider,newFile});
		
	}

	private String modifyGeneratedCode(String filename, String newcontent) throws FileNotFoundException, IOException {
		try {
			String content=ingenias.generator.util.FileUtils.readFile(filename).toString();

			int indexStart=content.indexOf(
					"\n",
					content.indexOf("//#start_node:"))+1;	
			assertTrue("There should be a line in the file "+filename+" containing the string \"//#start_node:\"", indexStart>0);
			int indexEnd=content.indexOf("//#end_node");
			assertTrue("There should be a line in the file "+filename+" containing the string \"//#end_node\"", indexStart>0);
			assertTrue("The //#start_node: substring should be placed before the //#end_node substring in the file "+filename+"", indexStart<indexEnd);
			content=content.substring(0,indexStart)+
					newcontent+"\n"+
					content.substring(indexEnd,content.length());

			FileOutputStream fos = new FileOutputStream(filename);
			fos.write(content.getBytes());
			fos.close();
			return content;
		} catch (java.io.FileNotFoundException t){
			t.printStackTrace();
			fail("The file "+filename+" was not found");
		} 
		return "";
	}

	
	
	private void generateCode(String specfile) {
		try {
			ingenias.codeproc.IAFGenerator.main(
					new String[]{specfile,
							"target",
							"javagensrc",
							"javapermsrc",
					"true"});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(invocationCount=1)
	public void testNonExistingCompomentModification() throws FileNotFoundException, IOException, UnknowFormat, DamagedFormat, CannotLoad {
		String resultingSpec="target/newspecs/modifiedtestCompomentModification.xml";
		new File(resultingSpec).delete();
		generateCode("src/test/resources/specificationwithcodecompoments.xml");
		// no changes are made
		uploadSpec("src/test/resources/specificationwithcodecompoments.xml", 
				"target/javagensrc/",
				resultingSpec);
		assertTrue("There should be no changes in the spec. A new spec has been generated in "+resultingSpec,
				!(new File(resultingSpec).exists()));
		
		/*Browser bimp1=BrowserImp.initialise("target/newspecs/modifiedtestCompomentModification.xml");
		Browser bimp2=BrowserImp.initialise("src/test/resources/specificationwithcodecompoments.xml");
		
		assertTrue("A spec not triggering changes should remain the same. " +
				"Files (A) target/newspecs/modifiedtestCompomentModification.xml  and " +
				"(B) src/test/resources/specificationwithcodecompoments.xml \n Differences are (A) in (B):"+
				BrowserImp.findAllDifferences(bimp1,bimp2)+
				" \n Differences (B) in (A) "+BrowserImp.findAllDifferences(bimp2,bimp1), 
				BrowserImp.compare(bimp1,bimp2));*/
	}

	@Test(invocationCount=1)
	public void testNoExistingTags() throws UnknowFormat, DamagedFormat, CannotLoad{

		String resultingSpec="target/newspecs/modifiedtestCompomentModification.xml";
		new File(resultingSpec).delete();
		uploadSpec("src/test/resources/specificationwithcodecompoments.xml", 
				"src/test/resources/javasourceswithbadcompomenttags",
				resultingSpec);
		assertTrue("There should be no changes in the spec. A new spec has been generated in "+resultingSpec,
				!(new File(resultingSpec).exists()));
		
		/*Browser bimp1=BrowserImp.initialise(resultingSpec);
		Browser bimp2=BrowserImp.initialise("src/test/resources/specificationwithcodecompoments.xml");
		
		assertTrue("A spec not triggering changes should remain the same. " +
				"Files (A) target/newspecs/modifiedtestCompomentModification.xml  and " +
				"(B) src/test/resources/specificationwithcodecompoments.xml \n Differences are (A) in (B):"+
				BrowserImp.findAllDifferences(bimp1,bimp2)+
				" \n Differences (B) in (A) "+BrowserImp.findAllDifferences(bimp2,bimp1), 
				BrowserImp.compare(bimp1,bimp2));*/
	}

}
