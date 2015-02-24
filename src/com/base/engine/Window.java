/*
 * Copyright (C) 2015 Michael Browell <mbrowell1984@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.base.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public class Window 
{
	public static void CreateWindow(int width, int height, String title) {
            
		Display.setTitle(title);
		try  {
                    
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Keyboard.create();
			Mouse.create();
                        
		}  catch (LWJGLException e)  {
                    
			e.printStackTrace();
                        
		}
                
	}
	
	public static void Render() {
            
		Display.update();
                
	}
	
	public static void Dispose() {
            
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
                
	}
	
	public static boolean IsCloseRequested() {
            
		return Display.isCloseRequested();
                
	}
	
	public static int GetWidth() {
            
		return Display.getDisplayMode().getWidth();
                
	}
	
	public static int GetHeight() {
            
		return Display.getDisplayMode().getHeight();
                
	}
	
	public static String GetTitle() {
            
		return Display.getTitle();
                
	}

	public Vector2f GetCenter() {
            
		return new Vector2f(GetWidth()/2, GetHeight()/2);
                
	}
        
}
