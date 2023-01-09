package com.generic.webpage.converter;

import com.generic.webpage.entities.IConvertible;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import java.util.List;

@Named
public class GenericConverter implements Converter<Object> {
    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        IConvertible ret = null;
        if (arg1 != null) {
            List<UIComponent> childs = arg1.getChildren();
            UISelectItems itens = null;
            if (childs != null) {
                for (UIComponent ui : childs) {
                    if (ui instanceof UISelectItems) {
                        itens = (UISelectItems) ui;
                        break;
                    } else if (ui instanceof UISelectItem item) {
                        try {
                            IConvertible val = (IConvertible) item.getItemValue();
                            if (arg2.equals("" + val.getId())) {
                                ret = val;
                                break;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
            }

            if (itens != null) {
                List<IConvertible> values = (List<IConvertible>) itens.getValue();
                if (values != null) {
                    for (IConvertible val : values) {
                        if (arg2.equals("" + val.getId())) {
                            ret = val;
                            break;
                        }
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        String ret = "";
        if (arg2 instanceof IConvertible m) {
            Integer id = m.getId();
            if (id != null) {
                ret = id.toString();
            }
        }
        return ret;
    }
}
