package uy.edu.um.prog2.adt.exceptions;

import java.io.IOException;

public class FileNotValidException extends Throwable {


    public FileNotValidException(String fileGenerationError, Exception ex) {
        super(fileGenerationError, ex); }

}
