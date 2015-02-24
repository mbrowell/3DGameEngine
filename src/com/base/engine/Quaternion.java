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
public class Quaternion
{
	private float m_x;
	private float m_y;
	private float m_z;
	private float m_w;

	public Quaternion(float x, float y, float z, float w) {
            
		this.m_x = x;
		this.m_y = y;
		this.m_z = z;
		this.m_w = w;
                
	}

	public float Length() {
            
		return (float)Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z + m_w * m_w);
                
	}
	
	public Quaternion Normalized() {
            
		float length = Length();
		
		return new Quaternion(m_x / length, m_y / length, m_z / length, m_w / length);
                
	}
	
	public Quaternion Conjugate() {
            
		return new Quaternion(-m_x, -m_y, -m_z, m_w);
                
	}

	public Quaternion Mul(Quaternion r) {
            
		float w_ = m_w * r.GetW() - m_x * r.GetX() - m_y * r.GetY() - m_z * r.GetZ();
		float x_ = m_x * r.GetW() + m_w * r.GetX() + m_y * r.GetZ() - m_z * r.GetY();
		float y_ = m_y * r.GetW() + m_w * r.GetY() + m_z * r.GetX() - m_x * r.GetZ();
		float z_ = m_z * r.GetW() + m_w * r.GetZ() + m_x * r.GetY() - m_y * r.GetX();
		
		return new Quaternion(x_, y_, z_, w_);
                
	}
	
	public Quaternion Mul(Vector3f r) {
            
		float w_ = -m_x * r.GetX() - m_y * r.GetY() - m_z * r.GetZ();
		float x_ =  m_w * r.GetX() + m_y * r.GetZ() - m_z * r.GetY();
		float y_ =  m_w * r.GetY() + m_z * r.GetX() - m_x * r.GetZ();
		float z_ =  m_w * r.GetZ() + m_x * r.GetY() - m_y * r.GetX();
		
		return new Quaternion(x_, y_, z_, w_);
                
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

	public void SetY(float m_y) {
            
		this.m_y = m_y;
                
	}

	public float GetZ() {
            
		return m_z;
                
	}

	public void SetZ(float z) {
            
		this.m_z = z;
                
	}

	public float GetW() {
            
		return m_w;
                
	}

	public void SetW(float w) {
            
		this.m_w = w;
                
	}

}