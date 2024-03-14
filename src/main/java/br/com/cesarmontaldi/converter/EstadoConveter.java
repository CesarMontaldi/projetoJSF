package br.com.cesarmontaldi.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.cesarmontaldi.jpautil.JpaUtil;
import br.com.cesarmontaldi.model.Estados;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@FacesConverter(forClass = Estados.class, value = "estadoConverter")
public class EstadoConveter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override /* Retorna o Objeto inteiro */
	public Object getAsObject(FacesContext context, UIComponent component, String codigoEstado) {
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Estados estado = (Estados) entityManager.find(Estados.class, Long.parseLong(codigoEstado));
		
		return estado;
	}

	@Override /* Retorna apenas o código do Objeto em String */
	public String getAsString(FacesContext context, UIComponent component, Object estado) {
		
		if (estado == null) {
			return null;
		}
		
		if (estado instanceof Estados) {
			return ((Estados) estado).getId().toString();
		}
		
		else {
			return estado.toString();
		}
	}

}