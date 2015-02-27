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
public class Camera {

    public static final Vector3f m_yAxis = new Vector3f(0 , 1, 0);
    
    private Vector3f m_pos;
    private Vector3f m_forward;
    private Vector3f m_up;
    
    public Camera() {
        
        this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
        
    }
    
    public Camera(Vector3f pos, Vector3f forward, Vector3f up) {
        
        this.m_pos = pos;
        this.m_forward = forward;
        this.m_up = up;
        
        up.normalized();
        forward.normalized();
        
    }
    
    /**
     *
     */
    public void input() {
        
        float moveAmt = (float)(10 * Time.getDelta());
        float rotAmt = (float)(100 * Time.getDelta());
        
        if(Input.getKey(Input.KEY_W)) {
            
            move(getM_forward(), moveAmt);
            
        }
        if(Input.getKey(Input.KEY_A)) {
            
            move(getLeft(), moveAmt);
            
        }
        if(Input.getKey(Input.KEY_S)) {
            
            move(getM_forward(), -moveAmt);
            
        }if(Input.getKey(Input.KEY_D)) {
            
            move(getRight(), moveAmt);
            
        }
        
        if(Input.getKey(Input.KEY_UP)) {
            
            rotateX(-rotAmt);
            
        }
        if(Input.getKey(Input.KEY_LEFT)) {
            
            rotateY(-rotAmt);
            
        }
        if(Input.getKey(Input.KEY_RIGHT)) {
            
            rotateY(rotAmt);
            
        }
        if(Input.getKey(Input.KEY_DOWN)) {
            
            rotateX(rotAmt);
            
        }
        
    }
    
    /**
     *
     * @param direction
     * @param amount
     */
    public void move(Vector3f direction, float amount) {
        
        m_pos = m_pos.add(direction.multiply(amount));
        
    }
    
    /**
     *
     * @param angle
     */
    public void rotateY(float angle) {
        
        Vector3f hAxis = m_yAxis.cross(m_forward);
        hAxis.normalized();
        
        m_forward.rotate(angle, m_yAxis);
        m_forward.normalized();
        
        m_up = m_forward.cross(hAxis);
        m_up.normalized();
        
    }
    
    /**
     *
     * @param angle
     */
    public void rotateX(float angle) {
        
        Vector3f hAxis = m_yAxis.cross(m_forward);
        hAxis.normalized();
        
        m_forward.rotate(angle, hAxis);
        m_forward.normalized();
        
        m_up = m_forward.cross(hAxis);
        m_up.normalized();
        
    }
    
    /**
     *
     * @return
     */
    public Vector3f getLeft() {
        
        Vector3f left = m_forward.cross(m_up);
        left.normalized();
        return left;
        
    }
    
    /**
     *
     * @return
     */
    public Vector3f getRight() {
        
        Vector3f right = m_up.cross(m_forward);
        right.normalized();
        return right;
        
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
     * @param m_pos
     */
    public void setM_pos(Vector3f m_pos) {
        
        this.m_pos = m_pos;
        
    }

    /**
     *
     * @return
     */
    public Vector3f getM_forward() {
        
        return m_forward;
        
    }

    /**
     *
     * @param m_forward
     */
    public void setM_forward(Vector3f m_forward) {
        
        this.m_forward = m_forward;
        
    }

    /**
     *
     * @return
     */
    public Vector3f getM_up() {
        
        return m_up;
        
    }

    /**
     *
     * @param m_up
     */
    public void setM_up(Vector3f m_up) {
        
        this.m_up = m_up;
        
    }
    
}
