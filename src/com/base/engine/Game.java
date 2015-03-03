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
public class Game extends Camera {
    
    private final Mesh mesh;
    private final Material material;
    private final Shader shader;
    private final Transform transform;
    
    /**
     *
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public Game() {
        
        super();
        
        mesh = new Mesh(); // mesh = ResourceLoader.loadMesh("box.obj");
        material = new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(0, 1, 1));
        shader = PhongShader.getM_instance();
        transform = new Transform();
        
        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0.5773f), new Vector2f(0, 0)),
                                          new Vertex(new Vector3f(0, -1, -1.15475f), new Vector2f(0.5f, 0)),
                                          new Vertex(new Vector3f(1, -1, 0.5773f), new Vector2f(1, 0)),
                                          new Vertex(new Vector3f(0, 1, 0), new Vector2f(0.5f, 1))};
        
        int[] indices = new int[] {0, 3, 1,
                                   1, 3, 2,
                                   2, 3, 0,
                                   1, 2, 0};
        
        mesh.addVertices(vertices, indices, true);
        
        Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
        Transform.setCamera(this);
        
        PhongShader.setM_ambientLight(new Vector3f(0.1f, 0.1f, 0.1f));
        PhongShader.setM_directionalLight(new DirectionalLight(new BaseLight(new Vector3f(1, 1, 1), 0.8f), new Vector3f(1, 1, 1)));
        
    }
    
    float temp = 0.0f;
    
    /**
     *
     */
    public void updateGame() {
        
        temp += Time.getDelta();
        float sinTemp = (float)Math.sin(temp);
        
        transform.setM_translation(0, 0, 0);
        transform.setM_rotation(0 , sinTemp * 180, 0);
        //transform.setM_scale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);

    }
    
    /**
     *
     */
    public void render() {
        
        RenderUtil.setClearColour(Transform.getCamera().getM_pos().divide(2048f).abs());
        shader.bind();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
        mesh.draw();
        
    }
    
}
