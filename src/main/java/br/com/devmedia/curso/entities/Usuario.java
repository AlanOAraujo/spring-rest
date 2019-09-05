package br.com.devmedia.curso.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.format.Formatter;
import br.com.devmedia.curso.configuration.JacksonConfig;
import br.com.devmedia.curso.type.PGEnumUserType;

@Table(name = "usuario_rest",
        indexes = { @Index (
                columnList = "nm_usrcpf_usr",
                unique = true,
                name = "unique_cpf"
            )
        }
)
@TypeDef( name = "psql_enum", typeClass = PGEnumUserType.class )
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@SequenceGenerator(name = "usuario_rest_id_usuari_usr_seq", sequenceName = "usuario_rest_id_usuari_usr_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario_rest_id_usuari_usr_seq")
	@Column(name = "id_usuari_usr")
    private Long id;
	
	@NotBlank
	@Size(min = 3, max = 50)
	@Column(nullable = false, name = "nm_usrnom_usr")
	private String nome;
    
    @NotBlank
	@Column(length=11, nullable=false, name="nm_usrcpf_usr")
	private String cd_cpf;

	@NotBlank
	@Size(min = 3, max = 50, message = "Campo requerido entre {min} e {max} caracteres")
	@Column(name = "nm_usrmae_usr")
	private String nomeMae;
    
	@NotNull(message = "O Campo data de nascimento é requerido.")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = JacksonConfig.DEFAULT_DATE_FORMAT)
	@Column(nullable = false, name = "dt_usrnas_usr")
    private LocalDate dtNascimento;
	
	@Enumerated(EnumType.STRING)
	@Type( type = "psql_enum" )
	@Column(nullable = false, name = "sexo")
    private TipoSexo sexo;

    public Usuario(){}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCd_cpf() {
        return cd_cpf;
    }

    public void setCd_cpf(String cd_cpf) {
        this.cd_cpf = cd_cpf;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public TipoSexo getSexo() {
        return sexo;
    }

    public void setSexo(TipoSexo sexo) {
        this.sexo = sexo;
    }

    public String getCd_codcpf_fmt(){

        Formatter formatter = new CPFFormatter();

		String s = "";

		if ( getCd_cpf() != null && !"".equals(getCd_cpf()))
			s = formatter.format(getCd_cpf());

		return s;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Usuario [cd_cpf=" + cd_cpf + ", nome=" + nome + ", nomeMae=" + nomeMae + "]";
    }

}