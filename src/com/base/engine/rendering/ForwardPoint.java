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
import com.base.engine.core.Transform;

/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public class ForwardPoint extends Shader {
    
    private static final ForwardPoint m_instance = new ForwardPoint();

    /**
     *
     */
    public ForwardPoint() {
        
        super();
        
        addVertexShaderFromFile("forward-point.vs");
        addFragmentShaderFromFile("forward-point.fs");
        
        setAttribLocation(0, "position");
        setAttribLocation(1, "texCoord");
        setAttribLocation(2, "normal");
        
        linkShader();
        
        addUniform("model");
        addUniform("MVP");
        
        addUniform("eyePos");
        
        addUniform("specularIntensity");
        addUniform("specularExponent");
        
        addUniform("pointLight.base.colour");
        addUniform("pointLight.base.intensity");
        addUniform("pointLight.atten.constant");
        addUniform("pointLight.atten.linear");
        addUniform("pointLight.atten.exponent");
        addUniform("pointLight.position");
        addUniform("pointLight.range");
        
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
        
        setUniform("pointLight", getM_renderingEngine().getM_pointLight());
        
    }
    
    public void setUniform(String uniformName, PointLight pointLight) {
        
        setUniform(uniformName + ".base", pointLight.getM_base());
        setUniform(uniformName + ".atten", pointLight.getM_atten());
        setUniform(uniformName + ".position", pointLight.getM_position());
        setUniformf(uniformName + ".range", pointLight.getM_range());
        
    }
    
    public void setUniform(String uniformName, BaseLight base) {
        
        setUniform(uniformName + ".colour", base.getM_colour());
        setUniformf(uniformName + ".intensity", base.getM_intensity());
        
    }
    
    public void setUniform(String uniformName, Attenuation attenuation) {
        
        setUniformf(uniformName + ".constant", attenuation.getM_constant());
        setUniformf(uniformName + ".linear", attenuation.getM_linear());
        setUniformf(uniformName + ".exponent", attenuation.getM_exponent());
        
    }
    
    /**
     *
     * @return
     */
    public static ForwardPoint getM_instance() {
        
        return m_instance;
        
    }
    
}
