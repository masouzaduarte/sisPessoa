package sisPessoa.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import controller.PessoaBean;
import sisPessoa.dto.Estado;

@FacesConverter("estadoConverter")
public class EstadoConverter implements Converter {

    public Estado getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
       
        PessoaBean pessoaBean = context.getApplication().evaluateExpressionGet(context, "#{pessoaBean}", PessoaBean.class);
       
        Estado estado= pessoaBean.getEstados().stream()
                .filter(e -> e.getId() == Integer.parseInt(value))
                .findFirst()
                .orElse(null);
        
        return estado;
    }

    public String getAsString(FacesContext context, UIComponent component, Estado value) {
        if (value == null) {
            return "";  
        }
        return String.valueOf(value.getId());  
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || !(value instanceof Estado)) {
            return null;
        }
        return String.valueOf(((Estado) value).getId());
    }

}