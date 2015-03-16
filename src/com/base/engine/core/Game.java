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

/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public abstract class Game {
    
    private GameObject m_root;
    
    public void init() {
        
        
        
    }
    
    public void input() {
        
        getRootObject().input();
        
    }
    
    public void update() {
        
        getRootObject().update();
        
    }
    
    public GameObject getRootObject() {
        
        if(m_root == null) {
            
            m_root = new GameObject();
            
        }
        return m_root;
        
    }
    
}
