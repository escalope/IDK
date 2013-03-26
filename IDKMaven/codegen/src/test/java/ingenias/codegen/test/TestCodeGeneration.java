package ingenias.codegen.test;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringBufferInputStream;
import java.io.StringReader;

import javax.sound.midi.Sequence;

import ingenias.editor.Log;
import ingenias.exception.NotWellFormed;
import ingenias.generator.datatemplate.Repeat;
import ingenias.generator.datatemplate.Sequences;
import ingenias.generator.datatemplate.Var;
import ingenias.generator.interpreter.Codegen;
import ingenias.generator.interpreter.SplitHandler;
import ingenias.generator.util.FileUtils;



public class TestCodeGeneration {
	@Test
	public void testFileGeneration() throws NotWellFormed, Exception{

		Log.initInstance(new PrintWriter(System.out));
		String expectedContent="Estoesunaprueba";		
		String expectedFile="target/prueba.java";
		new File(expectedFile).delete();
		String goodFormat="<program>"+
				"<saveto>"+
					"<file overwrite=\"yes\">"+expectedFile+"</file>"+
					"<text>"+expectedContent+"</text>"+
				"</saveto>"+
				"</program>";
		
		SplitHandler sh=new SplitHandler(new StringBufferInputStream(goodFormat));
		AssertJUnit.assertTrue("There should not be any generated file named "+expectedFile,!new File(expectedFile).exists());		
		sh.writeFiles();
		AssertJUnit.assertTrue("There should t be a generated file named "+expectedFile ,new File(expectedFile).exists());
		StringBuffer content=FileUtils.readFile(expectedFile);
		
		AssertJUnit.assertTrue("The content should be "+expectedContent+" and it is "+content,
				content.toString().equals(expectedContent.toString()));
	}
	
