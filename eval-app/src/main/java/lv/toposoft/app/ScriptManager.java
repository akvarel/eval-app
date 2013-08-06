package lv.toposoft.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import lv.toposoft.client.model.Script;

import org.apache.bsf.*; // BSF support

public class ScriptManager {
	private BSFManager manager;
	private Script script;
	
	public ScriptManager(){
		String[] extensions = {"py"};
		BSFManager.registerScriptingEngine("jpython","com.ibm.bsf.engines.jpython.JPythonEngine", extensions);
		manager = new BSFManager();
	}
	
	public void executeScript(Script s){
		setScript(s);
		executeScript();
	}
	
	public void executeScript(){
		if(script!=null){
			try {
				if(script.getScriptType()==null)
					script.setScriptType("javascript");
				
				if(script.getScriptType().equalsIgnoreCase("jpython"))
					manager.loadScriptingEngine ("jpython");
				
				ByteArrayOutputStream buffer = new ByteArrayOutputStream() ;
				PrintStream saveSystemOut = System.out ;
				System.setOut( new PrintStream( buffer ) ) ;

				Object result = manager.eval(script.getScriptType(), script.getId(), 0, 0, script.getScriptBody());
				script.setEvalResult(result.toString());
				System.setOut( saveSystemOut ) ; 
				String output = buffer.toString().trim();
				script.setOutput(output);
			}
			catch(BSFException e) {
				e.printStackTrace();
			}
		}	
	}

	public Script getScript() {
		return script;
	}

	public void setScript(Script script) {
		this.script = script;
	}
}
