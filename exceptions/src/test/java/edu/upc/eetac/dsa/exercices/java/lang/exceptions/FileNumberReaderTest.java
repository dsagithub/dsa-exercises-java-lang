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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertArrayEquals;

public class FileNumberReaderTest {
    private BufferedReader reader = null;
    private int[] expectedIntegers = new int[NUM_OF_NUMBERS];
    private final static String PATH = "test-file.txt";
    private final static int NUM_OF_NUMBERS = 10;
    private final static int LIMIT = 64;

    @Before
    public void setUp() throws Exception {
        PrintWriter writer = new PrintWriter(PATH);
        for (int i = 0; i < NUM_OF_NUMBERS; i++) {
            expectedIntegers[i] = (int) Math.pow(2, i);
            writer.println(expectedIntegers[i]);
        }
        writer.close();
    }

    @After
    public void tearDown() throws Exception {
        File file = new File(PATH);
        file.delete();
    }

    @Test
    public void testReadFile() throws IOException, BigNumberException {
        int[] integers = FileNumberReader.readFile(PATH);
        assertArrayEquals(expectedIntegers, integers);
    }

    @Test(expected = BigNumberException.class)
    public void testReadFileBigNumberException() throws IOException, BigNumberException {
        FileNumberReader.readFile(PATH, LIMIT);
    }

    @Test(expected = NumberFormatException.class)
    public void testReadFileNumberFormatException() throws IOException, BigNumberException {
        PrintWriter writer = new PrintWriter(new FileOutputStream(PATH), true);
        writer.println("NOT_A_NUMBER");
        writer.close();

        FileNumberReader.readFile(PATH);
    }
}
