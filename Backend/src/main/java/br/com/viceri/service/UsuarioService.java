package br.com.viceri.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.viceri.model.UserLogin;
import br.com.viceri.model.Usuario;
import br.com.viceri.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario){
		
		if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
			return Optional.empty();
		
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		
		return Optional.of(usuarioRepository.save(usuario));
		
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {
			
			Optional<Usuario> buscaUsuario = usuarioRepository.findByEmail(usuario.getEmail());
			
			if(buscaUsuario.isPresent()) {
				if(buscaUsuario.get().getId() != usuario.getId())
					return Optional.empty(); 
			}
			
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			
			return Optional.of(usuarioRepository.save(usuario));
		}
		
		return Optional.empty();
	}
	public Optional<UserLogin> autenticarUsuario(Optional<UserLogin> usuarioLogin){
		Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioLogin.get().getEmail());
		
		if(usuario.isPresent()) {
			if(compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getEmail(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				
				return usuarioLogin;
			}	
		}
		
		return Optional.empty();
	}

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);
	
	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaBanco);
		
	}
	
	private String gerarBasicToken(String usuario, String senha) {
		
		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);
	}
	
}
