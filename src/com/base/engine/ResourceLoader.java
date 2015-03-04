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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.opengl.TextureLoader;


/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public class ResourceLoader {
    
    /**
     *
     * @param fileName
     * @return
     */
    public static Texture loadTexture(String fileName) {
        
        String[] splitArray = fileName.split("\\.");
        String extension = splitArray[splitArray.length - 1];
        
        try {
            
            int id = TextureLoader.getTexture(extension, new FileInputStream(new File("./res/textures/" + fileName))).getTextureID();
            
            return new Texture(id);
            
        } catch (IOException ex) {
            
            Logger.getLogger(ResourceLoader.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return null;
        
    }

    /**
     *
     * @param fileName
     * @return
     */
    public static String loadShader(String fileName) {
        
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;
        
        try {
            
            shaderReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(ResourceLoader.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        String line;
        
        try {
            
            while((line = shaderReader.readLine()) != null) {
                
                shaderSource.append(line).append("\n");
                               
            }
            
        } catch (IOException ex) {
            
            Logger.getLogger(ResourceLoader.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return shaderSource.toString();
        
    }
    
    /**
     *
     * @param fileName
     * @return
     */
    @SuppressWarnings("null")
    public static Mesh loadMesh(String fileName) {
        
        String[] splitArray = fileName.split("\\.");
        String extension = splitArray[splitArray.length - 1];
        
        if(!extension.equals("obj")) {
            
            System.err.println("Error: File format not supported for mesh data: " + extension);
            Logger.getLogger(ResourceLoader.class.getName()).log(Level.SEVERE, null, new Exception());
            System.exit(1);
            
        }
        
        ArrayList<Vertex> vertices = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        
        @SuppressWarnings("UnusedAssignment")
        BufferedReader meshReader = null;
        
        try {
            
            meshReader = new BufferedReader(new FileReader("./res/models/" + fileName));
            
        } catch (FileNotFoundException ex) {
            
            Logger.getLogger(ResourceLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
            
        }
        
        String line;
        
        try {
            
            while((line = meshReader.readLine()) != null) {
                
                String[] tokens = line.split(" ");
                tokens = Util.removeEmptyStrings(tokens);
                
                if(tokens.length == 0 || tokens[0].equals("#")) {
                } else if(tokens[0].equals("v")) {
                    
                    vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]),
                                                         Float.valueOf(tokens[2]),
                                                         Float.valueOf(tokens[3]))));
                    
                } else if(tokens[0].equals("f")) {
                    
                    indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                    
                    if(tokens.length > 4) {
                    
                    indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
                    
                }
                    
                }
                               
            }
            
        } catch (IOException ex) {
            
            Logger.getLogger(ResourceLoader.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        try {
            
            meshReader.close();
            
        } catch (IOException ex) {
            
            Logger.getLogger(ResourceLoader.class.getName()).log(Level.SEVERE, null, ex);
            
        }
            
        Mesh result = new Mesh();
        Vertex[] vertexData = new Vertex[vertices.size()];
        vertices.toArray(vertexData);
            
        Integer[] indexData = new Integer[indices.size()];
        indices.toArray(indexData);
        
        result.addVertices(vertexData, Util.toIntArray(indexData));
        
        return result;
        
    }

}
