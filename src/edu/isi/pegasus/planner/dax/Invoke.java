/**
 *  Copyright 2007-2008 University Of Southern California
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package edu.isi.pegasus.planner.dax;

import edu.isi.pegasus.common.util.XMLWriter;

/**
 * The Notification invoke object for the Dax API
 * @author gmehta
 * @version $Revision$
 */
public class Invoke {

    /**
     * WHEN To INVOKE
     */
    public static enum WHEN {
        never, start, on_success, success, on_error, error, at_end, end, all
    };

    /**
     * WHen to Invoke
     */
    protected WHEN mWhen;
    /**
     * What to invoke
     */
    protected String mWhat;

    /**
     * Copy Constructor
     * @param i
     */
    public Invoke(Invoke i) {
        this(WHEN.valueOf(i.getWhen()), i.getWhat());
    }
    /**
     *  Crete a new Invoke object
     * @param when
     */
    public Invoke(WHEN when) {
        setWhen(when);
    }

    /**
     * Create a new Invoke object
     * @param when
     * @param what
     */
    public Invoke(WHEN when, String what) {
        setWhen(when);
        mWhat = what;
    }

    /**
     * Get when to Invoke
     * @return
     */
    public String getWhen() {
        return mWhen.toString();
    }

    /**
     * Set when to invoke
     * @param when
     * @return
     */
    public final Invoke setWhen(WHEN when) {
        if( when.equals( WHEN.at_end) ){
            this.mWhen = WHEN.end;
        }
        else if( when.equals( WHEN.on_error)){
            this.mWhen = WHEN.error;
        }
        else if( when.equals(WHEN.on_success)){
            this.mWhen = WHEN.success;
        }
        else{
            this.mWhen = when;
        }
        return this;
    }

    /**
     * Get what to invoke
     * @return
     */
    public String getWhat() {
        return mWhat;
    }

    /**
     * Set what executable to invoke and how
     * @param what
     * @return
     */
    public Invoke setWhat(String what) {
        mWhat = what;
        return this;
    }

    /**
     * Create a copy of this Invoke object
     * @return
     */
    public Invoke clone() {
        return new Invoke(this.mWhen, this.mWhat);
    }

    public void toXML(XMLWriter writer) {
        toXML(writer, 0);
    }

    public void toXML(XMLWriter writer, int indent) {
        writer.startElement("invoke", indent);
        writer.writeAttribute("when", mWhen.toString().toLowerCase());
        writer.writeData(mWhat).endElement();


    }
    
    /**
     * Returns the object as String
     * 
     * @return the description 
     */
    public String toString(){
    	StringBuffer sb = new StringBuffer();
    	sb.append( "[invoke " ).append( "when=\"" ).append( mWhen.toString().toLowerCase() ).
    	   append( "\"" ).append( " what=\"" ).append( mWhat ).append( "\"]" );
    	return sb.toString();
    }
}
