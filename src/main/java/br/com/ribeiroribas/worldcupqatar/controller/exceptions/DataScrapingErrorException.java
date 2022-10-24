package br.com.ribeiroribas.worldcupqatar.controller.exceptions;

import java.io.Serial;

public class DataScrapingErrorException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public DataScrapingErrorException(String message) {
        super(message);
    }
}
