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

    /**
     *
     * @return
     */
    public float length() {

        return (float)Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z);

    }

    /**
     *
     * @param r
     * @return
     */
    public float dot(Vector3f r) {

        return m_x * r.getX() + m_y * r.getY() + m_z * r.getZ();

    }

    /**
     *
     * @param r
     * @return
     */
    public Vector3f cross(Vector3f r) {

        float x_ = m_y * r.getZ() - m_z * r.getY();
        float y_ = m_z * r.getX() - m_x * r.getZ();
        float z_ = m_x * r.getY() - m_y * r.getX();

        return new Vector3f(x_, y_, z_);

    }

    /**
     *
     * @return
     */
    public Vector3f normalized() {

        float length = length();

        return new Vector3f(m_x / length, m_y / length, m_z / length);

    }

    /**
     *
     * @param angle
     * @param axis
     * @return
     */
    public Vector3f rotate(float angle, Vector3f axis) {
        
        float sinHalfAngle = (float)Math.sin(Math.toRadians(angle / 2));
        float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));
        
        float rX = axis.getX() * sinHalfAngle;
        float rY = axis.getY() * sinHalfAngle;
        float rZ = axis.getZ() * sinHalfAngle;
        float rW = cosHalfAngle;
        
        Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
        Quaternion conjugate = rotation.conjugate();
        
        Quaternion w = rotation.multiply(this).multiply(conjugate);

        return new Vector3f(w.getX(), w.getY(), w.getZ());

    }

    /**
     *
     * @param r
     * @return
     */
    public Vector3f add(Vector3f r) {

        return new Vector3f(m_x + r.getX(), m_y + r.getY(), m_z + r.getZ());

    }

    /**
     *
     * @param r
     * @return
     */
    public Vector3f add(float r) {

        return new Vector3f(m_x + r, m_y + r, m_z + r);

    }

    /**
     *
     * @param r
     * @return
     */
    public Vector3f subtract(Vector3f r) {

        return new Vector3f(m_x - r.getX(), m_y - r.getY(), m_z - r.getZ());

    }

    /**
     *
     * @param r
     * @return
     */
    public Vector3f subtract(float r) {

        return new Vector3f(m_x - r, m_y - r, m_z - r);

    }

    /**
     *
     * @param r
     * @return
     */
    public Vector3f multiply(Vector3f r) {

        return new Vector3f(m_x * r.getX(), m_y * r.getY(), m_z * r.getZ());

    }

    /**
     *
     * @param r
     * @return
     */
    public Vector3f multiply(float r) {

        return new Vector3f(m_x * r, m_y * r, m_z * r);

    }

    /**
     *
     * @param r
     * @return
     */
    public Vector3f divide(Vector3f r) {

        return new Vector3f(m_x / r.getX(), m_y / r.getY(), m_z / r.getZ());

    }

    /**
     *
     * @param r
     * @return
     */
    public Vector3f divide(float r) {

        return new Vector3f(m_x / r, m_y / r, m_z / r);

    }
    
    public Vector3f abs() {
        
        return new Vector3f(Math.abs(m_x), Math.abs(m_y), Math.abs(m_z));
        
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
     * @param y
     */
    public void setY(float y) {

        this.m_y = y;

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
}
