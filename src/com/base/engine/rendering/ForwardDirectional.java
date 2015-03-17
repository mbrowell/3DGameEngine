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

import com.base.engine.core.Matrix4f;
import com.base.engine.core.RenderingEngine;
import com.base.engine.core.Transform;

/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public class ForwardDirectional extends Shader {
    
    private static final ForwardDirectional m_instance = new ForwardDirectional();
    
    private DirectionalLight m_directionalLight;
    private DirectionalLight m_directionalLight2;

    /**
     *
     */
    public ForwardDirectional() {
        
        super();
        
        addVertexShaderFromFile("forward-directional.vs");
        addFragmentShaderFromFile("forward-directional.fs");
        
        setAttribLocation(0, "position");
        setAttribLocation(1, "texCoord");
        setAttribLocation(2, "normal");
        
        linkShader();
        
        addUniform("model");
        addUniform("MVP");
        
        addUniform("eyePos");
        
        addUniform("specularIntensity");
        addUniform("specularExponent");
        
        addUniform("directionalLight.base.colour");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
        
    }
    
    /**
     *
     * @param material
     */
    @Override
    public void updateUniforms(Transform transform, Material material) {
        
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = getM_renderingEngine().getM_mainCamera().getViewProjection().multiply(worldMatrix);
        
        material.getM_texture().bind();
        
        setUniform("model", worldMatrix);
        setUniform("MVP", projectedMatrix);
        
        setUniform("eyePos", getM_renderingEngine().getM_mainCamera().getM_pos());
        
        setUniformf("specularIntensity", material.getM_specularIntensity());
        setUniformf("specularExponent", material.getM_specularExponent());
        
        setUniform("directionalLight", getM_renderingEngine().getM_directionalLight());
        
    }
    
    /**
     *
     * @return
     */
    public static ForwardDirectional getM_instance() {
        
        return m_instance;
        
    }
    
    public void setUniform(String uniformName, DirectionalLight directionalLight) {
        
        setUniform(uniformName + ".base.colour", directionalLight.getM_colour());
        setUniformf(uniformName + ".base.intensity", directionalLight.getM_intensity());
        setUniform(uniformName + ".direction", directionalLight.getM_direction());
        
    }
    
}
