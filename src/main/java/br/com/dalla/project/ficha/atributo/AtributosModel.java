package br.com.dalla.project.ficha.atributo;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ATRIBUTO")
@EntityListeners(AuditingEntityListener.class)
public class AtributosModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "NOME_ATRIBUTO   ")
    String nomeAtributo;

    public String getNomeAtributo() {
        return nomeAtributo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNomeAtributo(String nomeAtributo) {
        this.nomeAtributo = nomeAtributo;
    }

}
