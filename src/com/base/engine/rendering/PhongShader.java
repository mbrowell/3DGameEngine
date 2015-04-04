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
import com.base.engine.core.Vector3f;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public class PhongShader extends Shader {
    
    private static final int MAX_POINT_LIGHTS = 4;
    private static final int MAX_SPOT_LIGHTS = 4;

    private static final PhongShader m_instance = new PhongShader();
    
    private static Vector3f m_ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
    private static DirectionalLight m_directionalLight = new DirectionalLight(new Vector3f(1, 1, 1), 0, new Vector3f(0, 0, 0));
    private static PointLight[] m_pointLights = new PointLight[] {};
    private static SpotLight[] m_spotLights = new SpotLight[] {};

    /**
     *
     */
    public PhongShader() {
        
        super();
        
        addVertexShaderFromFile("phongShader.vs");
        addFragmentShaderFromFile("phongShader.fs");
        linkShader();
        
        addUniform("transform");
        addUniform("transformProjected");
        
        addUniform("baseColour");
        
        addUniform("eyePos");
        
        addUniform("specularIntensity");
        addUniform("specularExponent");
        
        addUniform("ambientLight");
        addUniform("directionalLight.base.colour");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
        for(int i = 0; i < MAX_POINT_LIGHTS; i++) {
            
            addUniform("pointLights[" + i + "].base.colour");
            addUniform("pointLights[" + i + "].base.intensity");
            addUniform("pointLights[" + i + "].atten.constant");
            addUniform("pointLights[" + i + "].atten.linear");
            addUniform("pointLights[" + i + "].atten.exponent");
            addUniform("pointLights[" + i + "].position");
            addUniform("pointLights[" + i + "].range");
            
        }
        for(int i = 0; i < MAX_SPOT_LIGHTS; i++) {
            
            addUniform("spotLights[" + i + "].pointLight.base.colour");
            addUniform("spotLights[" + i + "].pointLight.base.intensity");
            addUniform("spotLights[" + i + "].pointLight.atten.constant");
            addUniform("spotLights[" + i + "].pointLight.atten.linear");
            addUniform("spotLights[" + i + "].pointLight.atten.exponent");
            addUniform("spotLights[" + i + "].pointLight.position");
            addUniform("spotLights[" + i + "].pointLight.range");
            addUniform("spotLights[" + i + "].direction");
            addUniform("spotLights[" + i + "].cutoff");
            
        }
        
    }
    
    /**
     *
     * @param worldMatrix
     * @param projectedMatrix
     * @param material
     */
    @Override
    public void updateUniforms(Transform transform, Material material) {
        
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = getM_renderingEngine().getM_mainCamera().getViewProjection().multiply(worldMatrix);
        
        material.getM_texture().bind();
        
        setUniform("transform", worldMatrix);
        setUniform("transformProjected", projectedMatrix);
        setUniform("baseColour", material.getM_colour());
        
        setUniform("eyePos", getM_renderingEngine().getM_mainCamera().getM_pos());
        
        setUniform("ambientLight", m_ambientLight);
        setUniform("directionalLight", m_directionalLight);
        for (int i = 0; i < m_pointLights.length; i++) {
            
            setUniform("pointLights[" + i + "]", m_pointLights[i]);
            
        }
        for (int i = 0; i < m_spotLights.length; i++) {
            
            setUniform("spotLights[" + i + "]", m_spotLights[i]);
            
        }
        
        setUniformf("specularIntensity", material.getM_specularIntensity());
        setUniformf("specularExponent", material.getM_specularExponent());
        
        
        
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
    
    public static void setM_pointLights(PointLight[] pointLights) {
        
        if(pointLights.length > MAX_POINT_LIGHTS) {
            
            System.err.println("Error: You passed in too many point lights."
                    + "/nMax allowed: " + MAX_POINT_LIGHTS + ".  You passed: " + pointLights.length);
            Logger.getLogger(PhongShader.class.getName()).log(Level.SEVERE, null, new Exception());
            System.exit(1);
            
        }
        
        PhongShader.m_pointLights = pointLights;
        
    }
    
    public static void setM_spotLights(SpotLight[] spotLights) {
        
        if(spotLights.length > MAX_SPOT_LIGHTS) {
            
            System.err.println("Error: You passed in too many spot lights."
                    + "/nMax allowed: " + MAX_SPOT_LIGHTS + ".  You passed: " + spotLights.length);
            Logger.getLogger(PhongShader.class.getName()).log(Level.SEVERE, null, new Exception());
            System.exit(1);
            
        }
        
        PhongShader.m_spotLights = spotLights;
        
    }
    
    /**
     *
     * @return
     */
    public static PhongShader getM_instance() {
        
        return m_instance;
        
    }
    
    public void setUniform(String uniformName, BaseLight base) {
        
        setUniform(uniformName + ".colour", base.getM_colour());
        setUniformf(uniformName + ".intensity", base.getM_intensity());
        
    }
    
    public void setUniform(String uniformName, DirectionalLight directionalLight) {
        
        setUniform(uniformName + ".base", directionalLight.getM_base());
        setUniform(uniformName + ".direction", directionalLight.getM_direction());
        
    }
    
    public void setUniform(String uniformName, PointLight pointLight) {
        
        setUniform(uniformName + ".base", pointLight.getM_base());
        setUniform(uniformName + ".atten", pointLight.getM_atten());
        setUniform(uniformName + ".position", pointLight.getM_position());
        setUniformf(uniformName + ".range", pointLight.getM_range());
        
    }
    
    public void setUniform(String uniformName, SpotLight spotLight) {
        
        setUniform(uniformName + ".pointLight", spotLight.getM_pointLight());
        setUniformf(uniformName + ".cutoff", spotLight.getM_cutoff());
        
    }
    
    public void setUniform(String uniformName, Attenuation attenuation) {
        
        setUniformf(uniformName + ".constant", attenuation.getM_constant());
        setUniformf(uniformName + ".linear", attenuation.getM_linear());
        setUniformf(uniformName + ".exponent", attenuation.getM_exponent());
        
    }
    
}
