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
public class Game {
    
    private final Mesh mesh;
    private final Material material;
    private final Shader shader;
    private final Transform transform;
    private final Camera camera;
    
    public Game() {
        
        mesh = new Mesh(); // ResourceLoader.loadMesh("box.obj");
        material = new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(0, 1, 1));
        shader = new BasicShader();
        camera = new Camera();
        
        Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
        Transform.setCamera(camera);
        transform = new Transform();
        
        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0), new Vector2f(0, 0)),
                                          new Vertex(new Vector3f(0, 1, 0), new Vector2f(0.5f, 0)),
                                          new Vertex(new Vector3f(1, -1, 0), new Vector2f(1.0f, 0)),
                                          new Vertex(new Vector3f(0, -1, 1), new Vector2f(0, 0.5f))};
        
        int[] indices = new int[] {3, 1, 0,
                                   2, 1, 3,
                                   0, 1, 2,
                                   0, 2, 3};
        
        mesh.addVertices(vertices, indices);
        
    }
    
    /**
     *
     */
    public void input() {
        
//        if(Input.getKeyDown(Keyboard.KEY_UP)) {
//            
//            System.out.println("We've just pressed a up!");
//            
//        }
//        if(Input.getKeyUp(Keyboard.KEY_UP)) {
//            
//            System.out.println("We've just released up!");
//            
//        }
//        
//        if(Input.getMouseDown(1)) {
//            
//            System.out.println("We've just right clicked at " + Input.getMousePosition());
//            
//        }
//        if(Input.getMouseUp(1)) {
//            
//            System.out.println("We've just released the right mouse button!");
//            
//        }
        
        camera.input();
        
    }
    
    float temp = 0.0f;
    
    /**
     *
     */
    public void update() {
        
        temp += Time.getDelta();
        float sinTemp = (float)Math.sin(temp);
        
        transform.setM_translation(sinTemp*20, 0, 5);
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
