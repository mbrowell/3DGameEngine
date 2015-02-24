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

import org.lwjgl.input.Keyboard;

/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public class Game {
    
    private final Mesh mesh;
    
    public Game() {
        
        mesh = new Mesh();
        
        Vertex[] data;
        data = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0)),
                             new Vertex(new Vector3f(0, 1, 0)),
                             new Vertex(new Vector3f(1, -1, 0))};
        
        mesh.addVertices(data);
        
    }
    
    public void input() {
        
        if(Input.getKeyDown(Keyboard.KEY_UP)) {
            
            System.out.println("We've just pressed a up!");
            
        }
        if(Input.getKeyUp(Keyboard.KEY_UP)) {
            
            System.out.println("We've just released up!");
            
        }
        
        if(Input.getMouseDown(1)) {
            
            System.out.println("We've just right clicked at " + Input.getMousePosition());
            
        }
        if(Input.getMouseUp(1)) {
            
            System.out.println("We've just released the right mouse button!");
            
        }
        
    }
    
    public void update() {
        
        
        
    }
    
    public void render() {
        
        mesh.draw();
        
    }
    
}
