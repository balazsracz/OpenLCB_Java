// SimpleNodeIdent.java

package org.openlcb;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Node Ident Protocol 
 *
 * @see             "http://www.openlcb.org/trunk/specs/drafts/GenProtocolIdS.pdf"
 * @author			Bob Jacobsen   Copyright (C) 2012
 * @version			$Revision: 18542 $
 *
 */
public class SimpleNodeIdent {
    
    public SimpleNodeIdent( SimpleNodeIdentInfoReplyMessage msg) {
        byte data[] = msg.getData();
        for (int i = 0; i < data.length ; i++ ) {
           bytes[next++] = (byte)data[i];
        }
    }
    public SimpleNodeIdent() {
    }

    byte[] bytes = new byte[128];
    int next = 0;
   
    public void addMsg(SimpleNodeIdentInfoReplyMessage msg) {
        byte data[] = msg.getData();
        for (int i = 0; i < data.length ; i++ ) {
           bytes[next++] = (byte)data[i];
        }
    }
        
    public String getMfgName() {
        int len = 1;
        int start = 1;
        // skip mfg
        for (; len < bytes.length; len++)
            if (bytes[len] == 0) break;
       String s = new String(bytes,start, len-start);
       if (s == null) return "";
       else return s;
    }
    public String getModelName() {
        int len = 1;
        int start = 1;
        // skip mfg
        for (; len < bytes.length; len++)
            if (bytes[len] == 0) break;
        start = ++len;
        for (; len < bytes.length; len++)
            if (bytes[len] == 0) break;
       String s = new String(bytes,start, len-start);
       if (s == null) return "";
       else return s;
    }
    public String getHardwareVersion() {
        int len = 1;
        int start = 1;
        // skip mfg, model
        for (int i = 0; i<2; i++) {
            for (; len < bytes.length; len++)
                if (bytes[len] == 0) break;
            start = ++len;
        }
        
        // find this string
        for (; len < bytes.length; len++)
            if (bytes[len] == 0) break;
       String s = new String(bytes,start, len-start);
       if (s == null) return "";
       else return s;
    }

    public String getSoftwareVersion() {
        int len = 1;
        int start = 1;
        // skip mfg, model, hardware_version
        for (int i = 0; i<3; i++) {
            for (; len < bytes.length; len++)
                if (bytes[len] == 0) break;
            start = ++len;
        }
        
        // find this string
        for (; len < bytes.length; len++)
            if (bytes[len] == 0) break;
       String s = new String(bytes,start, len-start);
       if (s == null) return "";
       else return s;
    }

    public String getUserName() {
        int len = 1;
        int start = 1;
        // skip mfg, model, hardware_version, software_version
        for (int i = 0; i<4; i++) {
            for (; len < bytes.length; len++)
                if (bytes[len] == 0) break;
            start = ++len;
        }
        
        // find this string
        for (; len < bytes.length; len++)
            if (bytes[len] == 0) break;
       String s = new String(bytes,start, len-start);
       if (s == null) return "";
       else return s;
    }

    public String getUserDesc() {
        int len = 1;
        int start = 1;
        // skip mfg, model, hardware_version, software_version, user_name
        for (int i = 0; i<5; i++) {
            for (; len < bytes.length; len++)
                if (bytes[len] == 0) break;
            start = ++len;
        }
        
        // find this string
        for (; len < bytes.length; len++)
            if (bytes[len] == 0) break;
       String s = new String(bytes,start, len-start);
       if (s == null) return "";
       else return s;
    }

}
