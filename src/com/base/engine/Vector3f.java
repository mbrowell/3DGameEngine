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

	public float Length() {
            
		return (float)Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z);
                
	}

	public float Max() {
            
		return Math.max(m_x, Math.max(m_y, m_z));
                
	}

	public float Dot(Vector3f r) {
            
		return m_x * r.GetX() + m_y * r.GetY() + m_z * r.GetZ();
                
	}
	
	public Vector3f Cross(Vector3f r) {
            
		float x_ = m_y * r.GetZ() - m_z * r.GetY();
		float y_ = m_z * r.GetX() - m_x * r.GetZ();
		float z_ = m_x * r.GetY() - m_y * r.GetX();
		
		return new Vector3f(x_, y_, z_);
                
	}
	
	public Vector3f Normalized() {
            
		float length = Length();
		
		return new Vector3f(m_x / length, m_y / length, m_z / length);
                
	}

	public Vector3f Rotate(Vector3f axis, float angle) {
            
		float sinAngle = (float)Math.sin(-angle);
		float cosAngle = (float)Math.cos(-angle);

		return this.Cross(axis.Multiply(sinAngle)).Add(           //Rotation on local X
				(this.Multiply(cosAngle)).Add(                     //Rotation on local Z
						axis.Multiply(this.Dot(axis.Multiply(1 - cosAngle))))); //Rotation on local Y
	
        }

	public Vector3f Rotate(Quaternion rotation) {
            
		return null;
                
	}

	public Vector3f Add(Vector3f r) {
            
		return new Vector3f(m_x + r.GetX(), m_y + r.GetY(), m_z + r.GetZ());
                
	}
	
	public Vector3f Add(float r) {
            
		return new Vector3f(m_x + r, m_y + r, m_z + r);
                
	}
	
	public Vector3f Sub(Vector3f r) {
            
		return new Vector3f(m_x - r.GetX(), m_y - r.GetY(), m_z - r.GetZ());
                
	}
	
	public Vector3f Sub(float r) {
            
		return new Vector3f(m_x - r, m_y - r, m_z - r);
                
	}
	
	public Vector3f Multiply(Vector3f r) {
            
		return new Vector3f(m_x * r.GetX(), m_y * r.GetY(), m_z * r.GetZ());
                
	}
	
	public Vector3f Multiply(float r) {
            
		return new Vector3f(m_x * r, m_y * r, m_z * r);
                
	}
	
	public Vector3f Divide(Vector3f r) {
            
		return new Vector3f(m_x / r.GetX(), m_y / r.GetY(), m_z / r.GetZ());
                
	}
	
	public Vector3f Divide(float r) {
            
		return new Vector3f(m_x / r, m_y / r, m_z / r);
                
	}

	public float GetX() {
            
		return m_x;
                
	}

	public void SetX(float x) {
            
		this.m_x = x;
                
	}

	public float GetY() {
            
		return m_y;
                
	}

	public void SetY(float y) {
            
		this.m_y = y;
                
	}

	public float GetZ() {
            
		return m_z;
                
	}

	public void SetZ(float z) {
            
		this.m_z = z;
                
	}
}
