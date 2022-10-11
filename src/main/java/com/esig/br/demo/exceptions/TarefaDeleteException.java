package com.esig.br.demo.exceptions;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

public class TarefaDeleteException extends RuntimeException {

    private final Logger logger = LoggerFactory.logger(TarefaDeleteException.class);
    private static final String MESSAGE = "A tarefa não pôde ser deletada. Use DEBUG para mais detalhes.";

    public TarefaDeleteException() {
        super(MESSAGE);
        logger.error(MESSAGE);
    }

    public TarefaDeleteException(Throwable cause) {
        super(MESSAGE, cause);
        logger.error(MESSAGE + " - causa: " + cause.getClass().getSimpleName());
    }
}
