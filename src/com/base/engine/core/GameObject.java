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

import java.util.ArrayList;

/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public class GameObject {

    ArrayList<GameObject> m_children;
    ArrayList<GameComponent>m_components;
    
    private final Transform m_transform;
    
    public GameObject() {
        
        m_children = new ArrayList<>();
        m_components = new ArrayList<>();
        
        m_transform = new Transform();
        
    }
    
    public void addChild(GameObject child) {
        
        m_children.add(child);
        
    }
    
    public void addComponent(GameComponent component) {
        
        m_components.add(component);
        
    }
    
    public void input() {
        
        for(GameComponent component : m_components) {
            
            component.input(m_transform);
            
        }

        for(GameObject child : m_children) {
            
            child.input();
            
        }
        
    }
    
    public void update() {
        
        for(GameComponent component : m_components) {
            
            component.update(m_transform);
            
        }

        for(GameObject child : m_children) {
            
            child.update();
            
        }
        
    }
    
    public void render() {
        
        for(GameComponent component : m_components) {
            
            component.render(m_transform);
            
        }

        for(GameObject child : m_children) {
            
            child.render();
            
        }
        
    }

    public Transform getM_transform() {
        
        return m_transform;
        
    }
    
}
