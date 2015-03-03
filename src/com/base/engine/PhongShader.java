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
    
    private static Vector3f m_ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
    private static DirectionalLight m_directionalLight = new DirectionalLight(new BaseLight(new Vector3f(1, 1, 1), 0), new Vector3f(0, 0, 0));

    /**
     *
     */
    public PhongShader() {
        
        super();
        
        addVertexShader(ResourceLoader.loadShader("phongShader.vs"));
        addFragmentShader(ResourceLoader.loadShader("phongShader.fs"));
        linkShader();
        
        addUniform("transform");
        addUniform("transformProjected");
        addUniform("baseColour");
        addUniform("ambientLight");
        
        addUniform("directionalLight.base.colour");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
        
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
        
        setUniform("transform", worldMatrix);
        setUniform("transformProjected", projectedMatrix);
        setUniform("baseColour", material.getColour());
        setUniform("ambientLight", m_ambientLight);
        
        setUniform("directionalLight", m_directionalLight);
        
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

    public static DirectionalLight getM_directionalLight() {
        
        return m_directionalLight;
        
    }

    public static void setM_directionalLight(DirectionalLight directionalLight) {
        
        PhongShader.m_directionalLight = directionalLight;
        
    }
    
    /**
     *
     * @return
     */
    public static PhongShader getM_instance() {
        
        return m_instance;
        
    }
    
    public void setUniform(String uniformName, BaseLight baseLight) {
        
        setUniform(uniformName + ".colour", baseLight.getM_colour());
        setUniformf(uniformName + ".intensity", baseLight.getM_intensity());
        
    }
    
    public void setUniform(String uniformName, DirectionalLight directionalLight) {
        
        setUniform(uniformName + ".base", directionalLight.getM_base());
        setUniform(uniformName + ".direction", directionalLight.getM_direction());
        
    }
    
}
