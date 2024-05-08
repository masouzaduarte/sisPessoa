package sisPessoa.conversor;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import controller.PessoaBean;
import sisPessoa.dto.Cidade;

@FacesConverter("cidadeConverter")
public class CidadeConverter implements Converter {

    public Cidade getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
       
        PessoaBean pessoaBean = context.getApplication().evaluateExpressionGet(context, "#{pessoaBean}", PessoaBean.class);
        return  pessoaBean.getCidades().stream()
                .filter(c -> c.getId() == Integer.parseInt(value))
                .findFirst()
                .orElse(null);
    }

 
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || !(value instanceof Cidade)) {
            return null;
        }
        return String.valueOf(((Cidade) value).getId());
    }

}