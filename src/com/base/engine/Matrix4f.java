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
public class Matrix4f
{
	private float[][] m;
	
	public Matrix4f()
	{
		m = new float[4][4];
	}

	public Matrix4f initIdentity() {
            
		m[0][0] = 1;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = 0;
		m[1][0] = 0;	m[1][1] = 1;	m[1][2] = 0;	m[1][3] = 0;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = 1;	m[2][3] = 0;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;

		return this;
                
	}
	
	public Matrix4f multiply(Matrix4f r) {
            
		Matrix4f result = new Matrix4f();
		
		for(int i = 0; i < 4; i++) {
                    
			for(int j = 0; j < 4; j++) {
                            
				result.set(i, j, m[i][0] * r.get(0, j) +
						 m[i][1] * r.get(1, j) +
						 m[i][2] * r.get(2, j) +
						 m[i][3] * r.get(3, j));
                                
			}
                        
		}
		
		return result;
                
	}
	
	public float[][] getM() {
            
		float[][] result = new float[4][4];
		
		for(int i = 0; i < 4; i++) {
                    
                    System.arraycopy(m[i], 0, result[i], 0, 4);
                        
                }
		
		return result;
                
	}
	
	public float get(int x, int y) {
            
		return m[x][y];
                
	}

	public void setM(float[][] m) {
            
		this.m = m;
                
	}
	
	public void set(int x, int y, float value) {
            
		m[x][y] = value;
                
	}
        
}
