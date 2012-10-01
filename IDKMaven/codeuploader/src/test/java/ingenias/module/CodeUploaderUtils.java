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

import java.awt.Frame;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CodeUploaderUtils {
	

	public static String modifyGeneratedCode(String filename, String newcontent) throws FileNotFoundException, IOException {
		try {
			String content=ingenias.generator.util.FileUtils.readFile(filename).toString();

			int indexStart=content.indexOf(
					"\n",
					content.indexOf("//#start_node:"))+1;	
			assertTrue("There should be a line in the file "+filename+" containing the string \"//#start_node:\"", indexStart>0);
			int indexEnd=content.indexOf("//#end_node")-1;
			assertTrue("There should be a line in the file "+filename+" containing the string \"//#end_node\"", indexStart>0);
			assertTrue("The //#start_node: substring should be placed before the //#end_node substring in the file "+filename+"", indexStart<indexEnd);
			content=content.substring(0,indexStart)+
					newcontent+
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

	
	
	public static void generateCode(String specfile) {
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

	public static void finishScreens() {
		for (Frame f:Frame.getFrames()){
			f.dispose();
		}
	}

	public static String getComponentName(String newContent) {
		String idInitialSeq="//#start_node:";
		int indexStart=	newContent.indexOf(idInitialSeq)+idInitialSeq.length();
		int endOfTag=newContent.indexOf("<--",indexStart);
		return newContent.substring(indexStart,endOfTag).trim();
	}

	public static void loadSpecAndCheckModification(String spec, String codecompname, String expectedContent) throws UnknowFormat, DamagedFormat, CannotLoad {
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
	
	public static void loadSpecAndCheckNotModified(String spec, String codecompname, String expectedContent) throws UnknowFormat, DamagedFormat, CannotLoad {
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


	public static void uploadSpec(String originalFile, String folderToConsider, String newFile)  {
		ingenias.module.CodeUploader.main(new String[]{originalFile,folderToConsider,newFile});
		
	}

}
