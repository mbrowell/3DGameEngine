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

package com.base.engine.rendering;

import com.base.engine.core.Input;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;

/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public class Camera {

    /**
     *
     */
    public static Vector3f m_yAxis = new Vector3f(0 , 1, 0);
    
    private Vector3f m_pos;
    private Vector3f m_forward;
    private Vector3f m_up;
    private final Matrix4f m_projection;
    
    /**
     *
     * @param fov
     * @param aspectRatio
     * @param zNear
     * @param zFar
     */
    public Camera(float fov, float aspectRatio, float zNear, float zFar) {
        
        this.m_pos = new Vector3f(0, 0, 0);
        this.m_forward = new Vector3f(0, 0, 1).normalized();
        this.m_up = new Vector3f(0, 1, 0).normalized();
        this.m_projection = new Matrix4f().initPerspective(fov, aspectRatio, zNear, zFar);
        
    }
    
    public Matrix4f getViewProjection() {
        
        Matrix4f cameraRotation = new Matrix4f().initRotation(m_forward, m_up);
        Matrix4f cameraTranslation = new Matrix4f().initTranslation(-m_pos.getM_x(), -m_pos.getM_y(), -m_pos.getM_z());
        
        return m_projection.multiply(cameraRotation.multiply(cameraTranslation));
        
    }
    
    boolean mouseLocked = false;
    Vector2f centrePosition = new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2);
    
    /**
     *
     * @param delta
     */
    public void input(float delta) {
        
        float sensitivity = 0.5f;
        float moveAmt = (float)(10 * delta);
        //float rotAmt = (float)(100 * Time.getM_delta());
        
        if(Input.getKey(Input.KEY_ESCAPE)) {
            
            Input.setCursor(true);
            mouseLocked = false;
            
        }
        if(Input.getMouseDown(0)) {
            
            Input.setMousePosition(centrePosition);
            Input.setCursor(false);
            mouseLocked = true;
            
        }
        
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
        
        if(mouseLocked) {
            
            Vector2f deltaPos = Input.getMousePosition().subtract(centrePosition);
            
            boolean rotY = deltaPos.getM_x() != 0;
            boolean rotX = deltaPos.getM_y() != 0;
            
            if(rotY) {
                
                rotateY(deltaPos.getM_x() * sensitivity);
                
            }
            if(rotX) {
                
                rotateX(-deltaPos.getM_y() * sensitivity);
                
            }
            
            if (rotY || rotX) {
                
                Input.setMousePosition(new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2));
                
            }
            
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
        
        Vector3f hAxis = m_yAxis.cross(m_forward).normalized();
        
        m_forward = m_forward.rotate(angle, m_yAxis).normalized();
        
        m_up = m_forward.cross(hAxis).normalized();
        
    }
    
    /**
     *
     * @param angle
     */
    public void rotateX(float angle) {
        
        Vector3f hAxis = m_yAxis.cross(m_forward).normalized();
        
        m_forward = m_forward.rotate(angle, hAxis).normalized();
        
        m_up = m_forward.cross(hAxis).normalized();
        
    }
    
    /**
     *
     * @return
     */
    public Vector3f getLeft() {
        
        return m_forward.cross(m_up).normalized();
        
    }
    
    /**
     *
     * @return
     */
    public Vector3f getRight() {
        
        return m_up.cross(m_forward).normalized();
        
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
