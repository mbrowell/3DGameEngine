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
package com.base.engine.core;

import com.base.engine.rendering.Window;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public class CoreEngine {
    
    private boolean m_isRunning;
    
    private final Game m_game;
    private RenderingEngine m_renderingEngine;
    
    private final int m_width;
    private final int m_height;
    
    private double m_frameTime;
    
    /**
     *
     * @param width
     * @param height
     * @param frameRate
     * @param game
     */
    public CoreEngine(int width, int height, double frameRate, Game game) {
        
        m_isRunning = false;
        
        m_game = game;
        
        m_width = width;
        m_height = height;
        
        m_frameTime = 1 / frameRate;
        
    }
    
    public void createWindow(String title) {
        
        Window.createWindow(m_width, m_height, title);
        m_renderingEngine = new RenderingEngine();
        
    }
    
    /**
     *
     */
    public void start() {
        
        if(m_isRunning) {
            
            return;
            
        }
            
        try {
            
            run();
            
        } catch (InterruptedException e) {
            
            Logger.getLogger(CoreEngine.class.getName()).log(Level.SEVERE, null, e);
            
        }
        
    }
    
    /**
     *
     */
    public void stop() {
        
        if(!m_isRunning) {
            
            return;
            
        }
        
        m_isRunning = false;
        
    }
    
    @SuppressWarnings("SleepWhileInLoop")
    private void run() throws InterruptedException {
        
        m_isRunning = true;
        
        int frames = 0;
        long frameCounter = 0;
        
        m_game.init();
        
        double lastTime = Time.getTime();
        double unprocessedTime = 0;
        
        while(m_isRunning) {
            
            boolean render = false;
            
            double startTime = Time.getTime();
            double passedTime = startTime - lastTime;
            lastTime = startTime;
            
            unprocessedTime += passedTime;
            frameCounter += passedTime;
            
            while(unprocessedTime > m_frameTime) {
                
                render = true;
                
                unprocessedTime -= m_frameTime;
                
                if(Window.isCloseRequested()) {
                
                    stop();
                
                }
                
                m_game.input((float)m_frameTime);
                m_renderingEngine.input((float)m_frameTime);
                Input.update();
                
                m_game.update((float)m_frameTime);
                
                if(frameCounter >= 1.0) {
                
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                    
                }
                
            }
            
            if(render) {
                
                m_renderingEngine.render(m_game.getRootObject());
                Window.render();
                frames++;
                
            } else {
                
                try {
                    
                    Thread.sleep(1);
                    
                } catch(InterruptedException e) {
                    
                    Logger.getLogger(CoreEngine.class.getName()).log(Level.SEVERE, null, e);
                    
                }
                
            }
                
            
        }
        
        cleanUp();
        
    }
    
    private void cleanUp() {
        
        Window.dispose();
        
    }
    
}
