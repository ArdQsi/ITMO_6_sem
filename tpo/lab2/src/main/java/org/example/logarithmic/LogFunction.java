package org.example.logarithmic;

import org.example.function.AbstractFunction;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public abstract class LogFunction extends AbstractFunction {
    private static final int DEFAULT_ITERATIONS = 1000;
    protected int maxIteration;
    public LogFunction() {
        this.maxIteration = DEFAULT_ITERATIONS;
    }
}
