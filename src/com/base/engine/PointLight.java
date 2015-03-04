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
public class PointLight {

    private BaseLight m_baseLight;
    private Attenuation m_atten;
    private Vector3f m_position;

    public PointLight(BaseLight baseLight, Attenuation atten, Vector3f position) {
        
        this.m_baseLight = baseLight;
        this.m_atten = atten;
        this.m_position = position;
        
    }

    public BaseLight getM_baseLight() {
        
        return m_baseLight;
        
    }

    public void setM_baseLight(BaseLight baseLight) {
        
        this.m_baseLight = baseLight;
        
    }

    public Attenuation getM_atten() {
        
        return m_atten;
        
    }

    public void setM_atten(Attenuation atten) {
        
        this.m_atten = atten;
        
    }

    public Vector3f getM_position() {
        
        return m_position;
    }

    public void setM_position(Vector3f position) {
        
        this.m_position = position;
        
    }
    
}
