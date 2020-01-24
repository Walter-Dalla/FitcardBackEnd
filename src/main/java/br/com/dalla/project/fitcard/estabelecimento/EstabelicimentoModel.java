package br.com.dalla.project.fitcard.estabelecimento;

import br.com.dalla.project.fitcard.categoria.CategoriaModel;
import br.com.dalla.project.fitcard.usuario.usuario.UsuarioModel;
import br.com.dalla.project.fitcard.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ESTABELECIMENTO")
@EntityListeners(AuditingEntityListener.class)
public class EstabelicimentoModel implements Serializable {
    public EstabelicimentoModel(){}

    public EstabelicimentoModel(@CNPJ(message = "CNPJ invalido") @NotEmpty(message = "CNPJ não pode ser vazio.") String cnpj, String razaoSocial, String nomeFantasia, @Email String email, String endereco, String cidade, String estado, String cep, UsuarioModel usuario, String telefone, Date dataCadastro, Date dataAlteracao, CategoriaModel categoria, Boolean status, String conta, String agencia) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.usuario = usuario;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
        this.dataAlteracao = dataAlteracao;
        this.categoria = categoria;
        this.status = status;
        this.conta = conta;
        this.agencia = agencia;
    }

    @Id
    @CNPJ(message = "CNPJ invalido")
    @NotEmpty(message = "CNPJ não pode ser vazio.")
    @Column(name = "CNPJ", nullable = false, length = 18, updatable = false)
    private String cnpj;

    @Column(name = "RAZAO_SOCIAL", nullable = false)
    private String razaoSocial;

    @Column(name = "NOME_FANTASIA")
    private String nomeFantasia;

    @Email
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "ESTADO")
    private  String estado;

    @Column(name = "CEP")
    private  String cep;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "ID_USUARIO", updatable = false, nullable = false)
    UsuarioModel usuario;

    @Column(name = "TELEFONE", length = 14)
    private String telefone;

    @CreatedDate
    @Column(name = "DATA_CRIACAO")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @LastModifiedDate
    @Column(name = "DATA_ALTERACAO")
    @Temporal(TemporalType.DATE)
    private Date dataAlteracao;

    @OneToOne
    @JoinColumn(name = "CATEGORIA")
    private CategoriaModel categoria; // [Supermercado, Restaurante, Borracharia, Posto, Oficina]

    @Column(name = "STATUS")
    private Boolean  status = true; // [Ativo, Inativo]

    @Column(name = "CONTA", length = 8)
    private String conta;

    @Column(name = "AGENCIA", length = 5)
    private String agencia;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        if(Utils.validateContaMask(conta))
            this.conta = conta;
        else
            this.conta = null;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        if(Utils.validateAgenciaMask(agencia))
            this.agencia = agencia;
        else
            this.agencia = null;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }


}
