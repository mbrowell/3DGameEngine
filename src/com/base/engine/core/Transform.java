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
public class Transform {

    private Vector3f m_pos;
    private Vector3f m_rot;
    private Vector3f m_scale;
    
    /**
     *
     */
    public Transform() {
        
        m_pos = new Vector3f(0, 0, 0);
        m_rot = new Vector3f(0, 0, 0);
        m_scale = new Vector3f(1, 1, 1);
        
    }
    
    /**
     *
     * @return
     */
    public Matrix4f getTransformation() {
        
        Matrix4f pos = new Matrix4f().initTranslation(m_pos.getM_x(), m_pos.getM_y(), m_pos.getM_z());
        Matrix4f rot = new Matrix4f().initRotation(m_rot.getM_x(), m_rot.getM_y(), m_rot.getM_z());
        Matrix4f scale = new Matrix4f().initScale(m_scale.getM_x(), m_scale.getM_y(), m_scale.getM_z());
        
        return pos.multiply(rot.multiply(scale));
        
    }

    /**
     *
     * @return
     */
    public Vector3f getM_pos() {
        
        return m_pos;
        
    }

    /**
     *
     * @param pos
     */
    public void setM_pos(Vector3f pos) {
        
        this.m_pos = pos;
        
    }
    
    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public void setM_pos(float x, float y, float z) {
        
        this.m_pos = new Vector3f(x, y, z);
        
    }

    /**
     *
     * @return
     */
    public Vector3f getM_rot() {
        
        return m_rot;
        
    }

    /**
     *
     * @param rot
     */
    public void setM_rot(Vector3f rot) {
        
        this.m_rot = rot;
        
    }
    
    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public void setM_rot(float x, float y, float z) {
        
        this.m_rot = new Vector3f(x, y, z);
        
    }

    /**
     *
     * @return
     */
    public Vector3f getM_scale() {
        
        return m_scale;
        
    }

    /**
     *
     * @param m_scale
     */
    public void setM_scale(Vector3f m_scale) {
        
        this.m_scale = m_scale;
        
    }
    
    /**
     *
     * @param x
     * @param y
     * @param z
     */
    public void setM_scale(float x, float y, float z) {
        
        this.m_scale = new Vector3f(x, y, z);
        
    }
    
}
