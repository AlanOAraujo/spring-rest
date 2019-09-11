package br.com.devmedia.curso.resource.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.devmedia.curso.entities.DetalheErro;

/**
 * Essa Annotation @ControllerAdvice, trabalho como um ouvinte um Listener.
 * Sempre que um erro for lançado, essa annotation, irá chamar essa classe para tratar essa exception
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**E a @ExceptionHandler, que é a responsavél por informar a o @ControllerAdvice
     * qual erro ele deve ativar a classe. Passamos um Array informando quais erros
     * essa classe deve tratar.
    */
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> serverException(RuntimeException ex, WebRequest request){

        return handleExceptionInternal(
            ex, DetalheErro.builder()
                .addDetalhe("Uma exceção foi lançada.")
                .addErro(ex.getMessage())
                .addStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .addHttpMethod(getHttpMethod(request))
                .addPath(getPath(request))
                .build(),
             new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);

    }

    private String getPath(WebRequest request){
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    private String getHttpMethod(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getMethod();
    }
    
}