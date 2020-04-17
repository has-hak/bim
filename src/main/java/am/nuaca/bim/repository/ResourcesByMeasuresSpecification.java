package am.nuaca.bim.repository;

import java.util.List;
import javax.persistence.criteria.*;

import am.nuaca.bim.application.model.Measure;
import am.nuaca.bim.application.model.MeasureType;
import am.nuaca.bim.entity.Resource;
import am.nuaca.bim.service.ResourceSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Tigran Sargsyan on 17-Apr-20.
 */
public class ResourcesByMeasuresSpecification implements Specification<Resource> {

	private final int compilationId;

	private final String resourceTitle;

	private final List<Measure> measures;

	public ResourcesByMeasuresSpecification(ResourceSearchCriteria criteria) {
		this.compilationId = criteria.getCompilationId();
		this.measures = List.copyOf(criteria.getMeasures());
		resourceTitle = criteria.getResourceTitle();
	}

	@Override
	public Predicate toPredicate(Root<Resource> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Predicate compilationPredicate = builder.equal(root.get("compilation"), compilationId);
		Predicate titlePredicate = builder.like(root.get("title"), "%" + resourceTitle + "%");

		Predicate[] measurePredicates = new Predicate[measures.size()];

		for (int i = 0; i < measures.size(); i++) {
			Measure measure = measures.get(i);

			MeasureType type = measure.getType();
			double value = measure.getValue();

			Expression<Double> keyExpression = builder.function("JSON_EXTRACT", Double.class, root.get("measures"),
					builder.literal("$." + type));
			Predicate containsKey = builder.isNotNull(keyExpression);

			Expression<Double> startValueExpression = builder.function("JSON_EXTRACT", Double.class,
					root.get("measures"), builder.literal("$." + type + ".startValue"));
			Expression<Double> endValueExpression = builder.function("JSON_EXTRACT", Double.class, root.get("measures"),
					builder.literal("$." + type + ".endValue"));
			Predicate measurePredicate = builder.or(containsKey.not(),
					builder.between(builder.literal(value), startValueExpression, endValueExpression));

			measurePredicates[i] = measurePredicate;
		}

		Predicate[] predicates = new Predicate[measurePredicates.length + 2];
		predicates[0] = compilationPredicate;
		predicates[1] = titlePredicate;

		System.arraycopy(measurePredicates, 0, predicates, 2, measurePredicates.length);

		return builder.and(predicates);
	}
}
