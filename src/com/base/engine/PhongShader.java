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
public class PhongShader extends Shader {

    private static final PhongShader m_instance = new PhongShader();
    
    private static Vector3f m_ambientLight;

    /**
     *
     */
    public PhongShader() {
        
        super();
        
        addVertexShader(ResourceLoader.loadShader("phongShader.vs"));
        addFragmentShader(ResourceLoader.loadShader("phongShader.fs"));
        linkShader();
        
        addUniform("transform");
        addUniform("baseColour");
        addUniform("ambientLight");
        
    }
    
    /**
     *
     * @param worldMatrix
     * @param projectedMatrix
     * @param material
     */
    @Override
    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
        
        if(material.getTexture() != null) {
            
            material.getTexture().bind();
            
        } else {
            
            RenderUtil.unbindTextures();
            
        }
        
        setUniform("transform", projectedMatrix);
        setUniform("baseColour", material.getColour());
        setUniform("ambientLight", m_ambientLight);
        
    }

    /**
     *
     * @return
     */
    public static Vector3f getM_ambientLight() {
        
        return m_ambientLight;
        
    }

    /**
     *
     * @param ambientLight
     */
    public static void setM_ambientLight(Vector3f ambientLight) {
        
        PhongShader.m_ambientLight = ambientLight;
        
    }
    
    /**
     *
     * @return
     */
    public static PhongShader getM_instance() {
        
        return m_instance;
        
    }
    
}
