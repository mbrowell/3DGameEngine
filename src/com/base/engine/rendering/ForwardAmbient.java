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
public class ForwardAmbient extends Shader { 
    
    private static final ForwardAmbient m_instance = new ForwardAmbient();

    /**
     *
     */
    public ForwardAmbient() {
        
        super();
        
        addVertexShaderFromFile("forward-ambient.vs");
        addFragmentShaderFromFile("forward-ambient.fs");
        
        setAttribLocation(0, "position");
        setAttribLocation(1, "texCoord");
        
        linkShader();
        
        addUniform("MVP");
        addUniform("ambientIntensity");
        
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
        
        setUniform("MVP", projectedMatrix);
        setUniform("ambientIntensity", getM_renderingEngine().getM_ambientLight());
        
    }
    
    /**
     *
     * @return
     */
    public static ForwardAmbient getM_instance() {
        
        return m_instance;
        
    }
    
}
