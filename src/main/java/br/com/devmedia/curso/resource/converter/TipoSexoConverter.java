package br.com.devmedia.curso.resource.converter;

import org.springframework.core.convert.converter.Converter;

import br.com.devmedia.curso.entities.TipoSexo;

/**
 * TipoSexoConverter
 */
public class TipoSexoConverter implements Converter<String, TipoSexo>{

    @Override
    public TipoSexo convert(String texto) {

        char tipo = texto.charAt(0);

        return tipo == TipoSexo.FEMININO.getDesc() ? TipoSexo.FEMININO : TipoSexo.MASCULINO;
    }
    
}