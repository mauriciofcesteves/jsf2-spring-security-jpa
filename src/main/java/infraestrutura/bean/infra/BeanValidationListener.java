package infraestrutura.bean.infra;

import infraestrutura.exception.BeanValidationMessage;
import infraestrutura.exception.MensagemNegocio;
import infraestrutura.exception.NegocioException;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.TraversableResolver;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.Hibernate;

/**
 * Listener para validar os dados das entidades antes de serem salvas.
 */
public class BeanValidationListener {

	/**
	 * Valida os dados da entidade a ser persistida.
	 */
	@PrePersist
	@PreUpdate
	public void validate(Object entity) {
		TraversableResolver tr = new MyTraversableResolver();
		Validator validator = Validation.buildDefaultValidatorFactory().usingContext()
				.traversableResolver(tr).getValidator();

		final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(entity);

		if (!constraintViolations.isEmpty()) {
			List<MensagemNegocio> messages = new ArrayList<MensagemNegocio>();

			for (ConstraintViolation<?> violation : constraintViolations) {
				String fieldName = violation.getPropertyPath().toString();
				String message = violation.getMessageTemplate();
				MensagemNegocio errorMessage = null;

				if (message.contains(BeanValidationMessage.NOT_EMPTY.getKey())) {
					errorMessage = new MensagemNegocio(BeanValidationMessage.NOT_EMPTY,
							new Object[] { fieldName });
				}
				
				if (errorMessage != null) {
					messages.add(errorMessage);
				}
			}

			throw new NegocioException(messages);
		}
	}

	class MyTraversableResolver implements TraversableResolver {

		/**
		 * {@inheritDoc}
		 */
		public boolean isReachable(Object traversableObject, Path.Node traversableProperty,
				Class<?> rootBeanType, Path pathToTraversableObject, ElementType elementType) {
			return traversableObject == null || Hibernate.isInitialized(traversableObject);
		}

		/**
		 * {@inheritDoc}
		 */
		public boolean isCascadable(Object traversableObject, Path.Node traversableProperty,
				Class<?> rootBeanType, Path pathToTraversableObject, ElementType elementType) {
			return true;
		}
	}
}
