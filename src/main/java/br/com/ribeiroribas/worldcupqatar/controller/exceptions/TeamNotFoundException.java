package br.com.ribeiroribas.worldcupqatar.controller.exceptions;

import java.io.Serial;

public class TeamNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TeamNotFoundException(String message) {
        super(message);
    }
}
