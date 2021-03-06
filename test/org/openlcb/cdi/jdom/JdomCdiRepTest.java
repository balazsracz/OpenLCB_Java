package org.openlcb.cdi.jdom;

import org.junit.*;

import org.jdom2.*;

import org.openlcb.cdi.CdiRep;

/**
 * @author  Bob Jacobsen   Copyright 2011
 */
public class JdomCdiRepTest {
 
    @Test	
    public void testCtor() {
        new JdomCdiRep(null);
    }
    
    @Test	
    public void testGetIdent() {
        CdiRep rep = new JdomCdiRep(SampleFactory.getBasicSample());
        
        CdiRep.Identification id = rep.getIdentification();
        Assert.assertNotNull(id);
        
        Assert.assertEquals("mfg", "OpenLCB Prototype", id.getManufacturer());
        Assert.assertEquals("model", "Basic sketch", id.getModel());
        Assert.assertEquals("hardware", "Arduino (any)", id.getHardwareVersion());
        Assert.assertEquals("software", "0.4", id.getSoftwareVersion());
    }
    
    @Test	
    public void testMap() {
        Element e = new Element("map")
                    .addContent(new Element("relation")
                        .addContent(new Element("property").addContent("prop1"))
                        .addContent(new Element("value").addContent("val1"))
                    )
                    .addContent(new Element("relation")
                        .addContent(new Element("property").addContent("prop2"))
                        .addContent(new Element("value").addContent("val2"))
                    )
                    .addContent(new Element("relation")
                        .addContent(new Element("property").addContent("prop3"))
                        .addContent(new Element("value").addContent("val3"))
                    )
            ;
     
        JdomCdiRep.Map map = new JdomCdiRep.Map(e);
        
        Assert.assertEquals("prop1 value","val1",map.getEntry("prop1"));
        Assert.assertEquals("prop2 value","val2",map.getEntry("prop2"));
        Assert.assertEquals("prop3 value","val3",map.getEntry("prop3"));

        Assert.assertEquals("non-existant value",null,map.getEntry("propX"));
        
        Assert.assertEquals("prop1 key","prop1",map.getKey("val1"));
        Assert.assertEquals("prop2 key","prop2",map.getKey("val2"));
        Assert.assertEquals("prop3 key","prop3",map.getKey("val3"));

        java.util.List list = map.getKeys();
        Assert.assertNotNull(list);
        Assert.assertEquals("num keys", 3, list.size());
        Assert.assertEquals("key1", "prop1", list.get(0));
        Assert.assertEquals("key2", "prop2", list.get(1));
        Assert.assertEquals("key3", "prop3", list.get(2));
    }
    
    @Test	
    public void testSegments() {
        CdiRep rep = new JdomCdiRep(SampleFactory.getBasicSample());
        
        java.util.List list = rep.getSegments();
        
        Assert.assertEquals("len", 3, list.size());
        
        CdiRep.Segment segment;
        segment = (CdiRep.Segment)list.get(0);
        Assert.assertNotNull(segment);
        Assert.assertEquals("space", 0, segment.getSpace());
        Assert.assertEquals("name", "Content", segment.getName());
        Assert.assertEquals("description", "Variables for controlling general operation", segment.getDescription());
        
        segment = (CdiRep.Segment)list.get(1);
        Assert.assertNotNull(segment);
        Assert.assertEquals("space", 1, segment.getSpace());
        Assert.assertEquals("name", "Resets", segment.getName());
        Assert.assertEquals("description", "Memory locations controlling resets", segment.getDescription());
    }
        
    @Test	
    public void testGroupsInSegments() {
        CdiRep rep = new JdomCdiRep(SampleFactory.getBasicSample());
        
        java.util.List list = rep.getSegments();
        
        Assert.assertEquals("len", 3, list.size());
        
        CdiRep.Segment segment;
        segment = (CdiRep.Segment)list.get(0);
        Assert.assertNotNull(segment);
        
        java.util.List<CdiRep.Item> items = segment.getItems();
        Assert.assertNotNull(items);
        Assert.assertEquals("contents length",4,items.size());
    }

}
