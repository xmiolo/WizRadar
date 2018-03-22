package br.wiz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class Util {
	public boolean existVariable(String v) {
		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
			if(envName.equals(v)) {
				return true;
			} 
		    //System.out.println(envName +"  "+ env.get(envName));
		} return false;
	}

	public void build(String mavenDir) {
		 Process p = null;

	    try {
	        p = Runtime.getRuntime().exec(mavenDir+"/bin/ mvn verify install");
	    } catch (IOException e) {
	        System.err.println("Error on exec() method");
	        e.printStackTrace();
	    }

	   
	    try {
	    	try {
				copy(p.getInputStream(), System.out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void copy(InputStream in, OutputStream out) throws IOException {
	    while (true) {
	        int c = in.read();
	        if (c == -1)
	            break;
	        out.write((char) c);
	    }
	}
	
	
}
