package br.com.dalla.project.fitcard.estabelecimento;


import br.com.dalla.project.fitcard.categoria.CategoriaModel;
import br.com.dalla.project.fitcard.usuario.usuario.UsuarioModel;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<EstabelicimentoModel, Integer> {

    List<EstabelicimentoModel> findAll();


    Optional<EstabelicimentoModel> findByCnpj(String cnpj);

    @Transactional
    void deleteByCnpj(String cnpj);

    List<EstabelicimentoModel> findAllByUsuario(UsuarioModel usuario);

    Optional<EstabelicimentoModel> findByCnpjAndUsuario(String cnpj, UsuarioModel usuario);

    @Query("SELECT est FROM EstabelicimentoModel est WHERE " +
            " (est.usuario.id = :usuarioId) " +
            " and (est.cnpj like :cnpj or :cnpj is null) " +
            " and (est.razaoSocial like :razaoSocial or :razaoSocial is null) " +
            " and (est.nomeFantasia like :nomeFantasia or :nomeFantasia is null) "  +
            " and (est.email like :email or :email is null) " +
            " and (est.endereco like :endereco or :endereco is null) " +
            " and (est.cidade like :cidade or :cidade is null) " +
            " and (est.estado like :estado or :estado is null) " +
            " and (est.cep like :cep or :cep is null) " +
            " and (est.telefone like :telefone or :telefone is null) " +
            " and (est.dataCadastro = :dataCadastro or :dataCadastro is null) " +
            " and (est.dataAlteracao = :dataAlteracao or :dataAlteracao is null) " +
            " and (est.categoria like :categoria or :categoria is null) " +
            " and (est.status = :status or :status is null) " +
            " and (est.conta like :conta or :conta is null) " +
            " and (est.agencia like :agencia or :agencia is null)")
    List<EstabelicimentoModel> findAllByEstabelecimento(
        @Param("usuarioId") Integer usuarioId,
        @Param("cnpj") String cnpj,
        @Param("razaoSocial") String razaoSocial,
        @Param("nomeFantasia") String nomeFantasia,
        @Param("email") String email,
        @Param("endereco") String endereco,
        @Param("cidade") String cidade,
        @Param("estado") String estado,
        @Param("cep") String cep,
        @Param("telefone") String telefone,
        @Param("dataCadastro") Date dataCadastro,
        @Param("dataAlteracao") Date dataAlteracao,
        @Param("categoria") CategoriaModel categoria,
        @Param("status") Boolean status,
        @Param("conta") String conta,
        @Param("agencia") String agencia);
}
