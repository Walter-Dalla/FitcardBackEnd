package br.com.dalla.project.fitcard.usuario.login.token;


import br.com.dalla.project.fitcard.usuario.conexao.ConexaoModel;
import br.com.dalla.project.fitcard.usuario.usuario.UsuarioModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TOKEN")
@EntityListeners(AuditingEntityListener.class)
public class TokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "ID_USUARIO")
    @JsonIgnore
    private UsuarioModel usuario;

    @Column(name = "TOKEN")
    private String token;

    @CreatedDate
    @Column(name = "DATA_CRIACAO")
    private Date dataCriacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

