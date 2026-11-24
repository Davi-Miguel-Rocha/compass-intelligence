package converter;

import bean.ConversaoBean;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import modelo.Moeda;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value="moedaConverter", managed = true)
public class MoedaConverter implements Converter<Moeda> {

    @Inject
   private ConversaoBean conversaoBean;


    @Override
    public Moeda getAsObject(FacesContext context, UIComponent component, String value) {


        if (value == null || value.isBlank()) {
            return null;
        }

        Long id = Long.valueOf(value);


        for (int i = 0; i < conversaoBean.getMoedas().size(); i++) {

            Moeda moeda = conversaoBean.getMoedas().get(i);

            if (moeda.getId().equals(id)) {
                return moeda;
            }
        }

        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Moeda value) {

        if(value == null){

            return "";
        }
        return String.valueOf(value.getId());
    }
}
