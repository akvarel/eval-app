package lv.toposoft.app;

import org.bson.types.ObjectId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
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
    	Script script = new Script();
    	script.setId(new ObjectId().toStringMongod());
    	System.out.println(script);
    	
        assertTrue( new ObjectId(script.getId()).toString().equals(script.getId()) );
    }
    
    public void testAppSpring()
    {   
    	ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
    	MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");	  
    	if(!mongoOperation.collectionExists("script"))
    		mongoOperation.createCollection("script");
        assertTrue( mongoOperation.collectionExists("script") );
    }
    
    public void testAppMongo()
    {   
    	ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
    	MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");	  
    	if(!mongoOperation.collectionExists("script"))
    		mongoOperation.createCollection("script");
    	Script scr = new Script();
    	scr.setScriptBody("return 1");
    	mongoOperation.save(scr, "script");
    	System.out.print(scr.toString());
        assertTrue( !scr.getId().equals(null) );
    }
    
    public void testBSFreturnDouble(){
    	ScriptManager sm = new ScriptManager();
    	Script s = new Script();
    	s.setScriptBody("function a(b,c){return b+c;} a(3,6);");
    	sm.executeScript(s);
    	assertEquals(9.0, Double.parseDouble(s.getEvalResult()));
    }
    
    public void testBSFreturnString(){
    	ScriptManager sm = new ScriptManager();
    	Script s = new Script();
    	s.setScriptBody("function a(b,c){return b+c;} a('This is ','a test');");
    	sm.executeScript(s);
    	assertEquals("This is a test", s.getEvalResult());
    }
}
