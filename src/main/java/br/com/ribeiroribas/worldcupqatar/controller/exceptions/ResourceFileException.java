package br.com.ribeiroribas.worldcupqatar.controller.exceptions;

import java.io.Serial;

public class ResourceFileException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceFileException(String message) {
        super(message);
    }
}
