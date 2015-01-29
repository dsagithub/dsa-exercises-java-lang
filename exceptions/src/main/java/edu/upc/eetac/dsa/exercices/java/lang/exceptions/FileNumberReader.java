/*
 * Copyright (c) 2015 Diseño de Servicios y Aplicaciones
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package edu.upc.eetac.dsa.exercices.java.lang.exceptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileNumberReader {

    public final static int[] readFile(String path) throws FileNotFoundException, IOException, BigNumberException {
        return readFile(path, Integer.MAX_VALUE);
    }

    public final static int[] readFile(String path, int limit) throws FileNotFoundException, IOException, BigNumberException {
        List<Integer> numbers = new ArrayList<>();
        int[] integers = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line = reader.readLine()) != null) {
                Integer read = new Integer(line);
                if (read.intValue() > limit) {
                    throw new BigNumberException();
                }
                numbers.add(read);
            }

            integers = new int[numbers.size()];
            for (int i = 0; i < integers.length; i++)
                integers[i] = numbers.get(i).intValue();
        } catch (Exception e) {
            throw e;
        } finally {
            if (reader != null)
                reader.close();
        }

        return integers;
    }
}
