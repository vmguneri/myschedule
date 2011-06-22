package myschedule.service;

import groovy.lang.GroovyShell;

import java.io.File;
import java.util.Map;

/** 
 * Provide Groovy scripting service to the scheduler.
 *
 * @author Zemian Deng
 */
public class GroovyScriptingService implements Service {

	public <T> T run(String scriptText, Map<String, Object> variables) {
		GroovyShell groovyShell = new GroovyShell();
		for (Map.Entry<String, Object> entry : variables.entrySet())
			groovyShell.setVariable(entry.getKey(), entry.getValue());
		Object object = groovyShell.evaluate(scriptText);
		@SuppressWarnings("unchecked")
		T ret = (T)object;
		return ret;
	}

	public <T> T runScript(File file, Map<String, Object> variables) {
		try {
			GroovyShell groovyShell = new GroovyShell();
			for (Map.Entry<String, Object> entry : variables.entrySet())
				groovyShell.setVariable(entry.getKey(), entry.getValue());
			Object object = groovyShell.evaluate(file);
			@SuppressWarnings("unchecked")
			T ret = (T)object;
			return ret;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void destroy() {
	}
	
}