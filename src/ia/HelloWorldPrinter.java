package ia;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;

public class HelloWorldPrinter  {


    public static void main(String args[]) throws IOException, InterruptedException {
 
    	File file = new File ("University of Cambridge Budget Report.txt");
    	Desktop desktop = Desktop.getDesktop();
    	desktop.open(file);
    	Thread.sleep(1000); // This is to give the document time to open so it is in focus to print 
    	
    	try {
    	Robot robot = new Robot();
        robot.setAutoDelay(250);
        String os = System.getProperty("os.name"); //Checks for user's OS type to ensure the correct key sequence is sent
        if(os.contains("Windows")) {
        	// Key sequence to print on Windows 
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_P);
	        //Keys are released otherwise they will be pressed until the program closes causing issues for the user
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_P);
	        robot.keyPress(KeyEvent.VK_ENTER);
        } else if(os.contains("Mac")) {
        	// Key sequence to print on MacOS
	        robot.keyPress(KeyEvent.VK_META);  //This is meant to simulate the command key
	        robot.keyPress(KeyEvent.VK_P);
	        robot.keyRelease(KeyEvent.VK_META);
	        robot.keyRelease(KeyEvent.VK_P);
	        robot.keyPress(KeyEvent.VK_ENTER);
        }
    } catch (AWTException ex) {
        ex.printStackTrace();
    }
    }
}
