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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Browell <mbrowell1984@gmail.com>
 */
public class ResourceLoader {

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

}
