package me.dio.cardapio.service.exception;

public class ResourceNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super("Recurso não encontrado.");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
