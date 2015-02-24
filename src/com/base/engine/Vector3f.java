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
public class Vector3f {
    
	private float m_x;
	private float m_y;
	private float m_z;
	
	public Vector3f(float x, float y, float z) {
            
		this.m_x = x;
		this.m_y = y;
		this.m_z = z;
                
	}

	public float length() {
            
		return (float)Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z);
                
	}

	public float dot(Vector3f r) {
            
		return m_x * r.getX() + m_y * r.getY() + m_z * r.getZ();
                
	}
	
	public Vector3f cross(Vector3f r) {
            
		float x_ = m_y * r.getZ() - m_z * r.getY();
		float y_ = m_z * r.getX() - m_x * r.getZ();
		float z_ = m_x * r.getY() - m_y * r.getX();
		
		return new Vector3f(x_, y_, z_);
                
	}
	
	public Vector3f normalized() {
            
		float length = length();
		
		return new Vector3f(m_x / length, m_y / length, m_z / length);
                
	}

	public Vector3f Rotate() {
            
		return null;
	
        }

	public Vector3f add(Vector3f r) {
            
		return new Vector3f(m_x + r.getX(), m_y + r.getY(), m_z + r.getZ());
                
	}
	
	public Vector3f add(float r) {
            
		return new Vector3f(m_x + r, m_y + r, m_z + r);
                
	}
	
	public Vector3f subtract(Vector3f r) {
            
		return new Vector3f(m_x - r.getX(), m_y - r.getY(), m_z - r.getZ());
                
	}
	
	public Vector3f subtract(float r) {
            
		return new Vector3f(m_x - r, m_y - r, m_z - r);
                
	}
	
	public Vector3f multiply(Vector3f r) {
            
		return new Vector3f(m_x * r.getX(), m_y * r.getY(), m_z * r.getZ());
                
	}
	
	public Vector3f multiply(float r) {
            
		return new Vector3f(m_x * r, m_y * r, m_z * r);
                
	}
	
	public Vector3f divide(Vector3f r) {
            
		return new Vector3f(m_x / r.getX(), m_y / r.getY(), m_z / r.getZ());
                
	}
	
	public Vector3f divide(float r) {
            
		return new Vector3f(m_x / r, m_y / r, m_z / r);
                
	}

	public float getX() {
            
		return m_x;
                
	}

	public void setX(float x) {
            
		this.m_x = x;
                
	}

	public float getY() {
            
		return m_y;
                
	}

	public void setY(float y) {
            
		this.m_y = y;
                
	}

	public float getZ() {
            
		return m_z;
                
	}

	public void setZ(float z) {
            
		this.m_z = z;
                
	}
}
