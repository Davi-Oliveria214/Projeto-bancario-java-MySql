package projetoBancario.informacoes;

import projetoBancario.DAO.UsuarioDAO;
import projetoBancario.excecoes.ExcecoesBanco;
import projetoBancario.excecoes.excecoesCadastro.EmailInvalido;
import projetoBancario.sessao.Sessao;

public class EditarUser {
	private Usuario usuario;
	private UsuarioDAO usuarioDAO;

	public EditarUser() {
		usuario = Sessao.getLogado();
		usuarioDAO = new UsuarioDAO();
	}

	public void editarEmail(String email) throws ExcecoesBanco {
		if (email.isEmpty()) {
			throw new EmailInvalido("O email de usuário não pode esta vazio");
		}

		if (usuario.getEmail().equals(email)) {
			throw new EmailInvalido("Digite um novo email de usuário para alterar");
		}

		if (usuarioDAO.verificarEmail(email)) {
			throw new EmailInvalido("Esse email de usuário já está cadastrado");
		}

		usuario.setEmail(email);
		usuarioDAO.atualizarUser(usuario);
	}
}