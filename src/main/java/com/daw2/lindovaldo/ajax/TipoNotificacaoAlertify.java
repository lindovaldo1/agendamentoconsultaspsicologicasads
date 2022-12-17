package com.daw2.lindovaldo.ajax;

public enum TipoNotificacaoAlertify {
	
	SUCESSO("success"),
	ERRO("error"),
	WARNING("warning"),
	MEMSAGEM("message");
	
	private String tipo;
	
	private TipoNotificacaoAlertify(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}	
}