	@Test
	public void testTemplateProcessing() throws NotWellFormed, Exception{

		Log.initInstance(new PrintWriter(System.out));
		String goodFormat="<program>"+
				"</program>";
		goodFormat=ingenias.generator.util.Conversor.convertTagToArroba(goodFormat);
		Codegen.applyArroba("<sequences/>", new BufferedInputStream(new StringBufferInputStream(goodFormat)));
		try {
			String badFormat=">something>"+
					"</program>";
			badFormat=ingenias.generator.util.Conversor.convertTagToArroba(badFormat);
			Codegen.applyArroba("<sequences/>", new BufferedInputStream(new StringBufferInputStream(badFormat)));
		}catch (NotWellFormed nwf){};
		try {
			String badFormat=">something>"+
					"</something>";
			badFormat=ingenias.generator.util.Conversor.convertTagToArroba(badFormat);
			Codegen.applyArroba("<sequences/>", 
					new BufferedInputStream(new StringBufferInputStream(badFormat)));
		}catch (NotWellFormed nwf){};
		
		goodFormat="<program>"+
				"<repeat id=\"a\"<"+
				"</repeat>"+
				"</program>";
		String expectedResult = "<program ></program>";
		Sequences seq=new Sequences();
		Repeat r=new Repeat("b");
		seq.addRepeat(r);			
		checkCodeGeneration(goodFormat, expectedResult, seq);
				
		
		goodFormat="<program>"+
				"<repeat id=\"a\">something"+
				"</repeat>"+
				"</program>";		 
		 expectedResult = "<program ></program>";
		checkCodeGeneration(goodFormat, expectedResult, seq);
		
		
		goodFormat="<program>"+
				"<repeat id=\"a\">something"+
				"</repeat>"+
				"</program>";
		 expectedResult = "<program >something</program>";		
		seq=new Sequences();
		r=new Repeat("a");
		seq.addRepeat(r);		
		checkCodeGeneration(goodFormat, expectedResult, seq);

		goodFormat="<program>"+
				"<repeat id=\"a\">something<v>myvar</v>"+
				"</repeat>"+
				"</program>"; 	
		expectedResult = "<program >something</program>";
		checkCodeGeneration(goodFormat, expectedResult, seq);
		
		r.add(new Var("myvar","somevalue"));	
		expectedResult = "<program >somethingsomevalue</program>";
		checkCodeGeneration(goodFormat, expectedResult, seq);
		
		goodFormat="<program>"+
				"<repeat id=\"a\">something<v>myvar</v>"+
				"</repeat>"+
				"<repeat id=\"a\">something<v>myvar</v>"+
				"</repeat>"+
				"</program>"; 
		expectedResult = "<program >somethingsomevaluesomethingsomevalue</program>";
		checkCodeGeneration(goodFormat, expectedResult, seq);
		
		Repeat r1=new Repeat("b");
		r1.add(new Var("myvarb","anothervalue"));
		r.add(r1);
		goodFormat="<program>"+
				"<repeat id=\"a\">something<v>myvar</v>"+
				"</repeat>"+
				"<repeat id=\"a\">something<v>myvar</v>"+
				"</repeat>"+
				"</program>"; 
		expectedResult = "<program >somethingsomevaluesomethingsomevalue</program>";
		checkCodeGeneration(goodFormat, expectedResult, seq);
		
		goodFormat="<program>"+
				"<repeat id=\"a\">something<v>myvar</v>"+"</repeat>"+
				"<repeat id=\"a\">something<v>myvar</v>"+
					"<repeat id=\"b\"><v@@@myvarb</v>"+"</repeat>"+
				"</repeat>"+
				"</program>"; 
		expectedResult = "<program >somethingsomevaluesomethingsomevalueanothervalue</program>";
		checkCodeGeneration(goodFormat, expectedResult, seq);
		
		goodFormat="<program>"+			
				"<repeat id=\"a\">something<v>myvar</v>"+
				"<repeat id=\"b\"><v>myvarb</v>"+"</repeat>"+
				"</repeat>"+
				"</program>"; 
		expectedResult = "<program >somethingsomevalueanothervalue</program>";
		checkCodeGeneration(goodFormat, expectedResult, seq);
		
		goodFormat="<program>"+			
				"<repeat id=\"a\">something<v>myvar</v>"+
					"<repeat id=\"b\"><v>myvarb</v>"+"</repeat>"+
				"</repeat>"+
				"<repeat id=\"a\">something<v>myvar</v>"+
					"<repeat id=\"b\"><v>myvarb</v>"+"</repeat>"+
				"</repeat>"+
				"</program>"; 
		expectedResult = "<program >somethingsomevalueanothervaluesomethingsomevalueanothervalue</program>";
		checkCodeGeneration(goodFormat, expectedResult, seq);
		
		goodFormat="<program>"+			
				"<repeat id=\"a\">something."+
					"<repeat id=\"b\"><v>myvar</v>,<v>myvarb</v>;"+"</repeat>"+
				"</repeat>"+
				"<repeat id=\"a\">something."+
					"<repeat id=\"b\"><v>myvar</v>,<v>myvarb</v>;"+"</repeat>"+
				"</repeat>"+
				"</program>"; 
		expectedResult = "<program >something.somevalue,anothervalue;something.somevalue,anothervalue;</program>";
		checkCodeGeneration(goodFormat, expectedResult, seq);
		
		r1=new Repeat("b");
		r1.add(new Var("myvarb","morevalue"));
		r.add(r1);
		goodFormat="<program>"+			
				"<repeat id=\"a\">something,"+
					"<repeat id=\"b\"><v>myvar</v>.<v>myvarb</v>;"+
					"</repeat>"+
				"</repeat>"+
				"<repeat id=\"a\">something,"+
					"<repeat id=\"b\"><v>myvar</v>.<v>myvarb</v>;"+
					"</repeat>"+
				"</repeat>"+
				"</program>"; 
		expectedResult = "<program >something,somevalue.anothervalue;somevalue.morevalue;"+
				"something,somevalue.anothervalue;somevalue.morevalue;</program>";
		checkCodeGeneration(goodFormat, expectedResult, seq);
		
		seq=new Sequences();
		r=new Repeat("a");
		seq.addRepeat(r);
		r1=new Repeat("b");
		r.add(r1);
		Repeat r2=new Repeat("c");
		r1.add(r2);
		Repeat r3=new Repeat("d");
		r2.add(r3);
		Repeat r4=new Repeat("e");
		r3.add(r4);
		r4.add(new Var("myvar","value"));
		goodFormat="<program>"+
				"<repeat id=\"a\">a,"+
					"<repeat id=\"b\">b,"+
						"<repeat id=\"c\">c,"+
							"<repeat id=\"d\">d,"+
								"<repeat id=\"e\">e"+
								"</repeat>"+
							"</repeat>"+
						"</repeat>"+
					"</repeat>"+
				"</repeat>"+
				"</program>";
		expectedResult = "<program >a,b,c,d,e</program>";
		
		checkCodeGeneration(goodFormat, expectedResult, seq);
		goodFormat="<program>"+
				"<repeat id=\"a\">a,"+
					"<repeat id=\"b\">b,"+
						"<repeat id=\"c\">c,"+
							"<repeat id=\"d\">d,"+
								"<repeat id=\"e\">e"+
								"</repeat>"+
								"<repeat id=\"e\">e"+
								"</repeat>"+								
							"</repeat>"+
						"</repeat>"+
					"</repeat>"+
				"</repeat>"+
				"</program>";
		expectedResult = "<program >a,b,c,d,ee</program>";
		checkCodeGeneration(goodFormat, expectedResult, seq);			

		goodFormat="<program>"+
				"<repeat id=\"b\">b,"+
				"</repeat>"+				
				"<repeat id=\"a\">a,"+				
					"<repeat id=\"b\">b,"+				
						"<repeat id=\"c\">c,"+
							"<repeat id=\"d\">d,"+
								"<repeat id=\"e\">e"+
								"</repeat>"+								
							"</repeat>"+
						 "</repeat>"+
					"</repeat>"+
				"</repeat>"+
				"</program>";
		expectedResult = "<program >a,b,c,d,e</program>";
		checkCodeGeneration(goodFormat, expectedResult, seq);		

		goodFormat="<program>"+		
				"<repeat id=\"a\">a,"+				
					"<repeat id=\"b\">b,"+
					 	"<repeat id=\"b\">b,"+	
					 		"<repeat id=\"c\">c,"+
					 			"<repeat id=\"d\">d,"+
					 				"<repeat id=\"e\">e"+
					 				"</repeat>"+								
					 			"</repeat>"+
					 		"</repeat>"+
					 	"</repeat>"+
					"</repeat>"+
				"</repeat>"+
				"</program>";
		expectedResult = "<program >a,b,</program>";
		checkCodeGeneration(goodFormat, expectedResult, seq);			
		
	}

	private void checkCodeGeneration(String goodFormat, String expectedResult,
			Sequences seq) throws NotWellFormed, Exception {
		goodFormat=ingenias.generator.util.Conversor.convertTagToArroba(goodFormat);
		File result=null;
		result=Codegen.processTemplateWithSequences(seq.toString(), 
				new BufferedInputStream(new StringBufferInputStream(goodFormat)));
		StringBuffer resultString=FileUtils.readFile(result.getAbsolutePath());
		AssertJUnit.assertTrue("The result ought to have been "+expectedResult+" and the result is \""+
				resultString.toString()+"\"",
				resultString.toString().equals(expectedResult));
	}
}
