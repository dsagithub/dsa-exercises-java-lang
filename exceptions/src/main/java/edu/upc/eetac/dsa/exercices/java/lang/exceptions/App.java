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

import org.apache.commons.cli.*;

import java.io.*;

/**
 * App
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = null;
        String maxInteger = null;

        Options options = new Options();
        Option optionFile = OptionBuilder.withArgName("file")
                .hasArg()
                .withDescription("file with integers").withLongOpt("file")
                .create("f");
        options.addOption(optionFile);
        Option optionMax = OptionBuilder.withArgName("max")
                .hasArg()
                .withDescription("maximum integer allowed in the file").withLongOpt("max")
                .create("M");
        options.addOption(optionFile);
        options.addOption(optionMax);

        options.addOption("h", "help", false, "prints this message");

        CommandLineParser parser = new GnuParser();
        CommandLine line = null;
        try {
            // parse the command line arguments
            line = parser.parse(options, args);
            if (line.hasOption("h")) {    // No hace falta preguntar por el parámetro "help". Ambos son sinónimos
                new HelpFormatter().printHelp(App.class.getCanonicalName(), options);
                return;
            }
            filename = line.getOptionValue("f");
            if (filename == null) {
                throw new org.apache.commons.cli.ParseException("You must provide the path to the file with numbers.");
            }
            maxInteger = line.getOptionValue("M");
        } catch (ParseException exp) {
            System.err.println(exp.getMessage());
            new HelpFormatter().printHelp(App.class.getCanonicalName(), options);
            return;
        }


        try {
            int[] numbers = (maxInteger != null) ? FileNumberReader.readFile(filename, Integer.parseInt(maxInteger)) : FileNumberReader.readFile(filename);
            for (int i = 0; i < numbers.length; i++) {
                System.out.println("Integer read: " + numbers[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BigNumberException e) {
            e.printStackTrace();
        }
    }
}
