package br.com.dalla.project.fitcard.usuario.usuario;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "USUARIO")
@EntityListeners(AuditingEntityListener.class)
public class UsuarioModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name="NOME_LOGIN")
  private String nomeLogin;

  @Column(name="SENHA_LOGIN")
  private String senhaLogin;

  @Column(name = "NOME_USUARIO")
  private String nome;

  @Column(name = "EMAIL_PRIMARIO")
  private String email;
  
  @Column(name = "ATIVO")
  private boolean ativo;

  @CreatedDate
  @Column(name = "DATA_CRIACAO", updatable = false)
  Date dataCriacao;

  @LastModifiedDate
  @Column(name = "DATA_ATUALIZACAO", updatable = false)
  Date dataUltimaAtualizacao;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public Date getDataUltimaAtualizacao() {
    return dataUltimaAtualizacao;
  }

  public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
    this.dataUltimaAtualizacao = dataUltimaAtualizacao;
  }

  public String getNomeLogin() {
    return nomeLogin;
  }

  public void setNomeLogin(String nomeLogin) {
    this.nomeLogin = nomeLogin;
  }

  public String getSenhaLogin() {
    return senhaLogin;
  }

  public void setSenhaLogin(String senhaLogin) {
    this.senhaLogin = senhaLogin;
  }
}
