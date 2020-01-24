package br.com.dalla.project.fitcard.categoria;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIA")
@EntityListeners(AuditingEntityListener.class)
public class CategoriaModel {

    public CategoriaModel(){}

    public CategoriaModel(Integer id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME_DA_CATEGORIA")
    private String categoria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }



}
