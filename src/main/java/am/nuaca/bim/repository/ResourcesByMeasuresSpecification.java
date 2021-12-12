package am.nuaca.bim.repository;

import java.util.List;
import javax.persistence.criteria.*;

import am.nuaca.bim.application.model.Measure;
import am.nuaca.bim.application.model.MeasureType;
import am.nuaca.bim.entity.Resource;
import am.nuaca.bim.service.ResourceAttributes;
import am.nuaca.bim.service.ResourceSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Ani Khachatryan on 17-Apr-20.
 */
public class ResourcesByMeasuresSpecification implements Specification<Resource> {

	private final int compilationId;

	private final List<ResourceAttributes> resources;

	public ResourcesByMeasuresSpecification(ResourceSearchCriteria criteria) {
		this.compilationId = criteria.getCompilationId();
		this.resources = List.copyOf(criteria.getResources());
	}

	@Override
	public Predicate toPredicate(Root<Resource> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Predicate compilationPredicate = builder.equal(root.get("compilation"), compilationId);

		Predicate[] resourcesPredicates = new Predicate[resources.size()];

		for (int i = 0; i < resources.size(); i++) {
			ResourceAttributes resourceAttributes = resources.get(i);
			String resourceTitle = resourceAttributes.getTitle();
			List<Measure> measures = resourceAttributes.getMeasures();
			Predicate[] resourcePredicates = new Predicate[measures.size() + 1];

			resourcePredicates[0] = builder.like(root.get("title"), "%" + resourceTitle + "%");
			for (int j = 0; j < measures.size(); j++) {
				Measure measure = measures.get(j);
				MeasureType type = measure.getType();
				double value = measure.getValue();

				Expression<Double> keyExpression = builder.function("JSON_EXTRACT", Double.class, root.get("measures"),
						builder.literal("$." + type));
				Predicate containsKey = builder.isNotNull(keyExpression);

				Expression<Double> startValueExpression = builder.function("JSON_EXTRACT", Double.class,
						root.get("measures"), builder.literal("$." + type + ".startValue"));
				Expression<Double> endValueExpression = builder.function("JSON_EXTRACT", Double.class,
						root.get("measures"), builder.literal("$." + type + ".endValue"));
				Predicate measurePredicate = builder.or(containsKey.not(),
						builder.between(builder.literal(value), startValueExpression, endValueExpression));

				resourcePredicates[j + 1] = measurePredicate;
			}

			resourcesPredicates[i] = builder.and(resourcePredicates);
		}

		return builder.or(compilationPredicate, builder.and(resourcesPredicates));
	}
}
