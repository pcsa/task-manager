package com.esig.br.demo.exceptions;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

public class ResponsavelCreateOrUpdateException extends RuntimeException {

    private final Logger logger = LoggerFactory.logger(ResponsavelCreateOrUpdateException.class);
    private static final String MESSAGE = "O Responsavel não pôde ser salvo. Use DEBUG para mais detalhes.";

    public ResponsavelCreateOrUpdateException() {
        super(MESSAGE);
        logger.error(MESSAGE);
    }

    public ResponsavelCreateOrUpdateException(Throwable cause) {
        super(MESSAGE, cause);
        logger.error(MESSAGE + " - causa: " + cause.getClass().getSimpleName());
    }
}
