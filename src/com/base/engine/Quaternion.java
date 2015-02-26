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
public class Quaternion {
    
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

    /**
     *
     * @return
     */
    public float length() {
            
        return (float)Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z + m_w * m_w);
                
    }
	
    /**
     *
     * @return
     */
    public Quaternion normalized() {
            
        float length = length();
		
        return new Quaternion(m_x / length, m_y / length, m_z / length, m_w / length);
                
    }
	
    /**
     *
     * @return
     */
    public Quaternion conjugate() {
            
		return new Quaternion(-m_x, -m_y, -m_z, m_w);
                
    }

    /**
     *
     * @param r
     * @return
     */
    public Quaternion multiply(Quaternion r) {
            
        float w_ = m_w * r.getW() - m_x * r.getX() - m_y * r.getY() - m_z * r.getZ();
        float x_ = m_x * r.getW() + m_w * r.getX() + m_y * r.getZ() - m_z * r.getY();
        float y_ = m_y * r.getW() + m_w * r.getY() + m_z * r.getX() - m_x * r.getZ();
        float z_ = m_z * r.getW() + m_w * r.getZ() + m_x * r.getY() - m_y * r.getX();
	
        return new Quaternion(x_, y_, z_, w_);
                
    }
	
    /**
     *
     * @param r
     * @return
     */
    public Quaternion multiply(Vector3f r) {
            
        float w_ = -m_x * r.getX() - m_y * r.getY() - m_z * r.getZ();
        float x_ =  m_w * r.getX() + m_y * r.getZ() - m_z * r.getY();
        float y_ =  m_w * r.getY() + m_z * r.getX() - m_x * r.getZ();
        float z_ =  m_w * r.getZ() + m_x * r.getY() - m_y * r.getX();
		
	return new Quaternion(x_, y_, z_, w_);
                
    }
        
    /**
     *
     * @return
     */
    public float getX() {
            
	return m_x;
                
    }

    /**
     *
     * @param x
     */
    public void setX(float x) {
            
	this.m_x = x;
                
    }

    /**
     *
     * @return
     */
    public float getY() {
            
	return m_y;
                
    }

    /**
     *
     * @param m_y
     */
    public void setY(float m_y) {
            
	this.m_y = m_y;
                
    }

    /**
     *
     * @return
     */
    public float getZ() {
            
	return m_z;
                
    }

    /**
     *
     * @param z
     */
    public void setZ(float z) {
            
	this.m_z = z;
                
    }

    /**
     *
     * @return
     */
    public float getW() {
            
    	return m_w;
                
    }

    /**
     *
     * @param w
     */
    public void setW(float w) {
            
        this.m_w = w;
                
    }

}