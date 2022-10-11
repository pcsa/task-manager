package com.esig.br.demo.exceptions;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

public class TarefaCreateOrUpdateException extends RuntimeException {

    private final Logger logger = LoggerFactory.logger(TarefaCreateOrUpdateException.class);
    private static final String MESSAGE = "A tarefa não pôde ser salva. Use DEBUG para mais detalhes.";

    public TarefaCreateOrUpdateException() {
        super(MESSAGE);
        logger.error(MESSAGE);
    }

    public TarefaCreateOrUpdateException(Throwable cause) {
        super(MESSAGE, cause);
        logger.error(MESSAGE + " - causa: " + cause.getClass().getSimpleName());
    }
}
