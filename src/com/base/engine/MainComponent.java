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

/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public class MainComponent {
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "3d Game Engine";
    
    private boolean isRunning;
    
    public MainComponent() {
        
        isRunning = false;
        
    }
    
    public void Start() {
        
        Run();
        
    }
    
    public void Stop() {
        
        
        
    }
    
    private void Run() {
        
        
        
    }
    
    private void Render() {
        
        
    }
    
    private void CleanUp() {
        
        
        
    }
    
    public static void main(String[] args) {
        
        Window.CreateWindow(WIDTH, HEIGHT, TITLE);
        
        MainComponent game = new MainComponent();
        
        game.start();
        
    }
    
}
