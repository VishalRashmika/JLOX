package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
    // Entry Point
    public static void main(String[] args) throws IOException {
        // Error Handling
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        }
        // compile the parsed file
        else if (args.length == 1) {
            runFile(args[0]);
        }
        // switch to prompt mode
        else {
            runPrompt();
        }
    }
}

// compiling using file
private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    run(new String(bytes, Charset.defaultCharset()));

    if (hadError) System.exit(65);
}

// prompt
private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);
    for (;;) {
        System.out.print("> ");
        String line = reader.readLine();
        if (line == null) break;
        run(line);

        hadError = false;
    }
}

// called from the prompt function
private static void run(String source) {
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();
    // For now, just print the tokens.
    for (Token token : tokens) {
        System.out.println(token);
    }
}

// error handling
static void error(int line, String message) {
    report(line, "", message);
}
// error reporting
private static void report(int line, String where, String message) {
    System.err.println("[line " + line + "] Error" + where + ": " + message);
    hadError = true;
}

public class Lox {
    // checks whether an error has occured
    static boolean hadError = false;
