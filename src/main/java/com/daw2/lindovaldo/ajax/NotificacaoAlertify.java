package com.daw2.lindovaldo.ajax;

public class NotificacaoAlertify {

	private String mensagem;
	private TipoNotificacaoAlertify tipo;
	private int intervalo = 4;

	public NotificacaoAlertify(String mensagem) {
		this.mensagem = mensagem;
		tipo = TipoNotificacaoAlertify.MEMSAGEM;
	}

	public NotificacaoAlertify(String mensagem, TipoNotificacaoAlertify tipo) {
		this.mensagem = mensagem;
		this.tipo = tipo;
	}
	
	public NotificacaoAlertify(String mensagem, TipoNotificacaoAlertify tipo, int intervalo) {
		this.mensagem = mensagem;
		this.tipo = tipo;
		this.intervalo = intervalo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getTipo() {
		return tipo.getTipo();
	}

	public void setTipo(TipoNotificacaoAlertify tipo) {
		this.tipo = tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = TipoNotificacaoAlertify.valueOf(tipo);
	}

	public int getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}

	@Override
	public String toString() {
		return "mensagem: " + mensagem + "\ntipo: " + tipo.getTipo() + "\nintervalo: " + intervalo;
	}

}