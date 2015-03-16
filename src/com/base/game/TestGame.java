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

import com.base.engine.core.Game;
import com.base.engine.core.GameObject;
import com.base.engine.core.Vector2f;
import com.base.engine.core.Vector3f;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.Texture;
import com.base.engine.rendering.Vertex;

/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public class TestGame extends Game {
    
    /**
     *
     */
    @Override
    public void init() {
        
        float fieldDepth = 10.0f;
        float fieldWidth = 10.0f;
        
        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f (0, 0)),
                                          new Vertex(new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f (0, 1)),
                                          new Vertex(new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f (1, 0)),
                                          new Vertex(new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f (1, 1))};
    
        int indices[] = {0, 1, 2,
                         2, 1, 3};
        
        Mesh mesh = new Mesh(vertices, indices, true);
        Material material = new Material(new Texture("test.png"), new Vector3f(1, 1, 1), 1, 8);
        
        MeshRenderer meshRenderer = new MeshRenderer(mesh, material);
        
        GameObject planeObject = new GameObject();
        planeObject.addComponent(meshRenderer);
        planeObject.getM_transform().setM_pos(0, -1, 5);
        
        getRootObject().addChild(planeObject);
        
    }

}
