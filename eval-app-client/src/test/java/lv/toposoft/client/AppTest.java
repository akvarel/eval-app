package lv.toposoft.client;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import lv.toposoft.client.api.EvalApi;
import lv.toposoft.client.common.ApiException;
import lv.toposoft.client.model.Script;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testAppPostScript(){
    	assertTrue(postScript()!=null);
    }
    
    public void testAppGetScript(){
    	String id = "51f1202de4b0df7dd7f29ef2";
    	getScriptPrivate(id);
    }
    
    private String postScript(){
    	Script body = new Script();
    	body.setScriptBody("function test(d) { var i=d*5; return i;} test(55)");
    	body.setScriptType("javascript");
    	String _id = null;
    	try {
    		body = new EvalApi().createScript(body);
    		_id = body.getId();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
    	System.out.println("ID return:" + _id);
    	return _id;    	
    }
    
    private Script getScriptPrivate(String id){
    	Script body = null;
		try {
			body = new EvalApi().getScriptById(id);
			assertEquals("function test(d) { var i=d*5; return i;} test(55)", body.getScriptBody());
			assertEquals("javascript", body.getScriptType());
			assertEquals(new Double(55*5).doubleValue(), Double.parseDouble(body.getEvalResult()));
			assertEquals(id, body.getId());
		} catch (ApiException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			assertTrue(false);
		}
		return body;
    }
    
    public void testAppSystem(){
    	String id = postScript();
    	Script s = getScriptPrivate(id);
    	assertEquals(id, s.getId());
    }
}
