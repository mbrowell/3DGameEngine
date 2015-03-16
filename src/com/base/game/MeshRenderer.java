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

package com.base.game;

import com.base.engine.core.GameComponent;
import com.base.engine.core.Transform;
import com.base.engine.rendering.BasicShader;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.Shader;

/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public class MeshRenderer implements GameComponent {
    
    private final Mesh m_mesh;
    private final Material m_material;
    
    /**
     *
     * @param mesh
     * @param material
     */
    public MeshRenderer(Mesh mesh, Material material) {
        
        m_mesh = mesh;
        m_material = material;
        
    }
    
    /**
     *
     * @param transform
     */
    @Override
    public void render(Transform transform){
        
        Shader shader = BasicShader.getM_instance();
        
        shader.bind();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), m_material);
        m_mesh.draw();
        
    }

    @Override
    public void input(Transform transform) {}

    @Override
    public void update(Transform transform) {}
    
}
