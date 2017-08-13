/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.util;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class OutputStream extends ObjectOutputStream {
    public OutputStream(OutputStream os) throws IOException{
        super(os);
    }
    
    @Override
    protected void writeStreamHeader() {
        
    }
}
